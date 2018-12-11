package com.lewei.production.model;

import java.util.Date;

/**
 * Created by 马路遥 on 2017/6/6.
 */
public class OsetFlag {
    private Integer id;

    private Integer scanId;

    private String sflo;

    private Integer oset;

    private Date modifyDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScanId() {
        return scanId;
    }

    public void setScanId(Integer scanId) {
        this.scanId = scanId;
    }

    public String getSflo() {
        return sflo;
    }

    public void setSflo(String sflo) {
        this.sflo = sflo;
    }

    public Integer getOset() {
        return oset;
    }

    public void setOset(Integer oset) {
        this.oset = oset;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
