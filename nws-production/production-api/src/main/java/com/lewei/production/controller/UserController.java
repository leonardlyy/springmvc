package com.lewei.production.controller;

import com.lewei.production.model.UserDutyAuthority;
import com.lewei.production.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 22901 on 2017/3/16.
 */
@Controller
@RequestMapping(value="user")
public class UserController {
    @Resource
    private UserService userService;


    /**
     * 用户列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "userlist" ,method = RequestMethod.GET)
    public String userList(@RequestParam(required = false) String username,
                           @RequestParam(required = false) String companyNo,
                           @RequestParam(required = false) String warehouseNo,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String dutyid,
                           @RequestParam(required = false) Integer pageNo,
                           @RequestParam(required = false) Integer pageSize){
        return userService.userList(username, companyNo, warehouseNo, name, dutyid, pageNo, pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "userdetails" ,method = RequestMethod.GET)
    public String userDetails(@RequestParam(required = false) String username){
        return userService.userDetails(username);
    }
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login" ,method = RequestMethod.GET)
    public String login(@RequestParam(required = false) String username,
                        @RequestParam(required = false) String password){
        return userService.login(username,password);
    }

    /**
     * 添加用户
     * @param username
     * @param password
     * @param dutyid
     * @param name
     * @param len
     * @param defaultprinter
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "saveuser" ,method = RequestMethod.GET)
    public String saveUser(@RequestParam(required = false) String username,
                           @RequestParam(required = false) String password,
                           @RequestParam(required = false) Integer dutyid,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String tel,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) String len,
                           @RequestParam(required = false) String warehouseNo,
                           @RequestParam(required = false) String warehouseNo1,
                           @RequestParam(required = false) String seri,
                           @RequestParam(required = false) String seriN,
                           @RequestParam(required = false) String otyp,
                           @RequestParam(required = false) String companyNo,
                           @RequestParam(required = false) String defaultprinter){
        return userService.saveUser(name,username,password,tel,email,dutyid,defaultprinter,len,"1",warehouseNo,warehouseNo1,seri,seriN,otyp,companyNo);
    }

    /**
     * 修改用户资料、修改密码、删除用户（理论）、修改默认打印机
     * @param username
     * @param password
     * @param dutyid
     * @param name
     * @param len
     * @param defaultprinter
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateuser" ,method = RequestMethod.GET)
    public String updateUser(@RequestParam Integer userid,
                             @RequestParam(required = false) String username,
                             @RequestParam(required = false) String password,
                             @RequestParam(required = false) Integer dutyid,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String len,
                             @RequestParam(required = false) String tel,
                             @RequestParam(required = false) String email,
                             @RequestParam(required = false) String defaultprinter,
                             @RequestParam(required = false) String status,
                             @RequestParam(required = false) String warehouseNo,
                             @RequestParam(required = false) String warehouseNo1,
                             @RequestParam(required = false) String seri,
                             @RequestParam(required = false) String seriN,
                             @RequestParam(required = false) String otyp,
                             @RequestParam(required = false) String companyNo){
        return userService.updateUser(userid,name,username,password,tel,email,dutyid,defaultprinter,len,status,companyNo,warehouseNo,warehouseNo1,seri,seriN,otyp);

    }

    /**
     * 职务列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "dutylist" ,method = RequestMethod.GET)
    public String getDutyList(){
        return userService.selectDutyList();
    }
    /**
     * 职务详情
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "dutydetails" ,method = RequestMethod.GET)
    public String getDutyDetails(@RequestParam(required = false) String dutyname){
        return userService.selectDutyD(dutyname);
    }

    /**
     * 新建职务
     * @param dutyname
     * @param remark
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "saveduty" ,method = RequestMethod.GET)
    public String saveDuty(@RequestParam(required = false) String dutyname,
                           @RequestParam(required = false) String remark){
        return userService.saveDuty(dutyname,remark);
    }

    /**
     * 删除职务和权限
     */
    @ResponseBody
    @RequestMapping(value = "deleteduty",method = RequestMethod.GET)
    public String deleteDuty(@RequestParam(required = false) Integer dutyid){
        return userService.deleteDuty(dutyid);
    }

    /**
     * 修改添加权限
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateauthority",method = RequestMethod.GET)
    public String updateDuty(@RequestParam(required = false) Integer dutyid,
                             @RequestParam(required = false) Integer[] authorityids){
       List<UserDutyAuthority> userDutyAuthorities = new ArrayList<UserDutyAuthority>();
      if(authorityids !=null && authorityids[0] !=-1){
          for(int i = 0; i < authorityids.length; i++){
              UserDutyAuthority userDutyAuthority=new UserDutyAuthority();
              userDutyAuthority.setDutyid(dutyid);
              userDutyAuthority.setAuthorityid(authorityids[i]);
              userDutyAuthorities.add(userDutyAuthority);
          }
      }else {
          UserDutyAuthority userDutyAuthority=new UserDutyAuthority();
          userDutyAuthority.setDutyid(dutyid);
          userDutyAuthority.setAuthorityid(-1);
          userDutyAuthorities.add(userDutyAuthority);
      }

        return userService.saveDutyAuthoritiesList(userDutyAuthorities);
    }


}
