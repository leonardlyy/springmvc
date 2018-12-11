package com.lewei.production.print;

import com.lewei.production.mapper.promapper.PrintLabelLocationMapper;
import com.lewei.production.mapper.promapper.PrintRecordItemMapper;
import com.lewei.production.mapper.promapper.PrintRecordLocationMapper;
import com.lewei.production.model.PrintLabelLocation;
import com.lewei.production.model.PrintRecordItem;
import com.lewei.production.model.PrintRecordLocation;
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
public class WarehouseLocationPrintThread implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseLocationPrintThread.class);
    private String cwar;
    private String location;
    private String locationDsca;
    private String code;
    private String printername;
    private Integer num;
    private Integer userid;

    public WarehouseLocationPrintThread(String cwar, String location, String locationDsca, Integer num, Integer userid, String printername) {
        this.cwar = cwar;
        this.location = location;
        this.locationDsca = locationDsca;
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
            //获取标签位置
            PrintLabelLocationMapper printLabelLocationMapper= (PrintLabelLocationMapper) SpringApplicationContextHolder.getSpringBean("printLabelLocationMapper");
            PrintLabelLocation printLabelLocation=printLabelLocationMapper.selectByPrimaryKey(Code.PRINTLOCATION);

            PrintRecordLocationMapper printRecordLocationMapper= (PrintRecordLocationMapper) SpringApplicationContextHolder.getSpringBean("printRecordLocationMapper");
            PrintRecordLocation printRecordLocation=new PrintRecordLocation();
            printRecordLocation.setCwar(this.cwar);
            printRecordLocation.setLoca(this.location);
            printRecordLocation.setUserid(this.userid);
            //发送到打印机
            WarehouseLocationPrint warehouseLocationPrint= new WarehouseLocationPrint();
            warehouseLocationPrint.setLocation(this.location);
            warehouseLocationPrint.setCwar(this.cwar);
            warehouseLocationPrint.setLocationDsca(this.locationDsca);
            //选择的打印机
            warehouseLocationPrint.setPrinterName(this.printername);
            warehouseLocationPrint.setPadding(printLabelLocation.getLeft(),printLabelLocation.getTop());
            for(int i=0;i<num;i++){
                code= simpleDateFormat.format(new Date());
                warehouseLocationPrint.setCode(code);
                warehouseLocationPrint.printcode();
                printRecordLocation.setCode(code);
                printRecordLocationMapper.insert(printRecordLocation);
            }

        }catch (Exception e){
            LOGGER.error("WarehouseLocationPrintThread:{}", LoggerUtil.getException(e));
        }

    }
}
