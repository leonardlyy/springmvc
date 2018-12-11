package com.lewei.production.model;

import java.util.Date;

public class IssueRecordHead {
    private Integer id;

    private String method;

    private Integer userid;

    private String oorg;

    private String orno;

    private Integer oset;

    private String ittp;

    private String otyp;

    private String seri;

    private String sfco;

    private String sflo;

    private String stco;

    private String companyNo;

    private Integer status;

    private Date createDate;

    private Date modifyDate;
private Double asynid;

    public IssueRecordHead() {

    }

    public IssueRecordHead(IssueRecord ir) {
        this.id = ir.getId();
        this.method = ir.getMethod();
        this.userid = ir.getUserid();
        this.oorg = ir.getOorg();
        this.orno = ir.getOrno();
        this.oset = ir.getOset();
        this.ittp = ir.getIttp();
        this.otyp = ir.getOtyp();
        this.seri = ir.getSeri();
        this.sfco = ir.getSfco();
        this.sflo = ir.getSflo();
        this.stco = ir.getStco();
        this.companyNo = ir.getCompanyNo();
    }

    public Double getAsynid() {
        return asynid;
    }

    public void setAsynid(Double asynid) {
        this.asynid = asynid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getOorg() {
        return oorg;
    }

    public void setOorg(String oorg) {
        this.oorg = oorg == null ? null : oorg.trim();
    }

    public String getOrno() {
        return orno;
    }

    public void setOrno(String orno) {
        this.orno = orno == null ? null : orno.trim();
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
        this.ittp = ittp == null ? null : ittp.trim();
    }

    public String getOtyp() {
        return otyp;
    }

    public void setOtyp(String otyp) {
        this.otyp = otyp == null ? null : otyp.trim();
    }

    public String getSeri() {
        return seri;
    }

    public void setSeri(String seri) {
        this.seri = seri == null ? null : seri.trim();
    }

    public String getSfco() {
        return sfco;
    }

    public void setSfco(String sfco) {
        this.sfco = sfco == null ? null : sfco.trim();
    }

    public String getSflo() {
        return sflo;
    }

    public void setSflo(String sflo) {
        this.sflo = sflo == null ? null : sflo.trim();
    }

    public String getStco() {
        return stco;
    }

    public void setStco(String stco) {
        this.stco = stco == null ? null : stco.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}