package com.lewei.production.model;

import java.util.Date;

public class User {
    private Integer id;

    private Integer dutyid;

    private String name;

    private String username;

    private String password;

    private String tel;

    private String email;

    private String companyNo;

    private  String warehouseNo;

    private  String warehouseNo1;

    private String seri;

    private String seriN;

    private String otyp;

    private String len;

    private String status;

    private String defaultprinter;

    private Date lastvisitdate;

    private Date createDate;

    private Date modifyDate;

    private UserDuty userDuty;

    public String getSeriN() {
        return seriN;
    }

    public void setSeriN(String seriN) {
        this.seriN = seriN;
    }

    public String getWarehouseNo1() {
        return warehouseNo1;
    }

    public void setWarehouseNo1(String warehouseNo1) {
        this.warehouseNo1 = warehouseNo1;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getWarehouseNo() {
        return warehouseNo;
    }

    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastvisitdate() {
        return lastvisitdate;
    }

    public void setLastvisitdate(Date lastvisitdate) {
        this.lastvisitdate = lastvisitdate;
    }

    private UserDutyAuthority userDutyAuthority;

    public UserDuty getUserDuty() {
        return userDuty;
    }

    public void setUserDuty(UserDuty userDuty) {
        this.userDuty = userDuty;
    }

    public UserDutyAuthority getUserDutyAuthority() {
        return userDutyAuthority;
    }

    public void setUserDutyAuthority(UserDutyAuthority userDutyAuthority) {
        this.userDutyAuthority = userDutyAuthority;
    }

    public String getSeri() {
        return seri;
    }

    public void setSeri(String seri) {
        this.seri = seri;
    }

    public String getOtyp() {
        return otyp;
    }

    public void setOtyp(String otyp) {
        this.otyp = otyp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDutyid() {
        return dutyid;
    }

    public void setDutyid(Integer dutyid) {
        this.dutyid = dutyid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getLen() {
        return len;
    }

    public void setLen(String len) {
        this.len = len == null ? null : len.trim();
    }

    public String getDefaultprinter() {
        return defaultprinter;
    }

    public void setDefaultprinter(String defaultprinter) {
        this.defaultprinter = defaultprinter == null ? null : defaultprinter.trim();
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