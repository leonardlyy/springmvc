package com.lewei.production.model;

public class ScanFlag {
    private Integer id;

    private String sfco;

    private String stco;

    private String seri;

    private String orno;

    private Integer oset;

    private Integer num;

    private String companyNo;

    public String getSeri() {
        return seri;
    }

    public void setSeri(String seri) {
        this.seri = seri;
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

    public String getSfco() {
        return sfco;
    }

    public void setSfco(String sfco) {
        this.sfco = sfco == null ? null : sfco.trim();
    }

    public String getStco() {
        return stco;
    }

    public void setStco(String stco) {
        this.stco = stco == null ? null : stco.trim();
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}