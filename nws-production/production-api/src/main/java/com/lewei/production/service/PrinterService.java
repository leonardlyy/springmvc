package com.lewei.production.service;

import com.lewei.production.mapper.orclmapper.ItemMapper;
import com.lewei.production.mapper.promapper.PrintLabelLocationMapper;
import com.lewei.production.mapper.promapper.PrintRecordItemMapper;
import com.lewei.production.model.Condition;
import com.lewei.production.model.Other;
import com.lewei.production.model.PrintLabelLocation;
import com.lewei.production.print.WarehouseClotPrintThread;
import com.lewei.production.print.WarehouseItemPrintThread;
import com.lewei.production.print.WarehouseLocationPrintThread;
import com.lewei.production.print.WarehouseStcoPrintThread;
import com.lewei.production.util.Code;
import com.lewei.production.vo.ResultJson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 打印机设置
 * Created by 22901 on 2017/3/13.
 */
@Service
public class PrinterService {

    @Resource
    private ItemMapper itemMapper;

    @Resource
    private PrintRecordItemMapper printRecordItemMapper;

    @Resource
    private PrintLabelLocationMapper printLabelLocationMapper;

    /**
     * 获取打印机
     * @return
     */
    public String getPrinterList(){
        // 构建打印请求属性集
        HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        // 设置打印格式，因为未确定类型，所以选择autosense
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        // 查找所有的可用的打印服务
        PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
        Map<String,Object> jsonmap=
                new HashMap<String,Object>();
       String[] printerName=new String[printService.length];
       for (int i=0;i < printService.length;i++){
           printerName[i]=printService[i].getName();
       }
        jsonmap.put("Printer",printerName);
        return ResultJson.toJson(Code.SUCCESS,"打印机列表",jsonmap);
    }


    /**
     * 物料打印
     * @param cwar
     * @param item
     * @param itemDsca
     * @param num
     * @param userid
     * @param printername
     * @return
     */
   public String printItem(String cwar,String item,String itemDsca,Integer num,Integer userid,String printername){
       // 构建打印请求属性集
       HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
       // 设置打印格式，因为未确定类型，所以选择autosense
       DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
       // 查找所有的可用的打印服务
       PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
       Map<String,Object> jsonmap=new HashMap<String,Object>();
       String[] printerName=new String[printService.length];
       for (int i=0;i < printService.length;i++){
           if(printername.equals(printService[i].getName())){
               WarehouseItemPrintThread warehouseItemPrintThread=new WarehouseItemPrintThread(cwar,item,itemDsca,num,userid,printername);
               new Thread(warehouseItemPrintThread).start();
               return ResultJson.toJson(Code.SUCCESS,"打印机列表","");
           }
       }
       return ResultJson.toJson(Code.ERROR,"打印机不匹配",printername);
   }

    /**
     * 库位打印
     * @param cwar
     * @param loca
     * @param locaDsca
     * @param num
     * @param userid
     * @param printername
     * @return
     */
    public String printLocation(String cwar,String loca,String locaDsca,Integer num,Integer userid,String printername){
        // 构建打印请求属性集
        HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        // 设置打印格式，因为未确定类型，所以选择autosense
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        // 查找所有的可用的打印服务
        PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
        Map<String,Object> jsonmap=new HashMap<String,Object>();
        String[] printerName=new String[printService.length];
        for (int i=0;i < printService.length;i++){
            if(printername.equals(printService[i].getName())){
                WarehouseLocationPrintThread warehouseLocationPrintThread=new WarehouseLocationPrintThread(cwar,loca,locaDsca,num,userid,printername);
                new Thread(warehouseLocationPrintThread).start();
                return ResultJson.toJson(Code.SUCCESS,"打印机列表","");
            }
        }
        return ResultJson.toJson(Code.ERROR,"打印机不匹配",printername);
    }
    /**
     * 工作中心打印
     * @param userid
     * @param printername
     * @return
     */
    public String printStco(String stco,String dsca,Integer num,Integer userid,String printername){
        // 构建打印请求属性集
        HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        // 设置打印格式，因为未确定类型，所以选择autosense
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        // 查找所有的可用的打印服务
        PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
        Map<String,Object> jsonmap=new HashMap<String,Object>();
        String[] printerName=new String[printService.length];
        for (int i=0;i < printService.length;i++){
            if(printername.equals(printService[i].getName())){
                WarehouseStcoPrintThread w=new WarehouseStcoPrintThread(stco,dsca,num,userid,printername);
                new Thread(w).start();
                return ResultJson.toJson(Code.SUCCESS,"打印成功","");
            }
        }
        return ResultJson.toJson(Code.ERROR,"打印机不匹配",printername);
    }
    /**
     * 批次打印
     * @param cwar
     * @param item
     * @param num
     * @param userid
     * @param printername
     * @return
     */
    public String printClot(String cwar,String item,String dsca,String clot,Integer num,String user,Integer userid,String printername,String bpidname){
    	 
    	// 构建打印请求属性集
        HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        // 设置打印格式，因为未确定类型，所以选择autosense
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        // 查找所有的可用的打印服务
        PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
        Map<String,Object> jsonmap=new HashMap<String,Object>();
        String[] printerName=new String[printService.length];
        for (int i=0;i < printService.length;i++){
            if(printername.equals(printService[i].getName())){
            	System.out.println("111");
                WarehouseClotPrintThread w=new WarehouseClotPrintThread(cwar,item,dsca,clot,num,user,userid,printername,bpidname);
                System.out.println(w);
                new Thread(w).start();
                return ResultJson.toJson(Code.SUCCESS,"打印机列表","");
            }
        }
        System.out.println("111");
        return ResultJson.toJson(Code.ERROR,"打印机不匹配",printername);
    }
    /**
     * 修改标签位置
     * @param id
     * @param left
     * @param top
     * @return
     */
    public String updateLabelLocation(Integer id,Integer left,Integer top,Integer userid){
        PrintLabelLocation updateLabelLocation=new PrintLabelLocation();
        updateLabelLocation.setId(id);
        updateLabelLocation.setLeft(left);
        updateLabelLocation.setTop(top);
        updateLabelLocation.setUserid(userid);
        try{
            printLabelLocationMapper.updateByPrimaryKeySelective(updateLabelLocation);
            List<PrintLabelLocation> printLabelLocations=printLabelLocationMapper.selectAll();
            return ResultJson.toJson(Code.SUCCESS,"修改成功",printLabelLocations);
        }catch (Exception e){
            e.printStackTrace();
            return ResultJson.toJson(Code.ERROR,"修改失败","");
        }
    }

    /**
     * 标签位置列表
     * @return
     */
    public String selectLabelLocation(){
        List<PrintLabelLocation> printLabelLocations=printLabelLocationMapper.selectAll();
        return ResultJson.toJson(Code.SUCCESS,"查询成功",printLabelLocations);
    }


    public String getErpItem(String cwar, String companyNo) {
        Condition condition = new Condition();
        condition.setIf1(cwar);
        condition.setCompanyNo(companyNo);
        List<Other> others = itemMapper.selectPrint(condition);
        Map<String, Object> jsonmap = new HashMap<String, Object>();
        jsonmap.put("Item", others);
        return ResultJson.toJson(1, Code.FIND_SUCC_MSG, jsonmap);
    }
}
