package com.lewei.production.annotation;

import org.springframework.stereotype.Repository;

import java.lang.annotation.*;

/**
 * Created by qwz on 2016/10/28.
 * project common-framework.
 * 自定义注解，DAO层注解
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repository
public @interface MyBatis {

    /**
     * 返回spring逻辑组件的名称，添加该注解，自动注入为DAO组件
     *
     * @return 默认的组建名称
     */
    String value() default "";
}
