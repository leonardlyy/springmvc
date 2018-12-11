package com.lewei.production.model;

import java.util.Date;

public class ErpWarehouseReceipt {

	/** 采购订单 */
	private String orno;
	/** 行 */
	private Integer pono;
	/** 序号 */
	private Integer ponb;
	/** 收货日期 */
	private Date odat;
	/** 物料号 */
	private String item;
	/** 采购数量 */
	private double qoor;
	/** 仓库 */
	private String cwar;
	/** 库位 */
	private String loca;
	/** 批次 */
	private String clot;
	private String refa;
	private Date trdt;
	private Integer seqn;
	private Integer stat;
	private String error;
	private String whno;
	private Integer pnoo;
	  private Integer refc;
	    private Integer refu;
	private String companyNo;

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

	public Date getOdat() {
		return odat;
	}

	public void setOdat(Date odat) {
		this.odat = odat;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getQoor() {
		return qoor;
	}

	public void setQoor(double qoor) {
		this.qoor = qoor;
	}

	public String getCwar() {
		return cwar;
	}

	public void setCwar(String cwar) {
		this.cwar = cwar;
	}

	public String getLoca() {
		return loca;
	}

	public void setLoca(String loca) {
		this.loca = loca;
	}

	public String getClot() {
		return clot;
	}

	public void setClot(String clot) {
		this.clot = clot;
	}

	public String getRefa() {
		return refa;
	}

	public void setRefa(String refa) {
		this.refa = refa;
	}

	public Date getTrdt() {
		return trdt;
	}

	public void setTrdt(Date trdt) {
		this.trdt = trdt;
	}

	public Integer getSeqn() {
		return seqn;
	}

	public void setSeqn(Integer seqn) {
		this.seqn = seqn;
	}

	public Integer getStat() {
		return stat;
	}

	public void setStat(Integer stat) {
		this.stat = stat;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getWhno() {
		return whno;
	}

	public void setWhno(String whno) {
		this.whno = whno;
	}

	public Integer getPnoo() {
		return pnoo;
	}

	public void setPnoo(Integer pnoo) {
		this.pnoo = pnoo;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public Integer getRefc() {
		return refc;
	}

	public void setRefc(Integer refc) {
		this.refc = refc;
	}

	public Integer getRefu() {
		return refu;
	}

	public void setRefu(Integer refu) {
		this.refu = refu;
	}
 
}
