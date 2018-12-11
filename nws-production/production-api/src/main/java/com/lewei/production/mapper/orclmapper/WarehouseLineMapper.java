package com.lewei.production.mapper.orclmapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.Condition;
import com.lewei.production.model.ErpWarehouseLine;
import com.lewei.production.model.ErpWarehouseSFCLine;
import java.util.List;

/**
 * Created by 22901 on 2017/3/13.
 */
@MyBatis
public interface WarehouseLineMapper {
    List<ErpWarehouseLine> selectByPrimaryKey(Condition condition);

    List<ErpWarehouseLine> selectMinOset(Condition condition);
    
    int insretWarehouseLine(ErpWarehouseSFCLine record);

    ErpWarehouseLine selectMaxOset(Condition condition);
}
