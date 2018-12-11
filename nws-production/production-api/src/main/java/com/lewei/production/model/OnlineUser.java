package com.lewei.production.model;

import java.util.Date;

/**
 * @author 马路遥(229010812 @ qq.com)
 * @version V1.0
 * @Title: nws-production
 * @Package com.lewei.production.model
 * @Description: TODO
 * @Date 2017-12-19 13:26
 */
public class OnlineUser {
    /*用户id*/
    private Integer id;
    /*用户姓名*/
    private String name;
    /*用户账户*/
    private String username;
    /*在线时间*/
    private Date lastvisitdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastvisitdate() {
        return lastvisitdate;
    }

    public void setLastvisitdate(Date lastvisitdate) {
        this.lastvisitdate = lastvisitdate;
    }
}
