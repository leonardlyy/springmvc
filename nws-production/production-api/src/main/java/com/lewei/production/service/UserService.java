package com.lewei.production.service;

import com.lewei.production.mapper.promapper.UserDutyAuthorityMapper;
import com.lewei.production.mapper.promapper.UserDutyMapper;
import com.lewei.production.mapper.promapper.UserMapper;
import com.lewei.production.model.Authority;
import com.lewei.production.model.User;
import com.lewei.production.model.UserDuty;
import com.lewei.production.model.UserDutyAuthority;
import com.lewei.production.util.Code;
import com.lewei.production.util.LoggerUtil;
import com.lewei.production.util.Pagination;
import com.lewei.production.vo.ResultJson;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 22901 on 2017/3/15.
 */
@Service
public class UserService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserDutyMapper userDutyMapper;
    @Resource
    private UserDutyAuthorityMapper userDutyAuthorityMapper;


    /**
     * 用户列表显示
     * @return
     */
    public String userList(String username,String companyNo,String warehouseNo,String name,String dutyid,Integer pageNo,Integer pageSize){
        Pagination pagination = new Pagination();
        Map<String, Object> params = new HashedMap();
        params.put("name",name);
        params.put("dutyid",dutyid);
        params.put("username",username);
        params.put("companyNo",companyNo);
        params.put("warehouseNo",warehouseNo);
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);
        pagination.setParams(params);
        List<User> users =  userMapper.selectAllPage(pagination);
        List<User> users1 = new ArrayList<User>();
        for(User u:users){
            if(u.getDutyid()!=null){
                u.setUserDuty(userDutyMapper.selectByPrimaryKey(u.getDutyid()));
            }

            users1.add(u);
        }
        pagination.setResults(users1);
        return ResultJson.toJson(Code.SUCCESS,"用户列表",pagination);
    }

    /**
     * 用户详情
     * @param username
     * @return
     */
    public String userDetails(String username){
        User user =  userMapper.selectUserName(username);
        if(user!=null){
            user.setUserDuty(userDutyMapper.selectByPrimaryKey(user.getDutyid()));
        }
        return ResultJson.toJson(Code.SUCCESS,"用户详情",user);
    }
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public String login(String username,String password){
        User u=new User();
        u.setUsername(username);
        u.setPassword(password);
        User user=userMapper.login(u);
        if(user != null){
            user.setLastvisitdate(new Date());
            userMapper.updateByPrimaryKeySelective(user);
            UserDuty userDuty= userDutyMapper.selectByPrimaryKey(user.getDutyid());
            List<Authority> authoritys= userDutyAuthorityMapper.selectDuty(userDuty.getId());
            Map<String, Object> jsonMap = new HashMap<String, Object>();
            jsonMap.put("user",user);
            jsonMap.put("duty",userDuty);
            jsonMap.put("authority",authoritys);
            return ResultJson.toJson(Code.SUCCESS,"登录成功",jsonMap);
        }else{
            return ResultJson.toJson(Code.ERROR,"用户名或密码错误！","");
        }


    }



    /**
     * 获取职务列表
     * @return
     */
    public String selectDutyList() {
        List<UserDuty> userDuties = userDutyMapper.selectAll();
        return ResultJson.toJson(Code.SUCCESS, "职务列表", userDuties);
    }

    /**
     * 获取职务详情
     * @return
     */
    public String selectDutyD(String dutyname) {
        UserDuty userDuty = userDutyMapper.selectDutyName(dutyname);
        List<Authority> authoritys= userDutyAuthorityMapper.selectDuty(userDuty.getId());
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("duty",userDuty);
        jsonMap.put("authority",authoritys);
        return ResultJson.toJson(Code.SUCCESS, "职务列表", jsonMap);
    }

    /**
     * 添加职务，成功返回职务列表
     * @return
     */
    public String saveDuty(String dutyname,String remark){
        UserDuty userDuty = new UserDuty();
        userDuty.setDutyname(dutyname);
        userDuty.setRemark(remark);
        try{
            if(userDutyMapper.selectDutyName(dutyname)==null) {
                userDutyMapper.insertSelective(userDuty);
                List<UserDuty> userDuties = userDutyMapper.selectAll();
                return ResultJson.toJson(Code.SUCCESS, "添加成功", userDuties);
            }else {
                return ResultJson.toJson(Code.SUCCESS, "当前职务已存在", dutyname);
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResultJson.toJson(Code.ERROR, "添加失败", "");
        }
    }

    /**
     * 删除职务和权限
     * @return
     */
    public String deleteDuty(Integer dutyid){
        List<User> u=userMapper.selectDutyId(dutyid);
        if(u==null||u.size()<1){
            userDutyMapper.deleteByPrimaryKey(dutyid);
            userDutyAuthorityMapper.deleteByPrimaryKey(dutyid);
            return ResultJson.toJson(Code.SUCCESS, "删除成功", "");
        }else {
            return ResultJson.toJson(Code.SUCCESS, "有用户正在使用该职务，请修改后删除", u);
        }

    }

    /**
     * 修改权限
     * @param userDutyAuthorities
     * @return
     */
    public String saveDutyAuthoritiesList(List<UserDutyAuthority> userDutyAuthorities){
        try{
            if(userDutyAuthorityMapper.selectDuty(userDutyAuthorities.get(0).getDutyid())!=null){
                userDutyAuthorityMapper.deleteByPrimaryKey(userDutyAuthorities.get(0).getDutyid());
            }
            if(!userDutyAuthorities.get(0).getAuthorityid().equals(-1)){
                userDutyAuthorityMapper.savelist(userDutyAuthorities);
            }
            return ResultJson.toJson(Code.SUCCESS, "成功", "");
        }catch (Exception e){
            e.printStackTrace();
            return ResultJson.toJson(Code.ERROR, "失败", "");
        }

    }

    /**
     * 用户资料修改、删除用户、密码修改
     * @param username
     * @param password
     * @param dutyid
     * @param defaultprinter
     * @param len
     * @return
     */
    public String updateUser(Integer userid,String name,String username,String password,String tel,String email,Integer dutyid,String defaultprinter,String len,String status,String companyNo,String warehouseNo,String warehouseNo1,String seri,String  seriN,String otyp){
        User u = new User();
        u.setId(userid);
        u.setUsername(username);
        u.setPassword(password);
        u.setDutyid(dutyid);
        u.setDefaultprinter(defaultprinter);
        u.setLen(len);
        u.setStatus(status);
        u.setName(name);
        u.setCompanyNo(companyNo);
        u.setWarehouseNo(warehouseNo);
        u.setWarehouseNo1(warehouseNo1);
        u.setSeri(seri);
        u.setSeriN(seriN);
        u.setOtyp(otyp);
        u.setEmail(email);
        u.setTel(tel);
        try{
            userMapper.updateByPrimaryKeySelective(u);
            return ResultJson.toJson(Code.SUCCESS, "成功", "");
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("错误信息{}", LoggerUtil.getException(e));
            return ResultJson.toJson(Code.ERROR, "失败", "");

        }
    }

    /**
     * 添加用户
     * @param name
     * @param username
     * @param password
     * @param dutyid
     * @param defaultprinter
     * @param len
     * @param status
     * @return
     */
    public String saveUser(String name,String username,String password,String tel,String email,Integer dutyid,String defaultprinter,String len,String status,String warehouseNo,String warehouseNo1,String seri,String seriN,String otyp,String companyNo){
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        u.setDutyid(dutyid);
        u.setDefaultprinter(defaultprinter);
        u.setLen(len);
        u.setStatus(status);
        u.setName(name);
        u.setEmail(email);
        u.setTel(tel);
        u.setCompanyNo(companyNo);
        u.setSeri(seri);
        u.setSeriN(seriN);
        u.setOtyp(otyp);
        u.setWarehouseNo(warehouseNo);
        u.setWarehouseNo1(warehouseNo1);
        try{
            User user=userMapper.selectUserName(username);
            if(user == null ||user.equals("")){
                userMapper.insertSelective(u);
                return ResultJson.toJson(Code.SUCCESS, "成功", "");
            }else {
                return ResultJson.toJson(Code.SUCCESS, "已存在该用户", "");
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResultJson.toJson(Code.ERROR, "失败", "");
        }

    }
}
