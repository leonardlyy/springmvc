package com.lewei.production.service;

import com.lewei.production.model.OnlineUser;
import com.lewei.production.model.User;
import com.lewei.production.util.Code;
import com.lewei.production.vo.OnlineUserVO;
import com.lewei.production.vo.ResultJson;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 马路遥(229010812 @ qq.com)
 * @version V1.0
 * @Title: nws-production
 * @Package com.lewei.production.service
 * @Description: TODO
 * @Date 2017-12-18 17:34
 */
@Service
public class CommonService {


    public static void setOnlineUser(Integer userId,String username,String name) {
        List<OnlineUser>  users = (List<OnlineUser>) VerifyCodeCache.INSTANCE.getCaches().get("users");
        OnlineUser u= new OnlineUser();//登录用户信息对象
        u.setId(userId);
        u.setUsername(username);
        u.setName(name);
        u.setLastvisitdate(new Date());
        if(users!=null&&users.size()>0){
            Iterator<OnlineUser> userIter = users.iterator();
            boolean newUser = true;
            while (userIter.hasNext()) {
                OnlineUser user = userIter.next();
                if (user.getId()==userId){
                    userIter.remove();
                }
                else if(user.getLastvisitdate().getTime()+1000*60*1<=new Date().getTime())
                    userIter.remove();//这里要使用Iterator的remove方法移除当前对象，如果使用List的remove方法，则同样会出现ConcurrentModificationException
                
            }
            users.add(u);
        }else{
            users = new ArrayList<OnlineUser>();
            users.add(u);
        }
        VerifyCodeCache.INSTANCE.getCaches().put("users",users);
        String uss ="";
        for(OnlineUser us:users){
            uss=uss + us.getId()+"---";
        }
        System.out.println(uss);

    }

    /**
     * 在线用户列表
     * @return
     */
    public String getOnlineUserList(){
        List<OnlineUser>  users = (List<OnlineUser>) VerifyCodeCache.INSTANCE.getCaches().get("users");
        if(users.size()>0){
            List<OnlineUserVO> onlineUserVO = OnlineUserVO.getList(users);
            return ResultJson.toJson(Code.SUCCESS,"查询成功",onlineUserVO);
        }
        return ResultJson.toJson(Code.ERROR,"查询失败", Code.NULL_STR);
    }

    /**
     * 在线用户单例
     */
    public enum VerifyCodeCache
    {

        INSTANCE;

        private Map<String, Object> caches = new HashMap<String, Object>();

        public Map<String, Object> getCaches()
        {
            return caches;
        }

    }


}
