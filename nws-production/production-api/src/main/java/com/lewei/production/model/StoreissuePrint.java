package com.lewei.production.model;

import java.util.Date;

public class StoreissuePrint {
    private Integer id;

    private String tCwar;

    private String tLoca;

    private String tItem;

    private Integer tNum;

    private Date createDate;

    private Date modifyDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettCwar() {
        return tCwar;
    }

    public void settCwar(String tCwar) {
        this.tCwar = tCwar == null ? null : tCwar.trim();
    }

    public String gettLoca() {
        return tLoca;
    }

    public void settLoca(String tLoca) {
        this.tLoca = tLoca == null ? null : tLoca.trim();
    }

    public String gettItem() {
        return tItem;
    }

    public void settItem(String tItem) {
        this.tItem = tItem == null ? null : tItem.trim();
    }

    public Integer gettNum() {
        return tNum;
    }

    public void settNum(Integer tNum) {
        this.tNum = tNum;
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

    @Override
    public String toString() {
        return "StoreissuePrint{" +
                "id=" + id +
                ", tCwar='" + tCwar + '\'' +
                ", tLoca='" + tLoca + '\'' +
                ", tItem='" + tItem + '\'' +
                ", tNum=" + tNum +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}