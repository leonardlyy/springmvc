package com.lewei.production.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2018-6-9 11:24.
 *
 * @author Kevin
 * <pro>
 * Please Write Class Comments.
 * </pro>
 */
public enum CachePool {

    INSTANCE;

    private Map<String, Boolean> bw = new HashMap<String, Boolean>();

    public Map<String, Boolean> getBw() {
        return bw;
    }
}
