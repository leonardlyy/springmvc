package com.lewei.production.controller;

import com.lewei.production.service.CommonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 马路遥(229010812 @ qq.com)
 * @version V1.0
 * @Title: nws-production
 * @Package com.lewei.production.controller
 * @Description: TODO
 * @Date 2017-12-18 17:31
 */
@Controller
@RequestMapping(value="common")
public class CommonController {

    @Resource
    private CommonService commonService;

    @ResponseBody
    @RequestMapping(value="up-online-user",method = RequestMethod.GET)
    public void setOnlineUser(@RequestParam Integer userId,
                              @RequestParam String username,
                              @RequestParam String name){
        commonService.setOnlineUser(userId,username,name);
        return;
    }

    @ResponseBody
    @RequestMapping(value="online-user-list",method = RequestMethod.GET)
    public String getOnlineUserList(){
        return commonService.getOnlineUserList();
    }

}
