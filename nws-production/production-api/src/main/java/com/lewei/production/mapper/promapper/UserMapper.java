package com.lewei.production.mapper.promapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.User;
import com.lewei.production.util.Pagination;

import java.util.List;

@MyBatis
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    List<User> selectDutyId(Integer id);

    User selectUserName(String username);

    List<User> selectAllPage(Pagination pagination);

     User login(User u);

    int insertSelective(User record);

    int updateByPrimaryKeySelective(User record);

}