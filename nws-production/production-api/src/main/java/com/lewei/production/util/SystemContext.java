package com.lewei.production.util;

/**
 * @author quweizhe
 * @time 2017/3/20 18:16.
 * Project idea4Customer
 * Package com.lewei.production.util
 * @doc
 */
public class SystemContext {

    private static ThreadLocal<String> type = new ThreadLocal<String>();

    public static String getType() {
        return type.get();
    }

    public static void setType(String type) {
        SystemContext.type.set(type);
    }


}
