package com.lewei.production.mapper.promapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.Authority;
import com.lewei.production.model.UserDutyAuthority;

import java.util.List;

@MyBatis
public interface UserDutyAuthorityMapper {
    int deleteByPrimaryKey(Integer dutyid);
    int savelist(List<UserDutyAuthority> userDutyAuthorities);

    List<Authority> selectDuty(Integer dutyid);

    int insert(UserDutyAuthority record);

    int insertSelective(UserDutyAuthority record);

    UserDutyAuthority selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDutyAuthority record);

    int updateByPrimaryKey(UserDutyAuthority record);
}