package com.lewei.production.web;


import com.lewei.production.util.Pagination;
import com.lewei.production.util.SystemContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * 分页
 * @Doc
 */
@Intercepts({@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class})})
public class PageInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(PageInterceptor.class);

    private static final String FORM = "from";

    private String databaseType;// 数据库类型，不同的数据库有不同的分页方法

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        if (target instanceof StatementHandler) {
            RoutingStatementHandler handler = (RoutingStatementHandler) target;
            StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
            MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");
            BoundSql boundSql;
            if (mappedStatement != null) {
                if (mappedStatement.getId().endsWith("Page")) {
                    boundSql = delegate.getBoundSql();
                    String sql = boundSql.getSql();
                    Connection connection = (Connection) invocation.getArgs()[0];
                    Pagination<?> obj = (Pagination<?>) boundSql.getParameterObject();
//          sql = getCountSql(sql);
                    setTotalRecord(obj, mappedStatement, connection);
                    String pageSql = this.getPageSql(obj, sql);
                    ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
        databaseType = properties.getProperty("databaseType");
    }

    /**
     * 根据page对象获取对应的分页查询Sql语句，这里只做了两种数据库类型，Mysql和Oracle 其它的数据库都 没有进行分页
     *
     * @param page 分页对象
     * @param sql  原sql语句
     * @return
     */
    private String getPageSql(Pagination<?> page, String sql) {
        this.databaseType = SystemContext.getType();
        if ("mysql".equalsIgnoreCase(SystemContext.getType())) {
            return getMysqlPageSql(page, sql);
        } else if ("oracle".equalsIgnoreCase(SystemContext.getType())) {
            return getOraclePageSql(page, sql);
        } else if("sqlServer".equalsIgnoreCase(SystemContext.getType())){
            return getSqlServerPageSql(page, sql);
        }
        return sql;
    }

    /**
     * 获取Mysql数据库的分页查询语句
     *
     * @param page 分页对象
     * @return Mysql数据库分页语句
     */
    private static String getMysqlPageSql(Pagination<?> page, String sql) {
        StringBuilder sqlBuilder = new StringBuilder();
        int offset = (page.getPageNo() - 1) * page.getPageSize();
        sqlBuilder.append(sql);
        sqlBuilder.append(" limit ");
        sqlBuilder.append(offset);
        sqlBuilder.append(",");
        sqlBuilder.append(page.getPageSize());
        return sqlBuilder.toString();
    }

    /**
     * 获取Oracle数据库的分页查询语句
     *
     * @param pagination 分页对象
     * @param sql        包含原sql语句的StringBuffer对象
     * @return Oracle数据库的分页查询语句
     */
    private static String getOraclePageSql(Pagination<?> pagination, String sql) {
        sql = formatSql(sql);
        StringBuilder sqlBuilder = new StringBuilder();
        int offset = (pagination.getPageNo() - 1) * pagination.getPageSize() + 1;
        sqlBuilder.append("select * from ( select t.*, rownum rn from ( ");
        sqlBuilder.append(sql);
        sqlBuilder.append(" ) t where rownum < ");
        sqlBuilder.append(offset + pagination.getPageSize());
        sqlBuilder.append(" ) where rn >= ");
        sqlBuilder.append(offset);
        return sqlBuilder.toString();
    }
    /**
     * 获取SqlServer数据库的分页查询语句
     *
     * @param page 分页对象
     * @return Mysql数据库分页语句
     */
    private static String getSqlServerPageSql(Pagination<?> page, String sql) {
        sql = formatSql(sql);
        StringBuilder sqlBuilder = new StringBuilder();
        int offset = (page.getPageNo() - 1) * page.getPageSize();
        sqlBuilder.append("SELECT * FROM( SELECT TOP ");
        sqlBuilder.append(offset + page.getPageSize());
        sqlBuilder.append(" ROW_NUMBER() OVER(ORDER  BY ");
        //转化toUpperCase()大写
        sqlBuilder.append(sql.substring(sql.toUpperCase().indexOf("SELECT")+6,sql.toUpperCase().indexOf("FROM")));
        sqlBuilder.append(" DESC) AS ROWID,");
        sqlBuilder.append(sql.substring(sql.toUpperCase().indexOf("SELECT")+6));
        sqlBuilder.append( " ) as t WHERE ROWID > ");
        sqlBuilder.append(offset);
        return sqlBuilder.toString();
    }
    /**
     * 给当前的参数对象page设置总记录数
     *
     * @param pagination      Mapper映射语句对应的参数对象
     * @param mappedStatement Mapper映射语句
     * @param connection      当前的数据库连接
     */
    private static void setTotalRecord(Pagination<?> pagination, MappedStatement mappedStatement,
                                       Connection connection) {
        BoundSql boundSql = mappedStatement.getBoundSql(pagination);
        String sql = boundSql.getSql();
        String countSql = getCountSql(sql);
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        BoundSql countBoundSql =
                new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, pagination);
        ParameterHandler parameterHandler =
                new DefaultParameterHandler(mappedStatement, pagination, countBoundSql);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(countSql);
            parameterHandler.setParameters(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int totalRecord = rs.getInt(1);
                pagination.setTotal(totalRecord);
                logger.debug("page :" + pagination);
            }
        } catch (SQLException e) {
            logger.error("查询数据库出错:{}", e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logger.error("关闭rs,psmt出错:{}", e.getMessage());
            }
        }
    }

    /**
     * 根据原Sql语句获取对应的查询总记录数的Sql语句
     *
     * @param sql
     * @return
     */
    private static String getCountSql(String sql) {
        sql = formatSql(sql);
        sql = StringUtils.lowerCase(sql);

        // 去掉排序语句
        int orderIndex = StringUtils.indexOf(sql, " order");
        if (orderIndex > 0) {
            sql = sql.substring(0, orderIndex);
        }
        // 分组查询语句不截取from前的语句
//    if (StringUtils.contains(sql, "count")) {
//      sql = "from (" + sql + ")";
//    } else {
        sql = StringUtils.substring(sql, StringUtils.lastIndexOf(sql, FORM));
//    }
        return "select count(*) " + sql;
    }

    /**
     * 去除sql中空格，制表符等不可见字符
     *
     * @param sql
     * @return
     */
    private static String formatSql(String sql) {
        return StringUtils.replacePattern(sql, "\\s{1,}", " ");
    }


    /**
     * 利用反射进行操作的一个工具类
     */
    private static class ReflectUtil {
        /**
         * 利用反射获取指定对象的指定属性
         *
         * @param object    目标对象
         * @param fieldName 目标属性
         * @return 目标属性的值
         */
        public static Object getFieldValue(Object object, String fieldName) {
            Object result = null;
            Field field = ReflectUtil.getDeclaredField(object, fieldName);
            if (field != null) {
                field.setAccessible(true);
                try {
                    result = field.get(object);
                } catch (IllegalArgumentException e) {
                    logger.error("非法参数：{}", e.getMessage());
                } catch (IllegalAccessException e) {
                    logger.error("非法访问：{}", e.getMessage());
                }
            }
            return result;
        }

        /**
         * 利用反射获取指定对象里面的指定属性
         *
         * @param object    目标对象
         * @param filedName 目标属性
         * @return 目标字段
         */
        private static Field getDeclaredField(Object object, String filedName) {
            if (object != null) {
                for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass =
                        superClass.getSuperclass()) {
                    try {
                        return superClass.getDeclaredField(filedName);
                    } catch (NoSuchFieldException e) {
                        // Field 不在当前类定义, 继续向上转型
                    }
                }
            }
            return null;
        }

        /**
         * 利用反射设置指定对象的指定属性为指定的值
         *
         * @param object     目标对象
         * @param fieldName  目标属性
         * @param fieldValue 目标值
         */
        public static void setFieldValue(Object object, String fieldName, String fieldValue) {
            Field field = ReflectUtil.getDeclaredField(object, fieldName);
            if (field != null) {
                try {
                    field.setAccessible(true);
                    field.set(object, fieldValue);
                } catch (IllegalArgumentException e) {
                    logger.error("非法参数：{}", e.getMessage());
                } catch (IllegalAccessException e) {
                    logger.error("非法访问：{}", e.getMessage());
                }
            }
        }
    }
}
