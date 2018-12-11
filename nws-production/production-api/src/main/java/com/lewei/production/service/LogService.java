package com.lewei.production.service;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

/**
 * @author quweizhe
 * @time 2017/3/20 11:26.
 * Project idea4Customer
 * Package com.lewei.production.service
 * @doc
 */
@Service
public class LogService {

    public void save(Integer userId,String description,String module, String method, String requestURI) {
        //TODO 保存逻辑，其他参数再添加
        //使用方法：在controller的方法上添加@SystemLog(description="自定义",module= Module.GENERAL)注释

    }
}
