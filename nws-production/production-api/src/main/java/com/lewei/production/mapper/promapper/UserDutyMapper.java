package com.lewei.production.mapper.promapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.UserDuty;

import java.util.List;

@MyBatis
public interface UserDutyMapper {
    int deleteByPrimaryKey(Integer id);

    List<UserDuty> selectAll();

    int insert(UserDuty record);

    int insertSelective(UserDuty record);

    UserDuty selectByPrimaryKey(Integer id);

    UserDuty selectDutyName(String name);

    int updateByPrimaryKeySelective(UserDuty record);

}