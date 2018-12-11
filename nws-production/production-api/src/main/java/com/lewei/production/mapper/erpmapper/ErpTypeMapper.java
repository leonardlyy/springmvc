package com.lewei.production.mapper.erpmapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.Condition;
import com.lewei.production.model.Other;

import java.util.List;

@MyBatis
public interface ErpTypeMapper {
    List<Other> selectByPrimaryKey(Condition key);
}