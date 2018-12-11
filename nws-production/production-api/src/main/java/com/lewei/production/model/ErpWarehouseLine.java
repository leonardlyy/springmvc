package com.lewei.production.model;

/**
 * Created by 22901 on 2017/3/13.
 */
public class ErpWarehouseLine {
    /**订单类型 */
    private String oorg;
    /**订单号 */
    private String orno;
    /**集 */
    private Integer oset;
    /**仓单行 */
    private String pono;
    /**物料号 */
    private String item;
    /**物料说明 */
    private String itemdsca;
    /**数量 */
    private double qoro;
    /**数量单位 */
    private String orun;
    /**批次选择 */
    private String lsel;
    /**批次 */
    private String clot;
    /**序列号 */
    private String serl;

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

    public String getPono() {
        return pono;
    }

    public void setPono(String pono) {
        this.pono = pono;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItemdsca() {
        return itemdsca;
    }

    public void setItemdsca(String itemdsca) {
        this.itemdsca = itemdsca;
    }

    public double getQoro() {
        return qoro;
    }

    public void setQoro(double qoro) {
        this.qoro = qoro;
    }

    public String getOrun() {
        return orun;
    }

    public void setOrun(String orun) {
        this.orun = orun;
    }

    public String getLsel() {
        return lsel;
    }

    public void setLsel(String lsel) {
        this.lsel = lsel;
    }

    public String getClot() {
        return clot;
    }

    public void setClot(String clot) {
        this.clot = clot;
    }

    public String getSerl() {
        return serl;
    }

    public void setSerl(String serl) {
        this.serl = serl;
    }
}
