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
public class WarehouseStcoPrintThread implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseStcoPrintThread.class);
    private String stco;
    private String dsca;
    private String code;
    private String printername;
    private Integer num;
    private Integer userid;

    public WarehouseStcoPrintThread(String stco, String dsca, Integer num, Integer userid, String printername) {
        this.stco = stco;
        this.dsca = dsca;
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
            PrintLabelLocation printLabelLocation=printLabelLocationMapper.selectByPrimaryKey(Code.PRINT_STCO);

            //发送到打印机
            System.out.println("发送到打印机");
            WarehouseStcoPrint w= new WarehouseStcoPrint();
            w.setStco(this.stco);
            w.setDsca(this.dsca);
            w.setCode(this.code);
            //选择的打印机
            System.out.println("选择的打印机");
            w.setPrinterName(this.printername);
            //设置位置
            System.out.println("设置位置");
            w.setPadding(printLabelLocation.getLeft(),printLabelLocation.getTop());
            System.out.println("库位打印");
            for(int i=0;i<num;i++){
                code= simpleDateFormat.format(new Date());
                w.setCode(code);
                w.printcode();
                w.setCode(code);
                System.out.println("打印结束");
            }

        }catch (Exception e){
            LOGGER.error("WarehouseStcoPrintThread:{}", LoggerUtil.getException(e));
        }

    }
}
