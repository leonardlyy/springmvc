package com.lewei.production.mapper.orclmapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.ErpPurchaseOrdeLine;
import com.lewei.production.model.ErpWarehouseReceipt;
import com.lewei.production.model.Condition;

import java.util.List;

/**
 * 采购订单收货 Created by Simon on 2018/11/16.
 */
@MyBatis
public interface PurchaseReceiptMapper {

	/**
	 * 列表
	 * 
	 * @return
	 */
	List<ErpPurchaseOrdeLine> getItemListByOrderNO(Condition key);
	
    int insretWarehouseReceipt(ErpWarehouseReceipt record);
}
