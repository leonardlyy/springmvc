package com.lewei.production.model;
/**
 * Created by Simon on 2018/11/16.
 */
public class ErpPurchaseOrdeLine {
	/**采购订单*/
    private String orno;
    /**行*/
    private Integer pono;
    /**序号*/
    private Integer ponb;
    /**物料号 */
    private String item;
    /**物料说明 */
    private String dsca;
    /**采购单位 */
    private String cuni;
    /**供应商 */
    private String bpid;   
    /** 仓库*/
    private String cwar;
    /**仓库说明 */
    private String cwardsca;
    /**采购数量*/
    private double qana;
    /**批次控制*/
    private Integer ltct;
    /**库位管理*/
    private Integer sloc;
    /*业务伙伴说明*/
    private String bpidname;
    
	public String getOrno() {
		return orno;
	}
	public void setOrno(String orno) {
		this.orno = orno;
	}
	public Integer getPono() {
		return pono;
	}
	public void setPono(Integer pono) {
		this.pono = pono;
	}
	public Integer getPonb() {
		return ponb;
	}
	public void setPonb(Integer ponb) {
		this.ponb = ponb;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getDsca() {
		return dsca;
	}
	public void setDsca(String dsca) {
		this.dsca = dsca;
	}
	public String getCuni() {
		return cuni;
	}
	public void setCuni(String cuni) {
		this.cuni = cuni;
	}
	public String getBpid() {
		return bpid;
	}
	public void setBpid(String bpid) {
		this.bpid = bpid;
	}
	public String getCwar() {
		return cwar;
	}
	public void setCwar(String cwar) {
		this.cwar = cwar;
	}
	public String getCwardsca() {
		return cwardsca;
	}
	public void setCwardsca(String cwardsca) {
		this.cwardsca = cwardsca;
	}
	public double getQana() {
		return qana;
	}
	public void setQana(double qana) {
		this.qana = qana;
	}
	public Integer getLtct() {
		return ltct;
	}
	public void setLtct(Integer ltct) {
		this.ltct = ltct;
	}
	public Integer getSloc() {
		return sloc;
	}
	public void setSloc(Integer sloc) {
		this.sloc = sloc;
	}
	public String getBpidname() {
		return bpidname;
	}
	public void setBpidname(String bpidname) {
		this.bpidname = bpidname;
	}
	 
    
    
}
