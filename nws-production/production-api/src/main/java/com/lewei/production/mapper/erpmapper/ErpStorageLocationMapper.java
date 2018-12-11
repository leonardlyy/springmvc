package com.lewei.production.mapper.erpmapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.Condition;
import com.lewei.production.model.Other;

import java.util.List;

@MyBatis
public interface ErpStorageLocationMapper {
    List<Other> selectByPrimaryKey(Condition key);

    List<Other> selectDefault(Condition key);

}