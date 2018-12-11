package com.lewei.production.mapper.orclmapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.Other;
import com.lewei.production.model.Condition;

import java.util.List;

/**
 *仓库库位 Created by Simon on 2018/11/16.
 */
@MyBatis
public interface WarehouseLocaMapper {

	/**
	 * 列表
	 * 
	 * @return
	 */
	List<Other> checkWarehouseLoca(Condition key);
	List<Other> getWarehouseLoca(Condition key); 
	List<Other> getWarehouseBatch(Condition key);
}
