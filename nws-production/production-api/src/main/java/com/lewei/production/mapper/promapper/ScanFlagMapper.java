package com.lewei.production.mapper.promapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.ScanFlag;
@MyBatis
public interface ScanFlagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScanFlag record);

    int insertSelective(ScanFlag record);

    ScanFlag selectByPrimaryKey(Integer id);

    ScanFlag selectScanOrno(ScanFlag scanFlag);

    int updateByPrimaryKeySelective(ScanFlag record);

    int updateByPrimaryKey(ScanFlag record);
}