package com.lewei.production.print;

import com.lewei.production.mapper.promapper.PrintLabelLocationMapper;
import com.lewei.production.mapper.promapper.PrintRecordItemMapper;
import com.lewei.production.model.PrintLabelLocation;
import com.lewei.production.model.PrintRecordItem;
import com.lewei.production.service.WarehouseReceiptService;
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
public class WarehouseClotPrintThread implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseClotPrintThread.class);

    private String cwar;
    private String item;
    private String dsca;
    private String clot;
    private String code;
    private String printername;
    private Integer num;
    private Integer userid;
    private String user;
    private String bpidname;
    

    public WarehouseClotPrintThread(String cwar, String item ,String dsca, String clot, Integer num, String user, Integer userid, String printername,String bpidname) {
        this.cwar = cwar;
        this.item = item;
        this.dsca = dsca;
        this.clot = clot;
        this.num = num;
        this.userid = userid;
        this.user=user;
        this.printername = printername;
        this.bpidname =bpidname;
    }

    @Override
    public void run() {
        print();
    }

    private void print() {
        try {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat(Code.YYMMDDHHMMSSSSS);
            //查询标签位置
            PrintLabelLocationMapper printLabelLocationMapper= (PrintLabelLocationMapper) SpringApplicationContextHolder.getSpringBean("printLabelLocationMapper");
            PrintLabelLocation printLabelLocation=printLabelLocationMapper.selectByPrimaryKey(Code.PRINT_CLOT);

            //发送到打印机
            WarehouseClotPrint w= new WarehouseClotPrint();
            w.setItem(this.item);
            w.setDsca(this.dsca);
            w.setUser(this.user);
            w.setCwar(this.cwar);
            w.setClot(this.clot);
            w.setBpidname(this.bpidname);
            //选择的打印机
            w.setPrinterName(this.printername);
            //设置位置
            w.setPadding(printLabelLocation.getLeft(),printLabelLocation.getTop());
            for(int i=0;i<num;i++){
                code= simpleDateFormat.format(new Date());
                w.setCode(code);
                w.printcode();
            }
        }catch (Exception e){
            LOGGER.error("WarehouseClotPrintThread:{}", LoggerUtil.getException(e));
        }

    }
}
