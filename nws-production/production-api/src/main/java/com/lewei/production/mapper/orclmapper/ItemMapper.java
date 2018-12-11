package com.lewei.production.mapper.orclmapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.Condition;
import com.lewei.production.model.Other;

import java.util.List;

@MyBatis
public interface ItemMapper {

    List<Other> selectByPrimaryKey(Condition key);

    List<Other> selectDsca(Condition key);

    List<Other> selectPrint(Condition key);

}