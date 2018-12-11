package com.lewei.production.model;

import java.util.Date;

public class IssueRecordLine {
    private Integer id;

    private String method;

    private Integer userid;

    private String oorg;

    private String orno;

    private Integer oset;

    private String pono;

    private String item;

    private String dsca;

    private String qoro;

    private String orun;

    private String lsel;

    private String clot;

    private String serl;

    private String refe;

    private String companyNo;
    private Integer status;
    private Date createDate;
    private Double asynid;
    public IssueRecordLine() {

    }

    public IssueRecordLine(IssueRecord ir) {
        this.id = ir.getId();
        this.method = ir.getMethod();
        this.userid = ir.getUserid();
        this.oorg = ir.getOorg();
        this.orno = ir.getOrno();
        this.oset = ir.getOset();
        this.pono = ir.getPono();
        this.item = ir.getItem();
        this.dsca = ir.getDsca();
        this.qoro = ir.getQoro();
        this.orun = ir.getOrun();
        this.lsel = ir.getLsel();
        this.clot = ir.getClot();
        this.serl = ir.getSerl();
        this.refe = ir.getRefe();
        this.companyNo = ir.getCompanyNo();
    }

    public String getRefe() {
        return refe;
    }

    public void setRefe(String refe) {
        this.refe = refe;
    }

    public Double getAsynid() {
        return asynid;
    }

    public void setAsynid(Double asynid) {
        this.asynid = asynid;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    private Date modifyDate;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getPono() {
        return pono;
    }

    public void setPono(String pono) {
        this.pono = pono == null ? null : pono.trim();
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    public String getDsca() {
        return dsca;
    }

    public void setDsca(String dsca) {
        this.dsca = dsca == null ? null : dsca.trim();
    }

    public String getQoro() {
        return qoro;
    }

    public void setQoro(String qoro) {
        this.qoro = qoro == null ? null : qoro.trim();
    }

    public String getOrun() {
        return orun;
    }

    public void setOrun(String orun) {
        this.orun = orun == null ? null : orun.trim();
    }

    public String getLsel() {
        return lsel;
    }

    public void setLsel(String lsel) {
        this.lsel = lsel == null ? null : lsel.trim();
    }

    public String getClot() {
        return clot;
    }

    public void setClot(String clot) {
        this.clot = clot == null ? null : clot.trim();
    }

    public String getSerl() {
        return serl;
    }

    public void setSerl(String serl) {
        this.serl = serl == null ? null : serl.trim();
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