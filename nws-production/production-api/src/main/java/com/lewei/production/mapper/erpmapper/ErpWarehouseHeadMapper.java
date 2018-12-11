package com.lewei.production.mapper.erpmapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.Condition;
import com.lewei.production.model.ErpWarehouseHead;
import com.lewei.production.util.Pagination;

import java.util.List;

/**
 * 仓单头
 * Created by 22901 on 2017/3/13.
 */
@MyBatis
public interface ErpWarehouseHeadMapper {
    /**
     * 详细信息
     * @return
     */
    List<ErpWarehouseHead> selectByPrimaryKey(Condition condition);

    /**
     * 列表
     * @return
     */
    List<ErpWarehouseHead> selectAllPage(Pagination pagination);


}
