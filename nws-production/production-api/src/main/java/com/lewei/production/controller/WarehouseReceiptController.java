package com.lewei.production.controller;

import com.lewei.production.service.WarehouseReceiptService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 仓单控制层
 * Created by 22901 on 2017/3/9.
 */
@Controller
@RequestMapping("entering")
public class WarehouseReceiptController {
    @Resource
    private WarehouseReceiptService warehouseReceiptService;



    @ResponseBody
    @RequestMapping(value="headlist",method = RequestMethod.GET)
    public String getWarehouseList(@RequestParam(required = false) String orno,
                                   @RequestParam(required = false) String companyNo,
                                   @RequestParam(required = false) Integer pageNo,
                                   @RequestParam(required = false) Integer pageSize){
        return warehouseReceiptService.getErpWarehouseHeadList(orno,companyNo,pageNo,pageSize);
    }

    @ResponseBody
    @RequestMapping(value="details",method = RequestMethod.GET)
    public String getWarehouseDetails(@RequestParam(required = false) String orno,
                                      @RequestParam(required = false) Integer oset,
                                      @RequestParam(required = false) String companyNo){
        return warehouseReceiptService.getErpWarehouseD(orno,oset,companyNo);
    }

    /**
     * 获取企业单元
     * @return
     */
    @ResponseBody
    @RequestMapping(value="eunt",method = RequestMethod.GET)
    public String getEunt(@RequestParam(required = false) String companyNo){
        return warehouseReceiptService.getEunt(companyNo);
    }
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
                                      @RequestParam(required = false) String isClotNull,//1判断clot  其他不判断
                                      @RequestParam(required = false) String companyNo){
        return warehouseReceiptService.getErpStorageLocation(cwar,clot,item,isClotNull,companyNo);}
    /**
     * 获取仓单类型
     * 2 发料
     * @return
     */
    @ResponseBody
    @RequestMapping(value="type",method = RequestMethod.GET)
    public String getType( @RequestParam(required = false) String ittp,
                           @RequestParam(required = false) String companyNo){
        return warehouseReceiptService.getErpType(ittp,companyNo);}
    /**
     * 获取工作中心
     * @return
     */
    @ResponseBody
    @RequestMapping(value="workcenter",method = RequestMethod.GET)
    public String getWorkCenter(@RequestParam(required = false) String companyNo,
                                @RequestParam(required = false) String eunt){
        return warehouseReceiptService.getWorkCenter(companyNo,eunt);}
    /**
     * 获取系列
     * @return
     */
    @ResponseBody
    @RequestMapping(value="series",method = RequestMethod.GET)
    public String getSeries(@RequestParam(required = false) String companyNo){
        String nrgr="600";
        return warehouseReceiptService.getErpSeries(nrgr,companyNo);}
    /**
     * 获取序列号
     * @param item
     * @param cwar
     * @return
     */
    @ResponseBody
    @RequestMapping(value="serialnumber",method = RequestMethod.GET)
    public String getSerialNumber(@RequestParam(required = false) String item,
                                  @RequestParam(required = false) String cwar,
                                  @RequestParam(required = false) String companyNo){
        return warehouseReceiptService.getErpSerialNumber(item,cwar,companyNo);}
    /**
     * 获取批次
     * @param item
     * @param cwar
     * @return
     */
    @ResponseBody
    @RequestMapping(value="batch",method = RequestMethod.GET)
    public String getBatch(@RequestParam(required = false) String cwar,
                           @RequestParam(required = false) String loca,
                           @RequestParam(required = false) String item,
                           @RequestParam(required = false) String companyNo){
        return warehouseReceiptService.getErpBatch(cwar,loca,item,companyNo);}
    /**
     * 获取物料号
     * @param cwar
     * @return
     */
    @ResponseBody
    @RequestMapping(value="item",method = RequestMethod.GET)
    public String getItem(@RequestParam(required = false) String cwar,
                          @RequestParam(required = false) String companyNo){
        return warehouseReceiptService.getErpItem(cwar,companyNo);}
    /**
     * 获取 物料说明  单位   序列号
     * @param item
     * @param cwar
     * @return
     */
    @ResponseBody
    @RequestMapping(value="itemall",method = RequestMethod.GET)
    public String getItemAll(@RequestParam(required = false) String item,
                             @RequestParam(required = false) String  cwar,
                             @RequestParam(required = false) String companyNo){
        return warehouseReceiptService.getErpItemAll(cwar,item,companyNo);}
    @ResponseBody
    @RequestMapping(value="createall",method = RequestMethod.GET)
    public String createAll(@RequestParam(required = false) String headLine){
        return warehouseReceiptService.CreateAll(headLine);
    }

    /**
     * 库存数量
     * @param cwar
     * @param loca
     * @param item
     * @param clot
     * @param companyNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value="num",method = RequestMethod.GET)
    public String getStrNum(@RequestParam(required = false) String cwar,
                            @RequestParam(required = false) String loca,
                            @RequestParam(required = false) String item,
                            @RequestParam(required = false) String clot,
                            @RequestParam(required = false) String companyNo){
      return   warehouseReceiptService.getStorageNum(cwar,loca,item,clot,companyNo);
    }

    /**
     * 获取订单号
     * @param sfco
     * @param stco
     * @param companyNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value="scan-orno",method = RequestMethod.GET)
    public String getScanOrno(@RequestParam(required = false) String sfco,
                            @RequestParam(required = false) String stco,
                            @RequestParam(required = false) String seri,
                            @RequestParam(required = false) String companyNo){
        return   warehouseReceiptService.getScanOrnn(sfco,stco,seri,companyNo);
    }
    /**
     * 创建仓单头
     * @param oorg
     * @param ittp
     * @param otyp
     * @param seri
     * @param sfco
     * @param sflo
     * @param stco
     * @return
     */
    @ResponseBody
    @RequestMapping(value="create",method = RequestMethod.GET)
    public String getCreate(@RequestParam(required = false) String oorg,
                             @RequestParam(required = false) String ittp,
                             @RequestParam(required = false) String otyp,
                             @RequestParam(required = false) String seri,
                             @RequestParam(required = false) String sfco,
                             @RequestParam(required = false) String sflo,
                             @RequestParam(required = false) String stco,
                             @RequestParam(required = false) Integer userid,
                             @RequestParam(required = false)  Integer companyNo){
        return warehouseReceiptService.Create(oorg,ittp,null, Long.valueOf(1),otyp,seri,sfco,sflo,stco,userid,companyNo);}

    /**
     * 删除仓单
     * @param oorg
     * @param orno
     * @param oset
     * @return
     */
    @ResponseBody
    @RequestMapping(value="delete",method = RequestMethod.GET)
    public String Delete(@RequestParam(required = false) String oorg,
                             @RequestParam(required = false) String orno,
                             @RequestParam(required = false) Integer oset,
                         @RequestParam(required = false)  Integer userid,
                         @RequestParam(required = false)  Integer companyNo){
        return warehouseReceiptService.Delete(oorg,orno,oset,userid,companyNo);
    }

    /**
     * 创建仓单
     * @param oorg
     * @param ittp
     * @param otyp
     * @param seri
     * @param sfco
     * @param sflo
     * @param stco
     * @param item
     * @param qoro
     * @param orun
     * @param lsel
     * @param clot
     * @param serl
     * @param userid
     * @param companyNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value="create-warehouse",method = RequestMethod.GET)
    public String CreateWarehouse(@RequestParam(required = false)String oorg,
                                  @RequestParam(required = false)String orno,
                                  @RequestParam(required = false)String ittp,
                                  @RequestParam(required = false) String otyp,
                                  @RequestParam(required = false)String seri,
                                  @RequestParam(required = false)String sfco,
                                  @RequestParam(required = false)String sflo,
                                  @RequestParam(required = false) String stco,
                                  @RequestParam(required = false) String item,
                                  @RequestParam(required = false) String qoro,
                                  @RequestParam(required = false) String orun,
                                  @RequestParam(required = false) String lsel,
                                  @RequestParam(required = false) String clot,
                                  @RequestParam(required = false) String serl,
                                  @RequestParam(required = false) String refe,
                                  @RequestParam(required = false) Integer userid,
                                  @RequestParam(required = false) Integer companyNo){

        return warehouseReceiptService.CreateWarehouseDLL(oorg,orno,ittp,otyp,seri,sfco,sflo,stco,item,qoro,orun,lsel,clot,serl,refe,userid,companyNo);
//        return warehouseReceiptService.CreateWarehouse(oorg,ittp,otyp,seri,sfco,sflo,stco,item,qoro,orun,lsel,clot,serl,refe,userid,companyNo);
    }

    /**
     * 审核行
     * @param orno
     * @param oset
     * @param pono
     * @param companyNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value="approve-line",method = RequestMethod.GET)
    public String approveLine(@RequestParam(required = false)   String orno,
                              @RequestParam(required = false)   Integer oset,
                              @RequestParam(required = false)   String pono,
                              @RequestParam(required = false)   String companyNo){
        return warehouseReceiptService.approveLine(orno,oset,pono,companyNo);
    }

    @ResponseBody
    @RequestMapping(value="find-max-line")
    public String findMaxLine(@RequestParam(required = false) String orno,
                              @RequestParam(required = false) Integer oset,
                              @RequestParam(required = false) String companyNo){
      return warehouseReceiptService.findMaxLine(orno,oset,companyNo);
    }

    /**
     * 出库行（入料）
     * @param oorg
     * @param orno
     * @param oset
     * @param pono
     * @param item
     * @param qoro
     * @param orun
     * @param lsel
     * @param clot
     * @param serl
     * @return
     */
    @ResponseBody
    @RequestMapping(value="createoutline",method = RequestMethod.GET)
    public String CreateOutLine(@RequestParam(required = false) String oorg,
                                @RequestParam(required = false) String orno,
                                @RequestParam(required = false) Integer oset,
                                @RequestParam(required = false) String pono,
                                @RequestParam(required = false) String item,
                                @RequestParam(required = false)  Integer qoro,
                                @RequestParam(required = false) String orun,
                                @RequestParam(required = false)  String lsel,
                                @RequestParam(required = false)  String clot,
                                @RequestParam(required = false) String serl,
                                @RequestParam(required = false) String refe,
                                @RequestParam(required = false)  Integer userid,
                                @RequestParam(required = false)  Integer companyNo){
        return warehouseReceiptService.CreateDeliveryLine(oorg,orno,oset,pono,item,qoro,orun,lsel,clot,serl,refe,userid,companyNo);
    }

    /**
     * 删除出库行（入料）
     * @param oorg
     * @param orno
     * @param oset
     * @param pono
     * @return
     */
    @ResponseBody
    @RequestMapping(value="deleteoutline",method = RequestMethod.GET)
    public String DeleteOutLine(@RequestParam(required = false) String oorg,
                         @RequestParam(required = false) String orno,
                         @RequestParam(required = false) Integer oset,
                         @RequestParam(required = false) String pono,
                          @RequestParam(required = false)  Integer userid,
                                @RequestParam(required = false)  Integer companyNo){
        return warehouseReceiptService.DeleteDeliveryLine(oorg,orno,oset,pono,userid,companyNo);
    }

    /**
     * 头部操作列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value="headpagelist",method = RequestMethod.GET)
    public String getHeadListRecord(@RequestParam(required = false) Integer pageNo,
                                    @RequestParam(required = false) Integer pageSize){
       return warehouseReceiptService.getHeadListRecordPage(pageNo,pageSize);
    }

    /**
     * 行操作列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value="linepagelist",method = RequestMethod.GET)
    public String getLineListRecord(@RequestParam(required = false) Integer pageNo,
                                    @RequestParam(required = false) Integer pageSize){
        return warehouseReceiptService.getLineListRecordPage(pageNo,pageSize);
    }


}
