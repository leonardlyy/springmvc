package com.lewei.production.mapper.erpmapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.Condition;
import com.lewei.production.model.Other;

import java.util.List;

@MyBatis
public interface ErpWarehouseMapper {
    List<Other> selectByPrimaryKey(Condition condition);
    List<Other> selectDefault(Condition condition);

}