package com.lewei.production.print;

import com.lewei.production.mapper.promapper.PrintLabelLocationMapper;
import com.lewei.production.mapper.promapper.PrintRecordItemMapper;
import com.lewei.production.model.PrintLabelLocation;
import com.lewei.production.model.PrintRecordItem;
import com.lewei.production.util.Code;
import com.lewei.production.util.LoggerUtil;
import com.lewei.production.web.SpringApplicationContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 22901 on 2017/3/16.
 */
public class WarehouseItemPrintThread implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseItemPrintThread.class);
    private String cwar;
    private String item;
    private String itemDsca;
    private String code;
    private String printername;
    private Integer num;
    private Integer userid;

    public WarehouseItemPrintThread(String cwar, String item, String itemDsca, Integer num, Integer userid, String printername) {
        this.cwar = cwar;
        this.item = item;
        this.itemDsca = itemDsca;
        this.num = num;
        this.userid = userid;
        this.printername = printername;
    }

    @Override
    public void run() {
        print();
    }

    private void print() {
        try{
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat(Code.YYMMDDHHMMSSSSS);
            //查询标签位置
            System.out.println("查询标签位置");
            PrintLabelLocationMapper printLabelLocationMapper= (PrintLabelLocationMapper) SpringApplicationContextHolder.getSpringBean("printLabelLocationMapper");
            PrintLabelLocation printLabelLocation=printLabelLocationMapper.selectByPrimaryKey(Code.PRINTITEM);

            PrintRecordItemMapper printRecordItemMapper= (PrintRecordItemMapper) SpringApplicationContextHolder.getSpringBean("printRecordItemMapper");
            PrintRecordItem printRecordItem=new PrintRecordItem();
            printRecordItem.setCwar(this.cwar);
            printRecordItem.setItem(this.item);
            printRecordItem.setUserid(this.userid);
            //发送到打印机
            System.out.println("发送到打印机");
            WarehouseItemPrint warehouseItemPrint= new WarehouseItemPrint();
            warehouseItemPrint.setItem(this.item);
            warehouseItemPrint.setCwar(this.cwar);
            warehouseItemPrint.setItemDsca(this.itemDsca);
            //选择的打印机
            System.out.println("选择的打印机");
            warehouseItemPrint.setPrinterName(this.printername);
            //设置位置
            System.out.println("设置位置");
            warehouseItemPrint.setPadding(printLabelLocation.getLeft(),printLabelLocation.getTop());
            System.out.println("库位打印");
            for(int i=0;i<num;i++){
                code= simpleDateFormat.format(new Date());
                warehouseItemPrint.setCode(code);
                warehouseItemPrint.printcode();
                printRecordItem.setCode(code);
                System.out.println("打印结束");
                printRecordItemMapper.insertSelective(printRecordItem);
            }
        }catch (Exception e){
            LOGGER.error("WarehouseItemPrintThread:{}", LoggerUtil.getException(e));
        }
    }
}
