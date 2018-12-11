package com.lewei.production.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author quweizhe
 * @time 2017/3/8 18:35.
 * Project idea4Customer
 * Package com.lewei.production.datasource
 * @doc
 */
public class MultipleDataSource extends AbstractRoutingDataSource {


    private static final ThreadLocal<String> dataSourceKey = new ThreadLocal<String>();

    public static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(dataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }
}
