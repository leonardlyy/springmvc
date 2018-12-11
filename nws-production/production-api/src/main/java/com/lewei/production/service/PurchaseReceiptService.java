package com.lewei.production.service;

import java.io.Console;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lewei.production.mapper.orclmapper.*;
import com.lewei.production.model.*;
import com.lewei.production.util.*;

import com.lewei.production.vo.ResultJson;

/**
 * 采购收货服务层 Created by simon on 2018/11/23.
 */
@Service
public class PurchaseReceiptService {

	@Resource
	private PurchaseReceiptMapper PurchaseReceiptMapper;
	@Resource
	private WarehouseLocaMapper WarehouseLocaMapper;

	/**
	 * 采购订单收货行
	 *
	 * @return
	 */
	public String getItemListByOrderNO(String orno, String companyNo) {
		Condition condition = new Condition();
		
		condition.setIf1(orno);
		condition.setCompanyNo(companyNo);
		List<ErpPurchaseOrdeLine> purchaseorder = PurchaseReceiptMapper.getItemListByOrderNO(condition);
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		System.out.println(purchaseorder);
		jsonmap.put("PurchaseOrder", purchaseorder);
		return ResultJson.toJson(1, Code.FIND_SUCC_MSG, jsonmap);
	}

	/**
	 * 检查收货库位
	 *
	 * @return
	 */
	public String checkWarehouseLoca(String cwar, String loca, String companyNo) {
		Condition condition = new Condition();
		condition.setIf1(cwar);
		condition.setIf2(loca);
		condition.setCompanyNo(companyNo);
		
		List<Other> warehouseloca = WarehouseLocaMapper.checkWarehouseLoca(condition);
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		jsonmap.put("Localist", warehouseloca);
		return ResultJson.toJson(1, Code.FIND_SUCC_MSG, jsonmap);
	}
	
	/**
	 * 返物料固定库位及批次信息
	 *
	 * @return
	 */
	public String getWarehouseLocaAndClot(String cwar, String item, String companyNo) {
		Condition condition = new Condition();
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		condition.setIf1(cwar);
		condition.setIf2(item);		
		condition.setCompanyNo(companyNo);
		System.out.println(condition); 
		List<Other> warehouseloca = WarehouseLocaMapper.getWarehouseLoca(condition);
		jsonmap.put("Localist", warehouseloca);
		Condition condition1 = new Condition();		 
		condition1.setCompanyNo(companyNo);
		List<Other> warehousebatch = WarehouseLocaMapper.getWarehouseBatch(condition1);
 		jsonmap.put("Batchlist", warehousebatch); 		
		return ResultJson.toJson(1, Code.FIND_SUCC_MSG, jsonmap);
	}

	/**
	 * 仓库收货
	 *
	 * @return
	 */
	public String insretWarehouseReceipt(String orno, String pono, String ponb, String item, String qoor, String cwar,
			String loca, String clot, String refa, Integer userid, String companyNo) {

		if (refa == null || refa.equals("")) {
			refa = " ";
		}
		if (clot == null || clot.equals("")) {
			clot = " ";
		}
		if (loca == null || loca.equals("")) {
			loca = " ";
		}
		// ---数据库插入方式------//
		ErpWarehouseReceipt ErpWarehouseReceipt = new ErpWarehouseReceipt();
		ErpWarehouseReceipt.setCompanyNo(companyNo);
		ErpWarehouseReceipt.setOrno(orno);
		ErpWarehouseReceipt.setPono(Integer.valueOf(pono));
		ErpWarehouseReceipt.setPonb(Integer.valueOf(ponb));
		ErpWarehouseReceipt.setOdat(new Date());
		ErpWarehouseReceipt.setItem(item);
		ErpWarehouseReceipt.setQoor(Integer.valueOf(qoor));
		ErpWarehouseReceipt.setCwar(cwar);
		ErpWarehouseReceipt.setLoca(loca);
		ErpWarehouseReceipt.setClot(clot  );
		ErpWarehouseReceipt.setRefa(refa );
		ErpWarehouseReceipt.setTrdt(new Date());
		ErpWarehouseReceipt.setSeqn(0);
		ErpWarehouseReceipt.setStat(0);
		ErpWarehouseReceipt.setError(" ");
		ErpWarehouseReceipt.setWhno(" ");
		ErpWarehouseReceipt.setPnoo(0);
		ErpWarehouseReceipt.setRefc(0);
		ErpWarehouseReceipt.setRefu(0);
		System.out.println(ErpWarehouseReceipt);
		PurchaseReceiptMapper.insretWarehouseReceipt(ErpWarehouseReceipt);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("resStatus", "1");
		
	//返回最新的批号
		
		jsonMap.put("resMsg", " ");
		jsonMap.put("resLine", 1);
		
		return ResultJson.toJson(1, Code.FIND_SUCC_MSG, jsonMap);
	}

}
