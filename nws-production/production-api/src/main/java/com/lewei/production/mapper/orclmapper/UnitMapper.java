package com.lewei.production.mapper.orclmapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.Condition;
import com.lewei.production.model.Other;

@MyBatis
public interface UnitMapper {
    Other selectByPrimaryKey(Condition condition);
}