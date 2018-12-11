package com.lewei.production.mapper.promapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.PrintLabelLocation;

import java.util.List;

@MyBatis
public interface PrintLabelLocationMapper {

    PrintLabelLocation selectByPrimaryKey(Integer id);

    List<PrintLabelLocation> selectAll();

    int updateByPrimaryKeySelective(PrintLabelLocation record);

}