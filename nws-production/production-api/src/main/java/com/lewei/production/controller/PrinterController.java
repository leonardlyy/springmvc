package com.lewei.production.controller;

import com.lewei.production.service.PrinterService;
import com.lewei.production.service.WarehouseReceiptService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 打印设置
 * Created by 22901 on 2017/3/13.
 */
@Controller
@RequestMapping(value="printer")
public class PrinterController {
    @Resource
    private PrinterService printerService;
    @Resource
    private WarehouseReceiptService warehouseReceiptService;


    /**
     * 获取仓库
     * @return
     */
    @ResponseBody
    @RequestMapping(value="warehouse",method = RequestMethod.GET)
    public String getWarehouse(@RequestParam(required = false) String companyNo,
                               @RequestParam(required = false) String item,
                               @RequestParam(required = false) String eunt){
        return warehouseReceiptService.getErpWarehouse(companyNo,eunt,item);
    }

    /**
     * 获取库位
     * @param cwar
     * @return
     */
    @ResponseBody
    @RequestMapping(value="storagelocation",method = RequestMethod.GET)
    public String getStorageLocation( @RequestParam(required = false) String cwar,
                                      @RequestParam(required = false) String clot,
                                      @RequestParam(required = false) String item,
                                      @RequestParam(required = false) String companyNo){
        return warehouseReceiptService.getErpStorageLocation(cwar,clot,item,null,companyNo);}

    /**
     * 获取物料号
     * @param cwar
     * @return
     */
    @ResponseBody
    @RequestMapping(value="item",method = RequestMethod.GET)
    public String getItem(@RequestParam(required = false) String cwar,
                          @RequestParam(required = false) String companyNo){
        return printerService.getErpItem(cwar,companyNo);}
    /**
     * 获取打印机
     * @return
     */
    @ResponseBody
    @RequestMapping(value="list",method = RequestMethod.GET)
    public String getPrinterList(){
        return printerService.getPrinterList();
    }

    /**
     * 打印物料
     * @param cwar
     * @param item
     * @param itemDsca
     * @param num
     * @param userid
     * @param printername
     * @return
     */
    @ResponseBody
    @RequestMapping(value="printitem",method = RequestMethod.GET)
    public String printItem(@RequestParam(required = false) String cwar,
                            @RequestParam(required = false) String item,
                            @RequestParam(required = false) String itemDsca,
                            @RequestParam(required = false) Integer num,
                            @RequestParam(required = false) Integer userid,
                            @RequestParam(required = false) String printername){
       return printerService.printItem(cwar,item,itemDsca,num,userid,printername);
    }
    /**
     * 打印批次
     * @param cwar
     * @param item
     * @param num
     * @param userid
     * @param printername
     * @return
     */
    @ResponseBody
    @RequestMapping(value="printclot",method = RequestMethod.GET)
    public String printClot(@RequestParam(required = false) String cwar,
                            @RequestParam(required = false) String item,
                            @RequestParam(required = false) String dsca,
                            @RequestParam(required = false) String clot,
                            @RequestParam(required = false) Integer num,
                            @RequestParam(required = false) String user,
                            @RequestParam(required = false) Integer userid,
                            @RequestParam(required = false) String printername,
                            @RequestParam(required = false) String companyNo,
                            @RequestParam(required = false) String bpidname
                            ){
        return printerService.printClot(cwar,item,dsca,clot,num,user,userid,printername,bpidname);
    }
     /**
     * 工作中心打印
     * @param stco
     * @param dsca
     * @param num
     * @param userid
     * @param printername
     * @return
     */
    @ResponseBody
    @RequestMapping(value="printstco",method = RequestMethod.GET)
    public String printStco(@RequestParam(required = false) String stco,
                            @RequestParam(required = false) String dsca,
                            @RequestParam(required = false) Integer num,
                            @RequestParam(required = false) Integer userid,
                            @RequestParam(required = false) String printername){
        return printerService.printStco(stco,dsca,num,userid,printername);
    }

    /**
     * 打印库位
     * @param cwar
     * @param loca
     * @param locaDsca
     * @param num
     * @param userid
     * @param printername
     * @return
     */
    @ResponseBody
    @RequestMapping(value="printlocation",method = RequestMethod.GET)
    public String printLocation(@RequestParam(required = false) String cwar,
                            @RequestParam(required = false) String loca,
                            @RequestParam(required = false) String locaDsca,
                            @RequestParam(required = false) Integer num,
                            @RequestParam(required = false) Integer userid,
                            @RequestParam(required = false) String printername) {
        return printerService.printLocation(cwar, loca, locaDsca, num, userid, printername);
    }

    /**
     * 标签位置列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value="labellist",method = RequestMethod.GET)
    public String labelLocationList(){
        return  printerService.selectLabelLocation();
    }

    /**
     * 标签位置修改
     * @return
     */
    @ResponseBody
    @RequestMapping(value="updatelabel",method = RequestMethod.GET)
    public String updateLabelLocation(@RequestParam(required = false) Integer id,
                                      @RequestParam(required = false) Integer left,
                                      @RequestParam(required = false) Integer top,
                                      @RequestParam(required = false) Integer userid){
        return printerService.updateLabelLocation(id,left,top,userid);
    }
}
