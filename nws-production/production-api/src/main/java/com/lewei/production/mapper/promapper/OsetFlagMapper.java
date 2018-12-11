package com.lewei.production.mapper.promapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.OsetFlag;
import com.lewei.production.model.ScanFlag;

@MyBatis
public interface OsetFlagMapper {
    int insertSelective(OsetFlag record);

    OsetFlag selectByPrimaryKey(Integer id);

    OsetFlag selectScanOset(OsetFlag osetFlag);

    int updateByPrimaryKeySelective(OsetFlag record);
}