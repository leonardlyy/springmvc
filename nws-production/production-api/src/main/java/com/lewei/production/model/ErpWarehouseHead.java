package com.lewei.production.model;

/**
 * Created by 22901 on 2017/3/13.
 */
public class ErpWarehouseHead {
    /**订单类型 */
    private String oorg;
    /**订单号 */
    private String orno;
    /**集 */
    private Integer oset;
    /**事务处理类型 */
    private String ittp;
    /**仓单类型 */
    private String otyp;
    /**系列 */
    private String seri;
    /**仓库 */
    private String sfco;
    /**库位 */
    private String sflo;
    /**工作中心 */
    private String stco;

    public String getOorg() {
        return oorg;
    }

    public void setOorg(String oorg) {
        this.oorg = oorg;
    }

    public String getOrno() {
        return orno;
    }

    public void setOrno(String orno) {
        this.orno = orno;
    }

    public Integer getOset() {
        return oset;
    }

    public void setOset(Integer oset) {
        this.oset = oset;
    }

    public String getIttp() {
        return ittp;
    }

    public void setIttp(String ittp) {
        this.ittp = ittp;
    }

    public String getOtyp() {
        return otyp;
    }

    public void setOtyp(String otyp) {
        this.otyp = otyp;
    }

    public String getSeri() {
        return seri;
    }

    public void setSeri(String seri) {
        this.seri = seri;
    }

    public String getSfco() {
        return sfco;
    }

    public void setSfco(String sfco) {
        this.sfco = sfco;
    }

    public String getSflo() {
        return sflo;
    }

    public void setSflo(String sflo) {
        this.sflo = sflo;
    }

    public String getStco() {
        return stco;
    }

    public void setStco(String stco) {
        this.stco = stco;
    }
}
