package com.lewei.production.datasource;

import com.lewei.production.service.UserService;
import com.lewei.production.util.SystemContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author quweizhe
 * @time 2017/3/8 19:33.
 * Project idea4Customer
 * Package com.lewei.production.datasource
 * @doc
 */
@Component
@Aspect
public class DataSourceInterceptor {
    private final static Logger LOGGER = LoggerFactory.getLogger(DataSourceInterceptor.class);

    @Before("execution(* com.lewei.production.mapper.orclmapper..*(..))")
    public void setDataSourceThree(JoinPoint jp) {
        System.out.println("oracle");
        SystemContext.setType("oracle");
        MultipleDataSource.setDataSourceKey("oracleDataSource");
    }

    @Before("execution(* com.lewei.production.mapper.promapper..*(..))")
    public void setDataSourceOne(JoinPoint jp) {
      //  System.out.println("mysql");
        SystemContext.setType("mysql");
        LOGGER.error("数据库{}","mysql");
        MultipleDataSource.setDataSourceKey("mysqlDataSource");

    }

    @Before("execution(* com.lewei.production.mapper.erpmapper..*(..))")
    public void setDataSourceTwo(JoinPoint jp) {
      //  System.out.println("sqlServer");
        LOGGER.error("数据库{}","sqlServer");
        SystemContext.setType("sqlServer");
        MultipleDataSource.setDataSourceKey("sqlServerDataSource");

    }


}
