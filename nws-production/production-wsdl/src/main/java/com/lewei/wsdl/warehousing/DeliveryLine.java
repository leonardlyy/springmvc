package com.lewei.wsdl.warehousing;

import com.lewei.util.Constant;
import com.lewei.vo.ResultJson;
import net.sf.json.JSONObject;
import org.apache.axis2.AxisFault;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

/**
 * 出库单行
 * Created by 22901 on 2017/3/9.
 */
public class DeliveryLine {
    /**
     * 登录信息
     * @return
     */
    private static DeliveryLineServiceStub.Activation getActivation(Integer companyNo,String username,String password){
        DeliveryLineServiceStub.Activation at=new DeliveryLineServiceStub.Activation() ;
        DeliveryLineServiceStub.ActivationType att=new DeliveryLineServiceStub.ActivationType();
        if(companyNo==null ||companyNo.equals("")){ companyNo = Constant.COMPANY; username = Constant.USERNAME; password = Constant.PASSWORD; }
        if(username==null ||username.equals("")){ username = Constant.USERNAME; password = Constant.PASSWORD;}
        att.setCompany(companyNo);
        att.setUsername(username);
        att.setPassword(password);
        at.setActivation(att);
        return at;
    }

    /**
     * 创建出库行
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
    public static String create(String oorg, String orno, Integer oset, String pono, String item, double qoro, String orun, String lsel, String clot, String serl,Integer companyNo,String refe) throws ResultException, RemoteException {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        JSONObject resultjs = null;

            DeliveryLineServiceStub deliveryLineServiceStub=new DeliveryLineServiceStub();
            DeliveryLineServiceStub.Create create=new DeliveryLineServiceStub.Create();
            DeliveryLineServiceStub.CreateRequestType screq = new DeliveryLineServiceStub.CreateRequestType();
            DeliveryLineServiceStub.ControlArea_type3 sca = new DeliveryLineServiceStub.ControlArea_type3();
            DeliveryLineServiceStub.ProcessingScope sps=new DeliveryLineServiceStub.ProcessingScope("request",true);
            sca.setProcessingScope(sps);
            screq.setControlArea(sca);

            DeliveryLineServiceStub.DataArea_type3 sda = new DeliveryLineServiceStub.DataArea_type3();
            DeliveryLineServiceStub.DeliveryLine_type3[] sdl = new DeliveryLineServiceStub.DeliveryLine_type3[1];
             sdl[0]=new DeliveryLineServiceStub.DeliveryLine_type3();
            DeliveryLineServiceStub.Tcwset_mandatory soset=new DeliveryLineServiceStub.Tcwset_mandatory();
            soset.setTcwset_mandatory(oset);
            sdl[0].setOset(soset);

            if(lsel.equals("")||lsel==null){
                lsel="any";
            }
            DeliveryLineServiceStub.Tclsel_mandatory slsel=new DeliveryLineServiceStub.Tclsel_mandatory(lsel,true);
            sdl[0].setLsel(slsel);

            if(lsel.equals("specific")) {
                DeliveryLineServiceStub.Tcclot sclot = new DeliveryLineServiceStub.Tcclot();
                sclot.setTcclot(clot);
                sdl[0].setClot(sclot);
            }
            
            DeliveryLineServiceStub.Tcitem sitem=new DeliveryLineServiceStub.Tcitem();
            sitem.setTcitem(item);
            sdl[0].setItem(sitem);

            DeliveryLineServiceStub.WhinhOorg_mandatory spprg=new DeliveryLineServiceStub.WhinhOorg_mandatory(oorg,true);
            sdl[0].setOorg(spprg);

            DeliveryLineServiceStub.Tcorno sorno =new DeliveryLineServiceStub.Tcorno();
            sorno.setTcorno(orno);
            sdl[0].setOrno(sorno);

            DeliveryLineServiceStub.Tccuni sorun=new DeliveryLineServiceStub.Tccuni();
            sorun.setTccuni(orun);
            sdl[0].setOrun(sorun);

            if(refe==null||refe.equals("")){}else{
                DeliveryLineServiceStub.Tcrefa refeStr = new DeliveryLineServiceStub.Tcrefa();
                refeStr.setTcrefa(refe);
                sdl[0].setRefe(refeStr);
            }

            DeliveryLineServiceStub.Tcqst1 sqoro = new DeliveryLineServiceStub.Tcqst1();
            BigDecimal ssqoro=new BigDecimal(qoro);
            sqoro.setTcqst1(ssqoro);
            sdl[0].setQoro(sqoro);

            if(serl.equals("")||serl==null){  }else{
                DeliveryLineServiceStub.TcibdSern sserl=new DeliveryLineServiceStub.TcibdSern();
                sserl.setTcibdSern(serl);
                sdl[0].setSerl(sserl);
                        }
            sda.setDeliveryLine(sdl);
            screq.setDataArea(sda);
            create.setCreateRequest(screq);
        try {
            DeliveryLineServiceStub.CreateResponse result = deliveryLineServiceStub.create(create, getActivation(companyNo,null,null));
             DeliveryLineServiceStub.CreateResponseType result1 = result.getCreateResponse();
             DeliveryLineServiceStub.DataArea_type4 result2 = result1.getDataArea();
            DeliveryLineServiceStub.DeliveryLine_type4[] result3 = result2.getDeliveryLine();
            System.out.println(result3);
            jsonMap.put("pono", result3[0].getPono().toString());
            return ResultJson.toJson(1, "创建成功",jsonMap);
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();

            return ResultJson.toJson(2, "创建成功，返回数据出错",jsonMap);
        }
    }

    /**
     * 删除出库行
     * @param oorg
     * @param orno
     * @param oset
     * @param pono
     * @return
     */
    public static String delete(String oorg, String orno, Integer oset, String pono,Integer companyNo){
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        JSONObject resultjs = null;
        try {
            DeliveryLineServiceStub deliveryLineServiceStub=new DeliveryLineServiceStub();
            DeliveryLineServiceStub.Delete delete = new DeliveryLineServiceStub.Delete();


            DeliveryLineServiceStub.DeleteRequestType sdreq=new DeliveryLineServiceStub.DeleteRequestType();
            DeliveryLineServiceStub.ControlArea_type0 sca=new DeliveryLineServiceStub.ControlArea_type0();
            DeliveryLineServiceStub.ProcessingScope sps=new DeliveryLineServiceStub.ProcessingScope("request",true);
            sca.setProcessingScope(sps);
            sdreq.setControlArea(sca);
            DeliveryLineServiceStub.DataArea_type0 sda=new DeliveryLineServiceStub.DataArea_type0();
            DeliveryLineServiceStub.DeliveryLine_type0[] sdl=new DeliveryLineServiceStub.DeliveryLine_type0[1];
            sdl[0]=new DeliveryLineServiceStub.DeliveryLine_type0();
            DeliveryLineServiceStub.WhinhOorg_mandatory soorg = new DeliveryLineServiceStub.WhinhOorg_mandatory(oorg,true);
            sdl[0].setOorg(soorg);
            DeliveryLineServiceStub.Tcorno sorno=new DeliveryLineServiceStub.Tcorno();
            sorno.setTcorno(orno);
            sdl[0].setOrno(sorno);
            DeliveryLineServiceStub.Tcwset_mandatory soset=new DeliveryLineServiceStub.Tcwset_mandatory();
            soset.setTcwset_mandatory(oset);
            sdl[0].setOset(soset);
            DeliveryLineServiceStub.Tcpono spono=new DeliveryLineServiceStub.Tcpono();
            BigInteger sspono=new BigInteger(pono);
            spono.setTcpono(sspono);
            sdl[0].setPono(spono);
            sda.setDeliveryLine(sdl);
            sdreq.setDataArea(sda);
            delete.setDeleteRequest(sdreq);
            deliveryLineServiceStub.delete(delete,getActivation(companyNo,null,null));

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
