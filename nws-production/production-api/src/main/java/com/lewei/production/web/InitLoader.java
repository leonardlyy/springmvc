package com.lewei.production.web;


import com.lewei.production.util.CachePool;

public class InitLoader {

    public void execute()  {
        setBW(); 
    } 
    private void setBW() {
        CachePool.INSTANCE.getBw().put("Baan.Application.barcod1",true);
        CachePool.INSTANCE.getBw().put("Baan.Application.2",true);
        CachePool.INSTANCE.getBw().put("Baan.Application.3",true);
        CachePool.INSTANCE.getBw().put("Baan.Application.4",true);
        CachePool.INSTANCE.getBw().put("Baan.Application.barcode5",true);
    }
}
