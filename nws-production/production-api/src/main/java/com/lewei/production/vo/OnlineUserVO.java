package com.lewei.production.vo;

import com.lewei.production.model.OnlineUser;
import com.lewei.production.util.Code;
import com.lewei.production.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author 马路遥(229010812 @ qq.com)
 * @version V1.0
 * @Title: nws-production
 * @Package com.lewei.production.vo
 * @Description: TODO
 * @Date 2017-12-19 19:43
 */
public class OnlineUserVO {
    /*用户id*/
    private Integer id;
    /*用户姓名*/
    private String name;
    /*用户账户*/
    private String username;
    /*在线时间*/
    private String lastvisitdate;

    public OnlineUserVO() {

    }

    public OnlineUserVO(OnlineUser onlineUser) {
        this.id = onlineUser.getId();
        this.name = onlineUser.getName();
        this.username = onlineUser.getUsername();
        this.lastvisitdate = CommonUtils.dateCastToString(onlineUser.getLastvisitdate(), Code.YYYY_MM_DD_HH_MM_SS);
    }

    public static List<OnlineUserVO> getList(List<OnlineUser> onlineUsers){
        List<OnlineUserVO> onlineUserVOS = new ArrayList<OnlineUserVO>();
        if(onlineUsers!=null){
            for(OnlineUser onlineUser:onlineUsers){
                OnlineUserVO onlineUserVO =new OnlineUserVO(onlineUser);
                onlineUserVOS.add(onlineUserVO);
            }
        }
        return onlineUserVOS;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastvisitdate() {
        return lastvisitdate;
    }

    public void setLastvisitdate(String lastvisitdate) {
        this.lastvisitdate = lastvisitdate;
    }
}
