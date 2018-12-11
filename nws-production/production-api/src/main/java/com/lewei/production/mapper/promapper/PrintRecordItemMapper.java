package com.lewei.production.mapper.promapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.PrintRecordItem;
@MyBatis
public interface PrintRecordItemMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(PrintRecordItem record);

    PrintRecordItem selectByPrimaryKey(Integer id);


}