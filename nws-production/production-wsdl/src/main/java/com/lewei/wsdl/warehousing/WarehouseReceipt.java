package com.lewei.wsdl.warehousing;

import com.lewei.util.Constant;
import com.lewei.vo.ResultJson;
import net.sf.json.JSONObject;
import org.apache.axis2.AxisFault;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

/**
 * 仓单头
 * Created by 22901 on 2017/3/9.
 */
public class WarehouseReceipt {
//    public static void main(String[] args){
//        create("production.man","issue","OUS","WH","MDCU","A-03-2","1WAB00");
//    }

    /**
     * 登录
     * @return
     */
    private static WarehouseReceiptServiceStub.Activation getActivation(Integer companyNo,String username,String password){
        WarehouseReceiptServiceStub.Activation at=new WarehouseReceiptServiceStub.Activation() ;
        WarehouseReceiptServiceStub.ActivationType att=new WarehouseReceiptServiceStub.ActivationType();
        if(companyNo==null ||companyNo.equals("")){ companyNo = Constant.COMPANY; username = Constant.USERNAME; password = Constant.PASSWORD; }
        if(username==null ||username.equals("")){ username = Constant.USERNAME; password = Constant.PASSWORD;}
        att.setCompany(companyNo);
        att.setUsername(username);
        att.setPassword(password);
        at.setActivation(att);
        return at;
    }

    /**
     * 新建仓单头
     * @param oorg
     * @param ittp
     * @param otyp
     * @param seri
     * @param sfco
     * @param sflo
     * @param stco
     * @return
     */
    public static String create(String oorg, String ittp,String orno, Long oset,String otyp, String seri, String sfco, String sflo, String stco,Integer companyNo) throws ResultException, RemoteException {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        JSONObject resultjs = null;
            WarehouseReceiptServiceStub warehouseReceiptServiceStub=new WarehouseReceiptServiceStub();
            WarehouseReceiptServiceStub.Create create =new WarehouseReceiptServiceStub.Create();
            WarehouseReceiptServiceStub.CreateRequestType screq =new WarehouseReceiptServiceStub.CreateRequestType();
            WarehouseReceiptServiceStub.ControlArea_type4 sca=new WarehouseReceiptServiceStub.ControlArea_type4();
            WarehouseReceiptServiceStub.ProcessingScope sps = new WarehouseReceiptServiceStub.ProcessingScope("request",true);
            sca.setProcessingScope(sps);
            screq.setControlArea(sca);
            WarehouseReceiptServiceStub.DataArea_type4 sda = new WarehouseReceiptServiceStub.DataArea_type4();
            WarehouseReceiptServiceStub.WarehouseReceipt_type4[] swr=new WarehouseReceiptServiceStub.WarehouseReceipt_type4[1];
            swr[0]=new WarehouseReceiptServiceStub.WarehouseReceipt_type4();
            WarehouseReceiptServiceStub.WhinhOorg_mandatory saveoorg= new WarehouseReceiptServiceStub.WhinhOorg_mandatory(oorg,true);
            swr[0].setOorg(saveoorg);
            WarehouseReceiptServiceStub.WhinhIttp_mandatory saveittp=new WarehouseReceiptServiceStub.WhinhIttp_mandatory(ittp,true);
            swr[0].setIttp(saveittp);
            WarehouseReceiptServiceStub.Tccotp_mandatory saveotyp = new WarehouseReceiptServiceStub.Tccotp_mandatory();
            saveotyp.setTccotp_mandatory(otyp);
            swr[0].setOtyp(saveotyp);
            if(seri!=null && seri != ""){
                WarehouseReceiptServiceStub.Tcseri saveseri = new WarehouseReceiptServiceStub.Tcseri();
                saveseri.setTcseri(seri);
                swr[0].setSeri(saveseri);
            }
            WarehouseReceiptServiceStub.Tccshp_mandatory savesfco = new WarehouseReceiptServiceStub.Tccshp_mandatory();
            savesfco.setTccshp_mandatory(sfco);
            swr[0].setSfco(savesfco);
            if(sflo.equals("")||sflo==null){

            }else {
                WarehouseReceiptServiceStub.Whloca savesflo = new WarehouseReceiptServiceStub.Whloca();
                savesflo.setWhloca(sflo);
                swr[0].setSflo(savesflo);
            }
            WarehouseReceiptServiceStub.Tccshp_mandatory savestco = new WarehouseReceiptServiceStub.Tccshp_mandatory();
            savestco.setTccshp_mandatory(stco);
            swr[0].setStco(savestco);

            if(orno!=null){
                WarehouseReceiptServiceStub.Tcorno strOrno=new WarehouseReceiptServiceStub.Tcorno();
                strOrno.setTcorno(orno);
                swr[0].setOrno(strOrno);
            }
            if(oset!=null){
                WarehouseReceiptServiceStub.Tcwset_mandatory strOset=new WarehouseReceiptServiceStub.Tcwset_mandatory() ;
                strOset.setTcwset_mandatory(oset);
                swr[0].setOset(strOset);
            }
            if(stco!=null){
                WarehouseReceiptServiceStub.Tccwoc strWdep = new WarehouseReceiptServiceStub.Tccwoc();
                strWdep.setTccwoc(stco);
                swr[0].setWdep(strWdep);
            }
            sda.setWarehouseReceipt(swr);
            screq.setDataArea(sda);
            create.setCreateRequest(screq);
            WarehouseReceiptServiceStub.CreateResponse result = warehouseReceiptServiceStub.create(create, getActivation(companyNo,null,null));
            WarehouseReceiptServiceStub.CreateResponseType result1 = result.getCreateResponse();
            WarehouseReceiptServiceStub.DataArea_type5 result2 = result1.getDataArea();
            WarehouseReceiptServiceStub.WarehouseReceipt_type5[] result3 = result2.getWarehouseReceipt();
            jsonMap.put("oorg", result3[0].getOorg().toString());
            jsonMap.put("orno", result3[0].getOrno().toString());
            jsonMap.put("oset", result3[0].getOset().toString());
            return ResultJson.toJson(1, "创建成功",jsonMap);

    }

    /**
     * 修改仓单头
     * @param oorg
     * @param orno
     * @param oset
     * @param ittp
     * @param otyp
     * @param seri
     * @param sfco
     * @param sflo
     * @param stco
     * @return
     */
    public static String update(String oorg, String orno, Integer oset, String ittp, String otyp, String seri, String sfco, String sflo, String stco,Integer companyNo){
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        JSONObject resultjs = null;
        try {
            WarehouseReceiptServiceStub warehouseReceiptServiceStub=new WarehouseReceiptServiceStub();
            WarehouseReceiptServiceStub.Change change=new WarehouseReceiptServiceStub.Change();
            WarehouseReceiptServiceStub.ChangeRequestType screq =new WarehouseReceiptServiceStub.ChangeRequestType();
            WarehouseReceiptServiceStub.ControlArea_type0 sca=new WarehouseReceiptServiceStub.ControlArea_type0();
            WarehouseReceiptServiceStub.ProcessingScope sps = new WarehouseReceiptServiceStub.ProcessingScope("request",true);
            sca.setProcessingScope(sps);
            screq.setControlArea(sca);
            WarehouseReceiptServiceStub.DataArea_type0 sda = new WarehouseReceiptServiceStub.DataArea_type0();
            WarehouseReceiptServiceStub.WarehouseReceipt_type0[] swr=new WarehouseReceiptServiceStub.WarehouseReceipt_type0[1];
            swr[0]=new WarehouseReceiptServiceStub.WarehouseReceipt_type0();
            WarehouseReceiptServiceStub.WhinhOorg_mandatory saveoorg= new WarehouseReceiptServiceStub.WhinhOorg_mandatory(oorg,true);
            swr[0].setOorg(saveoorg);
            WarehouseReceiptServiceStub.Tcorno uporno = new WarehouseReceiptServiceStub.Tcorno();
            uporno.setTcorno(orno);
            swr[0].setOrno(uporno);
            WarehouseReceiptServiceStub.Tcwset_mandatory uposet= new WarehouseReceiptServiceStub.Tcwset_mandatory();
            uposet.setTcwset_mandatory(oset);
            swr[0].setOset(uposet);
            WarehouseReceiptServiceStub.WhinhIttp_mandatory saveittp=new WarehouseReceiptServiceStub.WhinhIttp_mandatory(ittp,true);
            swr[0].setIttp(saveittp);
            if(otyp.equals("")||otyp==null){}else {
            WarehouseReceiptServiceStub.Tccotp_mandatory saveotyp = new WarehouseReceiptServiceStub.Tccotp_mandatory();
            saveotyp.setTccotp_mandatory(otyp);
            swr[0].setOtyp(saveotyp);}
            if(seri.equals("")||seri==null){}else {
                WarehouseReceiptServiceStub.Tcseri saveseri = new WarehouseReceiptServiceStub.Tcseri();
                saveseri.setTcseri(seri);
                swr[0].setSeri(saveseri);
            }
            if(sfco.equals("")||sfco==null){}else {
            WarehouseReceiptServiceStub.Tccshp_mandatory savesfco = new WarehouseReceiptServiceStub.Tccshp_mandatory();
            savesfco.setTccshp_mandatory(sfco);
            swr[0].setSfco(savesfco);}
            if(sflo.equals("")||sflo==null){}else {
                WarehouseReceiptServiceStub.Whloca savesflo = new WarehouseReceiptServiceStub.Whloca();
                savesflo.setWhloca(sflo);
                swr[0].setSflo(savesflo);
            }
            if(stco.equals("")||stco==null){}else {
                WarehouseReceiptServiceStub.Tccshp_mandatory savestco = new WarehouseReceiptServiceStub.Tccshp_mandatory();
                savestco.setTccshp_mandatory(stco);
                swr[0].setStco(savestco);
            }
            sda.setWarehouseReceipt(swr);
            screq.setDataArea(sda);
            change.setChangeRequest(screq);
            WarehouseReceiptServiceStub.ChangeResponse result = warehouseReceiptServiceStub.change(change, getActivation(companyNo,null,null));
            WarehouseReceiptServiceStub.ChangeResponseType result1 = result.getChangeResponse();
            WarehouseReceiptServiceStub.DataArea_type2 result2 = result1.getDataArea();
            WarehouseReceiptServiceStub.WarehouseReceipt_type2[] result3 = result2.getWarehouseReceipt();
            jsonMap.put("oorg", result3[0].getOorg().toString());
            jsonMap.put("orno", result3[0].getOrno().toString());
            jsonMap.put("oset", result3[0].getOset().toString());
            return ResultJson.toJson(1, "修改成功",jsonMap);
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
            return ResultJson.toJson(-1, "修改失败",jsonMap);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultJson.toJson(-1, "修改失败",jsonMap);
        } catch (ResultException e) {
            e.printStackTrace();
            return ResultJson.toJson(-1, "修改失败",jsonMap);
        }

    }

    /**
     * 删除订单头
     * @param oorg
     * @param orno
     * @param oset
     * @return
     */
    public static String delete(String oorg, String orno, Integer oset,Integer companyNo){
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        JSONObject resultjs = null;
        try {
            WarehouseReceiptServiceStub warehouseReceiptServiceStub=new WarehouseReceiptServiceStub();
            WarehouseReceiptServiceStub.Delete delete =new WarehouseReceiptServiceStub.Delete();
            WarehouseReceiptServiceStub.DeleteRequestType sdreq=new WarehouseReceiptServiceStub.DeleteRequestType();
            WarehouseReceiptServiceStub.ControlArea_type3 sca=new WarehouseReceiptServiceStub.ControlArea_type3();
            WarehouseReceiptServiceStub.ProcessingScope sps=new WarehouseReceiptServiceStub.ProcessingScope("request",true);
            sca.setProcessingScope(sps);
            WarehouseReceiptServiceStub.DataArea_type3 sda=new WarehouseReceiptServiceStub.DataArea_type3();
            WarehouseReceiptServiceStub.WarehouseReceipt_type3[] swr=new WarehouseReceiptServiceStub.WarehouseReceipt_type3[1];
            swr[0]=new WarehouseReceiptServiceStub.WarehouseReceipt_type3();
            WarehouseReceiptServiceStub.WhinhOorg_mandatory soorg=new WarehouseReceiptServiceStub.WhinhOorg_mandatory(oorg,true);
            swr[0].setOorg(soorg);
            WarehouseReceiptServiceStub.Tcorno sorno=new WarehouseReceiptServiceStub.Tcorno();
            sorno.setTcorno(orno);
            swr[0].setOrno(sorno);
            WarehouseReceiptServiceStub.Tcwset_mandatory soset=new WarehouseReceiptServiceStub.Tcwset_mandatory();
            soset.setTcwset_mandatory(oset);
            swr[0].setOset(soset);
            sda.setWarehouseReceipt(swr);
            sdreq.setDataArea(sda);
            sdreq.setControlArea(sca);
            delete.setDeleteRequest(sdreq);
            warehouseReceiptServiceStub.delete(delete,getActivation(companyNo,null,null));
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
            return ResultJson.toJson(-1, "删除失败",jsonMap);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultJson.toJson(-1, "删除失败",jsonMap);
        } catch (ResultException e) {
            e.printStackTrace();
            return ResultJson.toJson(-1, "删除失败",jsonMap);
        }

        return ResultJson.toJson(1, "删除成功",jsonMap);

    }
}
