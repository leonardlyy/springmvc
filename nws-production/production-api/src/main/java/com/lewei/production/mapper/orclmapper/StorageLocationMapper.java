package com.lewei.production.mapper.orclmapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.Condition;
import com.lewei.production.model.Other;

import java.util.List;

@MyBatis
public interface StorageLocationMapper {
    List<Other> selectByPrimaryKey(Condition key);

    List<Other> selectDefault(Condition key);

}