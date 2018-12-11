package com.lewei.production.mapper.promapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.PrintRecordLocation;
@MyBatis
public interface PrintRecordLocationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PrintRecordLocation record);

    PrintRecordLocation selectByPrimaryKey(Integer id);

}