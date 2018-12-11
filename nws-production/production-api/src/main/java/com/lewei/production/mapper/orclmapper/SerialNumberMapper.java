package com.lewei.production.mapper.orclmapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.Condition;
import com.lewei.production.model.Other;

import java.util.List;

@MyBatis
public interface SerialNumberMapper {
    List<Other> selectByPrimaryKey(Condition key);
}