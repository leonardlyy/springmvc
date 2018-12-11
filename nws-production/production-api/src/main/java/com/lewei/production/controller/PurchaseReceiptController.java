package com.lewei.production.controller;

import com.lewei.production.service.PurchaseReceiptService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 采购收货控制层 Created by simon on 2018/11/23.
 */
@Controller
@RequestMapping("entering")
public class PurchaseReceiptController {
	@Resource
	private PurchaseReceiptService PurchaseReceiptService;

	/**
	 * 采购订单行
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getPurchaseOrder", method = RequestMethod.GET)
	public String getItemListByOrderNO(@RequestParam(required = false) String orno,
			@RequestParam(required = false) String companyNo) {
		return PurchaseReceiptService.getItemListByOrderNO(orno, companyNo);
	}

	/**
	 * 检验仓库库位
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkWarehouseLoca", method = RequestMethod.GET)
	public String checkWarehouseLoca(@RequestParam(required = false) String cwar,
			@RequestParam(required = false) String loca, @RequestParam(required = false) String companyNo) {
		return PurchaseReceiptService.checkWarehouseLoca(cwar, loca, companyNo);
	}
	
	/**
	 * 返物料固定库位及批次信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getWarehouseLocaAndClot", method = RequestMethod.GET)
	public String getWarehouseLocaAndClot(@RequestParam(required = false) String cwar,
										  @RequestParam(required = false) String item,
										  @RequestParam(required = false) String companyNo) {
		return PurchaseReceiptService.getWarehouseLocaAndClot(cwar, item, companyNo);
	}

	/**
	 * 采购订单行
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "createReceipt", method = RequestMethod.GET)
	public String insretWarehouseReceipt(@RequestParam(required = false) String orno,
			@RequestParam(required = false) String pono, @RequestParam(required = false) String ponb,
			@RequestParam(required = false) String item, @RequestParam(required = false) String qoor,
			@RequestParam(required = false) String cwar, @RequestParam(required = false) String loca,
			@RequestParam(required = false) String clot, @RequestParam(required = false) String refa,
			@RequestParam(required = false) Integer userid, @RequestParam(required = false) String companyNo) {
		return PurchaseReceiptService.insretWarehouseReceipt(orno, pono, ponb, item, qoor, cwar, loca, clot, refa,
				userid, companyNo);
	}

}
