package com.lewei.production.model;

import java.util.Date;

public class PrintRecordItem {
    private Integer id;

    private String code;

    private String cwar;

    private String item;

    private String number;

    private Integer userid;

    private Date createDate;

    private Date modifyDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCwar() {
        return cwar;
    }

    public void setCwar(String cwar) {
        this.cwar = cwar == null ? null : cwar.trim();
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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
        return "PrintRecordItem{" +
                "id=" + id +
                ", cwar='" + cwar + '\'' +
                ", item='" + item + '\'' +
                ", number='" + number + '\'' +
                ", userid=" + userid +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}