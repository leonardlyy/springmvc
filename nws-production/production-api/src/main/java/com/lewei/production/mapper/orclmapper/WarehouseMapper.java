package com.lewei.production.mapper.orclmapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.Condition;
import com.lewei.production.model.Other;

import java.util.List;

@MyBatis
public interface WarehouseMapper {
    List<Other> selectByPrimaryKey(Condition condition);
    List<Other> selectDefault(Condition condition);

}