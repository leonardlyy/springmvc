package com.lewei.production.service;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.lewei.production.mapper.orclmapper.*;
import com.lewei.production.mapper.promapper.IssueRecordHeadMapper;
import com.lewei.production.mapper.promapper.IssueRecordLineMapper;
import com.lewei.production.mapper.promapper.OsetFlagMapper;
import com.lewei.production.mapper.promapper.ScanFlagMapper;
import com.lewei.production.model.*;
import com.lewei.production.util.*;
import com.lewei.production.vo.ResultJson;
import com.lewei.util.Constant;
import com.lewei.wsdl.warehousing.DeliveryLine;
import com.lewei.wsdl.warehousing.ResultException;
import com.lewei.wsdl.warehousing.WarehouseReceipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * 仓单服务层
 * Created by 22901 on 2017/3/9.
 */
@Service
public class WarehouseReceiptService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseReceiptService.class);
    private static final String CLASS_NAME = "Baan.Application.barcod1";
    @Resource
    private WarehouseMapper warehouseMapper;
    @Resource
    private BatchMapper batchMapper;

    @Resource
    private ItemMapper itemMapper;
    @Resource
    private SerialNumberMapper serialNumberMapper;
    @Resource
    private SeriesMapper seriesMapper;
    @Resource
    private StorageLocationMapper storageLocationMapper;
    @Resource
    private TypeMapper typeMapper;
    @Resource
    private WorkCenterMapper workCenterMapper;
    @Resource
    private UnitMapper unitMapper;
    @Resource
    private WarehouseHeadMapper warehouseHeadMapper;
    @Resource
    private WarehouseLineMapper warehouseLineMapper;
    @Resource
    private IssueRecordHeadMapper issueRecordHeadMapper;
    @Resource
    private IssueRecordLineMapper issueRecordLineMapper;
    @Resource
    private ScanFlagMapper scanFlagMapper;
    @Resource
    private StorageNumMapper storageNumMapper;
    @Resource
    private EuntMapper euntMapper;
    @Resource
    private OsetFlagMapper osetFlagMapper;

    /**
     * 发料列表
     *
     * @return
     */
    public String getErpWarehouseHeadList(String orno, String companyNo, Integer pageNo, Integer pageSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orno", orno);
        params.put("companyNo", companyNo);
        Pagination pagination = new Pagination();
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);
        pagination.setParams(params);
        List<ErpWarehouseHead> heads = warehouseHeadMapper.selectAllPage(pagination);
        pagination.setResults(heads);
        return ResultJson.toJson(1, Code.FIND_SUCC_MSG, pagination);
    }

    /**
     * 显示详情
     *
     * @return
     */
    public String getErpWarehouseD(String orno, Integer oset, String companyNo) {
        Condition condition = new Condition();
        condition.setIf1(orno);
        condition.setIn1(oset);
        condition.setCompanyNo(companyNo);
        List<ErpWarehouseHead> erpWarehouseHeads = warehouseHeadMapper.selectByPrimaryKey(condition);
        List<ErpWarehouseLine> erpWarehouseLines = warehouseLineMapper.selectByPrimaryKey(condition);
        Map<String, Object> jsonmap = new HashMap<String, Object>();
        jsonmap.put("Head", erpWarehouseHeads);
        jsonmap.put("Line", erpWarehouseLines);
        return ResultJson.toJson(1, Code.FIND_SUCC_MSG, jsonmap);
    }

    /**
     * 获取仓库
     *
     * @return
     */
    public String getErpWarehouse(String companyNo, String eunt, String item) {
        Condition condition = new Condition();
        condition.setCompanyNo(companyNo);
        condition.setIf1(eunt);
        condition.setIf2(item);
        List<Other> others = warehouseMapper.selectDefault(condition);
        Map<String, Object> jsonmap = new HashMap<String, Object>();
        jsonmap.put("Warehouse", others);
        return ResultJson.toJson(1, Code.FIND_SUCC_MSG, jsonmap);
    }

    /**
     * 获取库位
     *
     * @param t_cwar
     * @return
     */
    public String getErpStorageLocation(String t_cwar, String clot, String item, String isClotNull, String companyNo) {
        if (item == null) {
            item = "";
        }
        Condition condition = new Condition();
        condition.setIf1(t_cwar);
        condition.setIf2(clot);
        condition.setIf3(item);
        condition.setIf4(isClotNull);
        condition.setCompanyNo(companyNo);
        List<Other> others = storageLocationMapper.selectDefault(condition);
        List<Other> tempList = new ArrayList<Other>();
        for (Other i : others) {
            if (!tempList.contains(i)) {
                tempList.add(i);
            }
        }
        return ResultJson.toJson(1, Code.FIND_SUCC_MSG, tempList);
    }

    /**
     * 仓单类型
     *
     * @param t_ittp
     * @return
     */
    public String getErpType(String t_ittp, String companyNo) {
    	System.out.println("-22222--");
        Condition condition = new Condition();
        condition.setCompanyNo(companyNo);
        condition.setIf1(t_ittp);
        List<Other> others = typeMapper.selectByPrimaryKey(condition);
        Map<String, Object> jsonmap = new HashMap<String, Object>();
        jsonmap.put("Type", others);
       
        System.out.println(ResultJson.toJson(1, Code.FIND_SUCC_MSG, jsonmap));
        return ResultJson.toJson(1, Code.FIND_SUCC_MSG, jsonmap);
    }

    /**
     * 获取企业单元
     *
     * @return
     */
    public String getEunt(String companyNo) {
        Condition condition = new Condition();
        condition.setCompanyNo(companyNo);
        List<Other> others = euntMapper.selectByPrimaryKey(condition);
        Map<String, Object> jsonmap = new HashMap<String, Object>();
        jsonmap.put("eunt", others);
        return ResultJson.toJson(1, Code.FIND_SUCC_MSG, jsonmap);
    }

    /**
     * 获取工作中心
     *
     * @return
     */
    public String getWorkCenter(String companyNo, String eunt) {
        Condition condition = new Condition();
        condition.setCompanyNo(companyNo);
        condition.setIf1(eunt);
        List<Other> others = workCenterMapper.selectByPrimaryKey(condition);
        Map<String, Object> jsonmap = new HashMap<String, Object>();
        jsonmap.put("ErpWorkCenter", others);
        return ResultJson.toJson(1, Code.FIND_SUCC_MSG, jsonmap);
    }

    /**
     * 获取系列
     *
     * @param t_nrgr
     * @return
     */
    public String getErpSeries(String t_nrgr, String companyNo) {
    	System.out.println("--11111-");
        Condition condition = new Condition();
        condition.setIf1(t_nrgr);
        condition.setCompanyNo(companyNo);
        List<Other> others = seriesMapper.selectByPrimaryKey(condition);
        Map<String, Object> jsonmap = new HashMap<String, Object>();
        jsonmap.put("Series", others);
        return ResultJson.toJson(1, Code.FIND_SUCC_MSG, jsonmap);
    }

    /**
     * 获取序列号
     *
     * @param t_item
     * @param t_cwar
     * @return
     */
    public String getErpSerialNumber(String t_item, String t_cwar, String companyNo) {
        Condition condition = new Condition();
        condition.setIf1(t_cwar);
        condition.setIf2(t_item);
        condition.setCompanyNo(companyNo);
        List<Other> others = serialNumberMapper.selectByPrimaryKey(condition);
        Map<String, Object> jsonmap = new HashMap<String, Object>();
        jsonmap.put("SerialNumber", others);
        return ResultJson.toJson(1, Code.FIND_SUCC_MSG, jsonmap);
    }

    /**
     * 获取批次
     *
     * @param t_cwar
     * @param t_loca
     * @param t_item
     * @return
     */
    public String getErpBatch(String t_cwar, String t_loca, String t_item, String companyNo) {
        Condition condition = new Condition();
        condition.setIf1(t_cwar);
        condition.setIf2(t_loca);
        condition.setIf3(t_item);
        condition.setCompanyNo(companyNo);
        List<Other> others = batchMapper.selectByPrimaryKey(condition);
        Map<String, Object> jsonmap = new HashMap<String, Object>();
        jsonmap.put("Batch", others);

        return ResultJson.toJson(1, Code.FIND_SUCC_MSG, jsonmap);
    }

    /**
     * 物料列表查询
     *
     * @param t_cwar
     * @return
     */
    public String getErpItem(String t_cwar, String companyNo) {
        Condition condition = new Condition();
        condition.setIf1(t_cwar);
        condition.setCompanyNo(companyNo);
        List<Other> others = itemMapper.selectByPrimaryKey(condition);
        Map<String, Object> jsonmap = new HashMap<String, Object>();
        jsonmap.put("Item", others);
        return ResultJson.toJson(1, Code.FIND_SUCC_MSG, jsonmap);
    }

    /**
     * 获取物料说明
     * 序列号
     * 数量单位
     *
     * @param t_cwar
     * @param t_item
     * @return
     */
    public String getErpItemAll(String t_cwar, String t_item, String companyNo) {
        System.out.print(t_cwar + "+++" + t_item);
        Condition condition = new Condition();
        condition.setIf1(t_cwar);
        condition.setIf2(t_item);
        condition.setCompanyNo(companyNo);
        List<Other> other = itemMapper.selectDsca(condition);
        List<Other> num = serialNumberMapper.selectByPrimaryKey(condition);
        Other unit = unitMapper.selectByPrimaryKey(condition);
        Map<String, Object> jsonmap = new HashMap<String, Object>();
        jsonmap.put("Item", other);
        jsonmap.put("SerialNumber", num);
        jsonmap.put("Unit", unit);
        return ResultJson.toJson(1, Code.FIND_SUCC_MSG, jsonmap);
    }

    /**
     * 获取库存数量   可用数量
     *
     * @param cwar
     * @param loca
     * @param item
     * @param clot
     * @param companyNo
     * @return
     */
    public String getStorageNum(String cwar, String loca, String item, String clot, String companyNo) {
        Condition condition = new Condition();
        condition.setIf1(cwar);
        condition.setIf2(loca);
        condition.setIf3(item);
        condition.setIf4(clot);
        condition.setCompanyNo(companyNo);
        Other other = storageNumMapper.selectStorageNum(condition);
        return ResultJson.toJson(1, Code.FIND_SUCC_MSG, other);
    }

    /**
     * @param sfco
     * @param stco
     * @return
     */
    public String getScanOrnn(String sfco, String stco, String seri, String companyNo) {
        ScanFlag scanFlag = new ScanFlag();
        scanFlag.setSfco(sfco);
        scanFlag.setStco(stco);
        scanFlag.setSeri(seri);
        scanFlag.setCompanyNo(companyNo);
        scanFlag = scanFlagMapper.selectScanOrno(scanFlag);
        return ResultJson.toJson(1, Code.FIND_SUCC_MSG, scanFlag);
    }

    /**
     * 离线同步
     *
     * @param headLine
     * @return
     */
    public String CreateAll(String headLine) {
        Map<String, Object> jsonmap = new HashMap<String, Object>();
        if (headLine.equals("")) {
            jsonmap.put("headnum", 0);
            jsonmap.put("linenum", 0);
            jsonmap.put("headcount", 0);
            jsonmap.put("linecount", 0);
        } else {
            int headnum = 0;
            int linenum = 0;
            int headcount = 0;
            int linecount = 0;
            Gson gson = new Gson();
            List<IssueRecord> irs = gson.fromJson(headLine, new TypeToken<List<IssueRecord>>() {
            }.getType());
            linecount = irs.size();
            try {
                for (IssueRecord ir : irs) {
                    ir.setMethod("create");
                    if (ir != null) {
                                Map<String,Object> jsonMap = addWarehouseDLL(ir.getOorg(), ir.getOrno(), ir.getIttp(), ir.getOtyp(), ir.getSeri(), ir.getSfco(), ir.getSflo(), ir.getStco(), ir.getItem(), ir.getQoro(), ir.getOrun(), ir.getLsel(), ir.getClot(), ir.getSerl(), ir.getRefe(), ir.getUserid() , Integer.valueOf(ir.getCompanyNo())); 
                    }
                }
            } catch (Exception e) {
                e.fillInStackTrace();
            }
            headcount = headnum;
            jsonmap.put("headnum", headnum);
            jsonmap.put("linenum", linenum);
            jsonmap.put("headcount", headcount);
            jsonmap.put("linecount", linecount);
        }
        return ResultJson.toJson(1, Code.FIND_SUCC_MSG, jsonmap);
    }

    /**
     * 创建仓单
     *
     * @return
     */
    public String Create(final String oorg, final String ittp, final String orno, final Long oset, final String otyp, final String seri, final String sfco, final String sflo, final String stco, Integer userid, final Integer companyNo) {
        IssueRecordHead head = new IssueRecordHead();
        head.setUserid(userid);
        head.setMethod("create");
        head.setOorg(oorg);
        head.setIttp(ittp);
        head.setOtyp(otyp);
        head.setSeri(seri);
        head.setSfco(sfco);
        head.setSflo(sflo);
        head.setStco(stco);
        head.setCompanyNo(String.valueOf(companyNo));
        String result = null;
            ExecutorService executor = Executors.newSingleThreadExecutor();
            FutureTask<String>
                    future =
                    new FutureTask<String>(new Callable<String>() {//使用Callable接口作为构造参数
                        public String call() {
                            //真正的任务在这里执行，这里的返回值类型为String，可以为任意类型
                            String res = null;
                            try {
                                LOGGER.error("WarehouseReceipt.create:{}",oorg, ittp, orno, oset, otyp, seri, sfco, sflo, stco, companyNo);
                                res=  WarehouseReceipt.create(oorg, ittp, orno, oset, otyp, seri, sfco, sflo, stco, companyNo);
                            } catch (ResultException e) {
                                e.printStackTrace();
                                LOGGER.error("WarehouseReceipt.create:{}", LoggerUtil.getException(e));
                                return ResultJson.toJson(-1, "创建失败", Code.NULL_STR);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                LOGGER.error("WarehouseReceipt.create:{}", LoggerUtil.getException(e));
                                return ResultJson.toJson(-1, "创建失败", Code.NULL_STR);
                            }
                            return  res;
                        }});
            executor.execute(future);
            boolean flagTimeOut = true;//false为正常，true为超时。
            try {
                result = future.get(5*60*1000, TimeUnit.MILLISECONDS); //取得结果，同时设置超时执行时间为5分钟。同样可以用future.get()，不设置执行超时时间取得结果
                flagTimeOut = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }finally {
                executor.shutdown();
            }

        Gson gson = new Gson();
        ResultJson json = gson.fromJson(result, ResultJson.class);
        if(json.getCode()==-1){
            return ResultJson.toJson(-1, json.getMessage(), Code.NULL_STR);
        }
        Map<String, Object> map = (Map<String, Object>) json.getData();
        if (map.get("orno") != null) {
            head.setOrno(map.get("orno").toString());
            head.setStatus(1);
        }
        if (map.get("oset") != null) {
            head.setOset(Integer.valueOf(map.get("oset").toString()));
        }
        issueRecordHeadMapper.insertSelective(head);
        if(flagTimeOut){
            return ResultJson.toJson(2, "发料行超时", "");
        }
        return  result;
    }

    /**
     *
     *
     * @param oorg |類型
     * @param orno  |倉單 如果为空则按仓单系统，自动创建仓单
     * @param ittp |活動類型
     * @param otyp
     * @param seri
     * @param sfco
     * @param sflo
     * @param stco
     * @param item
     * @param qoro
     * @param orun 单位
     * @param lsel 序列号类型
     * @param clot
     * @param serl 序列号
     * @param refe 行参考
     * @param userid
     * @param companyNo
     * @return
     */
    public String CreateWarehouseDLL(String oorg, String orno, String ittp,  String otyp, String seri, String sfco, String sflo, String stco, String item, String qoro, String orun, String lsel, String clot, String serl, String refe, Integer userid, Integer companyNo) {
        System.out.println("开始创建仓单");
 
        try {
            Map<String, Object> jsonMap = addWarehouseDLL(oorg, "", ittp, otyp, seri, sfco, sflo, stco, item, qoro, orun, lsel, clot, serl, refe, userid, companyNo);
            if (!jsonMap.get("resStatus").equals("1")) {
                return ResultJson.toJson(2, jsonMap.get("resMsg").toString(), Code.NULL_STR);
            }
            String pono = "";
            orno = jsonMap.get("resMsg").toString();
            pono = jsonMap.get("resLine").toString();
 
            Map<String, Object> res = new HashMap<String, Object>();
            res.put("orno", orno);
            res.put("pono", pono);
            return ResultJson.toJson(1, "创建成功！", res);
//            result = this.CreateDeliveryLine(oorg, orno, oset, String.valueOf(scanFlag.getNum()), item, qoroDou, orun, lsel, clot, serl, refe, userid, companyNo);
//        }
//       return ResultJson.toJson(0, "创建失败", Code.NULL_STR);
        }catch (Exception e){
            System.out.println("系统异常，发生错误");
            LOGGER.error("bw:{}", LoggerUtil.getException(e));
            return ResultJson.toJson(0, "创建失败", Code.NULL_STR);
        }
    }



    private Map<String,Object> addWarehouseDLL(String oorg, String orno, String ittp,  String otyp, String seri, String sfco, String sflo, String stco, String item, String qoro, String orun, String lsel, String clot, String serl, String refe, Integer userid, Integer companyNo) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        System.out.println("-------1111-------");
        try  {
//            create.warehouse.AFC.order(string var.oorg(2),|類型
//            string var.orno(9),   |倉單 如果为空则按仓单系统，自动创建仓单
//            string var.ittp(3),   |活動類型
//            string var.odat(14),  |交货日期
//            string var.pddt(14),  |收货日期
//            string var.sfco(9),    |供货方
//            string var.sflo(9),    |库位
//            string var.stco(9),    |进货方
//            string var.stlo(9),    |库位
//            string var.seri(4),      |系列
//            string var.otyp(3),      |類型
//            string var.wdep(6),      |部门
//            string var.item(47),     |物料号
//            string var.qoor(10),  |数量
//            string var.clot(20),   |批次
//            string var.order.ref(30),|页眉参考
//            string var.orderline.ref(30) , |行参考
//            string var.auto(1)  ) |是否自动发材料，1：自动发料 0：不发料
            String odat,pddt,wdep,qoor,stlo,auto,orderRef,orderlineRef;
            odat = CommonUtils.dateCastToString(new Date(),Code.YYYYMMDDHHMMSSS);//交货日期
            pddt = odat;//收货日期
            wdep = stco;//部门
            qoor=qoro;//数量
            stlo = "";
            orderRef = "";
            orderlineRef = refe;
            auto = "1";//自动
            if(orno==null){
                orno = "";
            }
        IssueRecordHead head = new IssueRecordHead();
        System.out.println("-------create Warehouse DLL-------");
        head.setUserid(userid);
        head.setMethod("create");
        head.setOorg(oorg);
        head.setIttp(ittp);
        head.setOtyp(otyp);
        head.setSeri(seri);
        head.setSfco(sfco);
        head.setSflo(sflo);
        head.setStco(stco);
        head.setCompanyNo(String.valueOf(companyNo));
        IssueRecordLine line = new IssueRecordLine();
        line.setUserid(userid);
        line.setMethod("create");
        line.setOorg(oorg);
        line.setOrno(orno);
//      line.setOset(oset);
        line.setCompanyNo(String.valueOf(companyNo));
        line.setItem(item);
        line.setQoro(String.valueOf(qoro));
        line.setOrun(orun);
        line.setLsel(lsel);
        line.setClot(clot);
        line.setSerl(serl);
        line.setRefe(refe);
        
//------------------------数据库插入方式----------------------------//
        ErpWarehouseSFCLine   erpWarehouseSFCLine = new ErpWarehouseSFCLine();
        erpWarehouseSFCLine.setCompanyNo("600");
        erpWarehouseSFCLine.setOorg(51);
        erpWarehouseSFCLine.setOrno(" ");
        erpWarehouseSFCLine.setIitp(2);
        erpWarehouseSFCLine.setOdat(new Date());
        erpWarehouseSFCLine.setPdat(new Date());
        erpWarehouseSFCLine.setSfco(sfco);
        erpWarehouseSFCLine.setSflo(sflo);
        erpWarehouseSFCLine.setStco(stco);
        erpWarehouseSFCLine.setStlo(" ");
        erpWarehouseSFCLine.setSeri(seri);
        erpWarehouseSFCLine.setOtyp(otyp);
        erpWarehouseSFCLine.setWdep(wdep);
        erpWarehouseSFCLine.setItem(item);
        erpWarehouseSFCLine.setQoor(Integer.valueOf(qoor) );
        erpWarehouseSFCLine.setClot(clot);
        erpWarehouseSFCLine.setRefa(orderRef+" ");
        erpWarehouseSFCLine.setRefb(orderlineRef+" ");
        erpWarehouseSFCLine.setAuto(1);
        erpWarehouseSFCLine.setTrdt(new Date());
        erpWarehouseSFCLine.setSeqn(0);
        erpWarehouseSFCLine.setStat(0);
        erpWarehouseSFCLine.setError(" ");
        erpWarehouseSFCLine.setWhno(" ");
        erpWarehouseSFCLine.setPono(0);
        erpWarehouseSFCLine.setRefc(0);
        erpWarehouseSFCLine.setRefu(0);
        System.out.println(erpWarehouseSFCLine);
        warehouseLineMapper.insretWarehouseLine(erpWarehouseSFCLine);
        System.out.println("-------2222-------");
        jsonMap.put("resStatus","1");
        jsonMap.put("resMsg"," ");        
     
        jsonMap.put("resLine",1);
//        head.setStatus(1);
//        head.setOrno(" ");
//        line.setOrno(" ");
//        line.setStatus(1);
//        line.setPono(" ");       
//        IssueRecordHead issueRecordHead = new IssueRecordHead();
//        issueRecordHead.setOrno(orno);
//        issueRecordHead.setStatus(1);
//        issueRecordHead.setMethod("create");
//        System.out.println("-------3333-------");
//        System.out.println(issueRecordHead);
//        List<IssueRecordHead> issueRecordHeads = issueRecordHeadMapper.selectByOrno(issueRecordHead);
//        if(issueRecordHeads.size()>0){
//        }else{
//        	  System.out.println("-------4444-------");
//            issueRecordHeadMapper.insertSelective(head);
//        }
//        System.out.println("-------5555-------");
//        issueRecordLineMapper.insertSelective(line);
//
//        System.out.println("-------6666-------");
        return jsonMap;
        }
        catch (Exception e) {
         
        	  System.out.println("-------error-------");
        	  LOGGER.error("bw:{}", LoggerUtil.getException(e));
        	   System.out.println(e);
        	   Map<String, Object> jsonmap = new HashMap<String, Object>();
               jsonmap.put("Warehouse", "error");
        	   return  jsonmap  ;
        }
        
    }

    /**
     * 创建订单，如果仓库和工作中心相同则累加，不同新建订单号
     */
    public String CreateWarehouse(String oorg, String ittp, String otyp, String seri, String sfco, String sflo, String stco, String item, String qoro, String orun, String lsel, String clot, String serl, String refe, Integer userid, Integer companyNo) {
        double qoroDou = 0;
        if (qoro != null) {
            qoroDou = Double.parseDouble(qoro);
        }
        String result;
        String orno = null;
        Integer oset = 1;
        String pono = null;
        ScanFlag scanFlag = new ScanFlag();
        scanFlag.setSeri(seri);
        scanFlag.setSfco(sfco);
        scanFlag.setStco(stco);
        scanFlag.setCompanyNo(String.valueOf(companyNo));
        scanFlag = scanFlagMapper.selectScanOrno(scanFlag);
        if (scanFlag != null) {
            if (scanFlag.getNum() >= 500) {
                result = this.Create(oorg, ittp, null, null, otyp, seri, sfco, sflo, stco, userid, companyNo);
                Gson gson = new Gson();
                ResultJson json = gson.fromJson(result, ResultJson.class);
                Map<String, Object> map = (Map<String, Object>) json.getData();
                if (map.get("orno") != null) {
                    orno = map.get("orno").toString();
                }
                if (map.get("oset") != null) {
                    oset = Integer.valueOf(map.get("oset").toString());
                }
                //scanFlag=new ScanFlag();
                scanFlag.setOrno(orno);
                scanFlag.setOset(1);
                scanFlag.setSfco(sfco);
                scanFlag.setStco(stco);
                scanFlag.setSeri(seri);
                scanFlag.setNum(1);
                scanFlagMapper.insertSelective(scanFlag);
                scanFlag = scanFlagMapper.selectScanOrno(scanFlag);
                OsetFlag osetFlag = new OsetFlag();
                osetFlag.setScanId(scanFlag.getId());
                osetFlag.setSflo(sflo);
                osetFlag.setOset(1);
                osetFlagMapper.insertSelective(osetFlag);
                result = this.CreateDeliveryLine(oorg, orno, oset, pono, item, qoroDou, orun, lsel, clot, serl, refe, userid, companyNo);

            } else {
                orno = scanFlag.getOrno();
                scanFlag.getId();
                OsetFlag osetFlag = new OsetFlag();
                osetFlag.setScanId(scanFlag.getId());
                osetFlag = osetFlagMapper.selectScanOset(osetFlag);
                if (osetFlag == null) {
                    oset = 1;
                    osetFlag = new OsetFlag();
                    osetFlag.setScanId(scanFlag.getId());
                    osetFlag.setSflo(sflo);
                    osetFlag.setOset(oset);
                    osetFlagMapper.insertSelective(osetFlag);
                } else {
                    Integer oset1 = osetFlag.getOset();
                    osetFlag.setScanId(scanFlag.getId());
                    osetFlag.setSflo(sflo);
                    osetFlag = osetFlagMapper.selectScanOset(osetFlag);
                    boolean flag = false;
                    if (osetFlag != null) {
                        //                    当天判断
                        Date date = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            date = sdf.parse(sdf.format(date));
                            osetFlag.setModifyDate(sdf.parse(sdf.format(osetFlag.getModifyDate())));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (date.getTime() == osetFlag.getModifyDate().getTime()) {
                            flag = true;
                        }
                    }
                    if (flag) {
                        oset = osetFlag.getOset();
                        orno = scanFlag.getOrno();
                    } else {
                        oset = oset1 + 1;
                        result = this.Create(oorg, ittp, orno, null, otyp, null, sfco, sflo, stco, userid, companyNo);
                        Gson gson = new Gson();
                        ResultJson json = gson.fromJson(result, ResultJson.class);
                        try {
                            Map<String, Object> map = (Map<String, Object>) json.getData();
                            if (map.get("orno") != null) {
                                orno = map.get("orno").toString();
                                if (map.get("oset") != null) {
                                    oset = Integer.valueOf(map.get("oset").toString());
                                }
                            } else {
                                return ResultJson.toJson(0, json.getMessage(), Code.NULL_STR);
                            }
                            osetFlag = new OsetFlag();
                            osetFlag.setScanId(scanFlag.getId());
                            osetFlag.setSflo(sflo);
                            osetFlag.setOset(oset);
                            osetFlagMapper.insertSelective(osetFlag);
                        } catch (Exception e) {
                            return ResultJson.toJson(0, "创建头失败", Code.NULL_STR);
                        }

                    }
                }
                scanFlag.setSfco(sfco);
                scanFlag.setStco(stco);


//                Condition condition = new Condition();
//                condition.setIf1(orno);
//                condition.setCompanyNo(String.valueOf(companyNo));
//                if(oset!=null){
//                    condition.setIn1(oset);
//                }
//                SystemContext.setType("oracle");
//                ErpWarehouseLine warehouseLine = warehouseLineMapper.selectMinOset(condition);
//                SystemContext.setType("mysql");
//                System.out.println("pono"+warehouseLine.getPono() + 1);
                result = this.CreateDeliveryLine(oorg, orno, oset, pono, item, qoroDou, orun, lsel, clot, serl, refe, userid, companyNo);
                Gson gson = new Gson();
                ResultJson json = gson.fromJson(result, ResultJson.class);
                Map<String, Object> map = (Map<String, Object>) json.getData();
                if (map.get("pono") != null || json.getCode() == 2) {
                    scanFlag.setNum(scanFlag.getNum() + 1);
                }
                scanFlag.setNum(scanFlag.getNum() + 1);
                scanFlag.setOset(oset);
            }

            scanFlagMapper.updateByPrimaryKeySelective(scanFlag);
        } else {
            result = this.Create(oorg, ittp, null, null, otyp, seri, sfco, sflo, stco, userid, companyNo);
            Gson gson = new Gson();
            ResultJson json = gson.fromJson(result, ResultJson.class);
            if (json.getCode() == -1) {
                return result;
            }
            Map<String, Object> map = (Map<String, Object>) json.getData();
            if (map.get("orno") != null) {
                orno = map.get("orno").toString();
                if (map.get("oset") != null) {
                    oset = Integer.valueOf(map.get("oset").toString());
                }
                // return ResultJson.toJson(0,"创建头失败",Code.NULL_STR);
            } else {
                return ResultJson.toJson(0, "创建头失败", Code.NULL_STR);
            }

            scanFlag = new ScanFlag();
            scanFlag.setOrno(orno);
            scanFlag.setOset(oset);
            scanFlag.setSfco(sfco);
            scanFlag.setStco(stco);
            scanFlag.setSeri(seri);
            scanFlag.setCompanyNo(String.valueOf(companyNo));
            scanFlag.setNum(1);
            scanFlagMapper.insertSelective(scanFlag);
            scanFlag = scanFlagMapper.selectScanOrno(scanFlag);
            OsetFlag osetFlag = new OsetFlag();
            osetFlag.setScanId(scanFlag.getId());
            osetFlag.setSflo(sflo);
            osetFlag.setOset(oset);
            osetFlagMapper.insertSelective(osetFlag);
            result = this.CreateDeliveryLine(oorg, orno, oset, String.valueOf(scanFlag.getNum()), item, qoroDou, orun, lsel, clot, serl, refe, userid, companyNo);
        }
//        if (result.equals("line_false")) {
//            LOGGER.error("line_false" + "line_false");
//            getScanOrnn(sfco, stco, seri, String.valueOf(companyNo));
//            scanFlag = new ScanFlag();
//            scanFlag.setSfco(sfco);
//            scanFlag.setStco(stco);
//            scanFlag.setSeri(seri);
//            scanFlag.setCompanyNo(String.valueOf(companyNo));
//            scanFlag = scanFlagMapper.selectScanOrno(scanFlag);
//            LOGGER.error("开始");
//            LOGGER.error(System.getProperty("java.library.path")
//            );
//            try {
//                ActiveXComponent bw = new ActiveXComponent(CLASS_NAME);
//                LOGGER.error("bw" + CLASS_NAME);
//                Variant call = new Variant();
//                call = Dispatch.call(bw, "ParseExecFunction", "owhinrdll90001",
//                        "approve.sfc.wh.order.line(" +
//                                "\"" + orno + "\"," +
//                                "\"" + oset + "\"," +
//                                "\"" + scanFlag.getNum() + "\"," +
//                                "\"" + 1 + "\"" +
//                                ")");
//                LOGGER.error("call" + call);
//                Variant property = bw.getProperty("ReturnValue");
//                LOGGER.error("property" + property);
//                Map<String, Object> jsonMap = new HashMap<String, Object>();
//                jsonMap.put("pono", scanFlag.getNum());
//                if (!property.getString().equals("1")) {
//                    return ResultJson.toJson(2, "订单行审核失败", jsonMap);
//                }
//                return com.lewei.vo.ResultJson.toJson(1, "创建成功", jsonMap);
//            } catch (Exception e) {
//                LOGGER.error("bw:{}", LoggerUtil.getException(e));
//            }
//        }



        return result;
    }

    /**
     * 创建出库行
     *
     * @return
     */
    public String CreateDeliveryLine(final String oorg, final String orno, final Integer oset, final String pono, final String item, final double qoro, final String orun, final String lsel, final String clot, final String serl, final String refe, final Integer userid, final Integer companyNo) {

        IssueRecordLine line = new IssueRecordLine();
        String result = null;
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<String> future =
                new FutureTask<String>(new Callable<String>() {//使用Callable接口作为构造参数
                    public String call() {
                        //真正的任务在这里执行，这里的返回值类型为String，可以为任意类型
                        String res = null;
                        try {
                            res = DeliveryLine.create(oorg, orno, oset, null, item, qoro, orun, lsel, clot, serl, companyNo, refe);
//                            try {
//                                Gson gson = new Gson();
//                                ResultJson json = gson.fromJson(res, ResultJson.class);
//                                Map<String, Object> map = (Map<String, Object>) json.getData();
//                                String ponoDll =pono;
//                                if (map.get("pono") != null) {
//                                    ponoDll = map.get("pono").toString();
//                                } else {
//                                    LOGGER.error("订单行号获取失败：{}", map);
//                                }
//
//                            } catch (Exception e) {
//                                LOGGER.error("订单行失败：{}", LoggerUtil.getException(e));
//                            }
                        } catch (ResultException e) {
                            e.printStackTrace();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        return res;
                    }
                });
        executor.execute(future);
        boolean flagTimeOut = true;//false为正常，true为超时。
        try {
            result = future.get(5 * 60 * 1000, TimeUnit.MILLISECONDS); //取得结果，同时设置超时执行时间为5分钟。同样可以用future.get()，不设置执行超时时间取得结果
            flagTimeOut = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
        Gson gson = new Gson();
        ResultJson json = gson.fromJson(result, ResultJson.class);
        Map<String, Object> map = (Map<String, Object>) json.getData();
        line.setUserid(userid);
        line.setMethod("create");
        line.setOorg(oorg);
        line.setOrno(orno);
        line.setOset(oset);
        line.setCompanyNo(String.valueOf(companyNo));
        Variant property = new Variant();
        if (map.get("pono") != null) {
            line.setPono(map.get("pono").toString());
            line.setStatus(1);
        }else if (json.getCode() == 2) {
            line.setStatus(1);
        }

        line.setItem(item);
        line.setQoro(String.valueOf(qoro));
        line.setOrun(orun);
        line.setLsel(lsel);
        line.setClot(clot);
        line.setSerl(serl);
        line.setRefe(refe);
        issueRecordLineMapper.insertSelective(line);
        if (flagTimeOut) {
            return ResultJson.toJson(2, "发料行超时", "");
        }
       
        return result;
    }

    public String approveLine(final String orno, final Integer oset, String pono, String companyNo){
                Condition condition = new Condition();
                condition.setIf1(orno);
                condition.setCompanyNo(companyNo);
                if(oset!=null){
                    condition.setIn1(oset);
                }
                List<ErpWarehouseLine> warehouseLines = warehouseLineMapper.selectMinOset(condition);
                if(warehouseLines!=null){
                     for (final ErpWarehouseLine warehouseLine :warehouseLines) {
                         Thread t = new Thread(new Runnable(){
                             public void run(){
                                 String pono = warehouseLine.getPono();
                                 ActiveXComponent bw = new ActiveXComponent(CLASS_NAME);
                                 Variant call = Dispatch.call(bw, "ParseExecFunction", "owhinrdll90001", "approve.sfc.wh.order.line(\"" + orno + "\",\"" + oset + "\",\"" + pono + "\",\"" + 1 + "\")");
                                 Variant property = bw.getProperty("ReturnValue");
                                 if (!property.getString().equals("1")) {
                                     LOGGER.error("订单行审核失败：{}", CLASS_NAME, property.getString());
                                     System.out.println("订单行审核失败:"+property.getString());
                                 }
                             }});
                         t.start();
                    }
                }else{
                    System.out.println("订单不存在:"+orno+ ":"+pono);
                    LOGGER.error("订单不存在：{}", CLASS_NAME, orno+ ":"+pono);
                }
        return ResultJson.toJson(1, "订单行审核成功", pono);
    }
    /**
     * 删除仓单
     *
     * @return
     */
    public String Delete(String oorg, String orno, Integer oset, Integer userid, Integer companyNo) {
        IssueRecordHead head = new IssueRecordHead();
        head.setUserid(userid);
        head.setMethod("delete");
        head.setOorg(oorg);
        head.setOrno(orno);
        head.setOset(oset);
        head.setCompanyNo(String.valueOf(companyNo));
        String result = WarehouseReceipt.delete(oorg, orno, oset, companyNo);
        Gson gson = new Gson();
        ResultJson json = gson.fromJson(result, ResultJson.class);
        Map<String, Object> map = (Map<String, Object>) json.getData();
        if (map.get("code") == "1") {
            head.setStatus(1);
        }
        issueRecordHeadMapper.insertSelective(head);
        return result;
    }

    /**
     * 删除出库行
     *
     * @return
     */
    public String DeleteDeliveryLine(String oorg, String orno, Integer oset, String pono, Integer userid, Integer companyNo) {
        IssueRecordHead head = new IssueRecordHead();
        head.setUserid(userid);
        head.setMethod("delete");
        head.setOorg(oorg);
        head.setOrno(orno);
        head.setOset(oset);
        head.setCompanyNo(String.valueOf(companyNo));
        String result = DeliveryLine.delete(oorg, orno, oset, pono, companyNo);
        Gson gson = new Gson();
        ResultJson json = gson.fromJson(result, ResultJson.class);
        Map<String, Object> map = (Map<String, Object>) json.getData();
        if (map.get("code") == "1") {
            head.setStatus(1);
        }
        issueRecordHeadMapper.insertSelective(head);
        return result;
    }


    public String getHeadListRecordPage(Integer pageNo, Integer pageSize) {
        Pagination pagination = new Pagination();
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);
        List<IssueRecordHead> issueRecordHeads = issueRecordHeadMapper.listByPage(pagination);
        pagination.setResults(issueRecordHeads);
        return ResultJson.toJson(Code.SUCCESS, "清单头历史分页", pagination);
    }

    public String getLineListRecordPage(Integer pageNo, Integer pageSize) {
        Pagination pagination = new Pagination();
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);
        List<IssueRecordLine> sssueRecordLine = issueRecordLineMapper.listByPage(pagination);
        pagination.setResults(sssueRecordLine);
        return ResultJson.toJson(Code.SUCCESS, "清单行历史分页", pagination);
    }

    public String findMaxLine(String orno, Integer oset, String companyNo) {
        Condition condition = new Condition();
        condition.setIf1(orno);
        condition.setCompanyNo(companyNo);
        if(oset!=null){
            condition.setIn1(oset);
        }
        ErpWarehouseLine warehouseLine = warehouseLineMapper.selectMaxOset(condition);
        return ResultJson.toJson(Code.SUCCESS, "订单行号", warehouseLine.getPono());
    }
}
