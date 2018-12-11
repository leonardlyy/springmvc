package com.lewei.production.mapper.erpmapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.Condition;
import com.lewei.production.model.ErpWarehouseLine;

import java.util.List;

/**
 * Created by 22901 on 2017/3/13.
 */
@MyBatis
public interface ErpWarehouseLineMapper {
    List<ErpWarehouseLine> selectByPrimaryKey(Condition condition);
}
