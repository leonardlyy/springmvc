package com.lewei.production.model;

import java.util.Date;

public class UserDutyAuthority {

    private Integer id;

    private Integer dutyid;

    private Integer authorityid;

    private Authority module;

    public Authority getModule() {
        return module;
    }

    public void setModule(Authority module) {
        this.module = module;
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

    public Integer getAuthorityid() {
        return authorityid;
    }

    public void setAuthorityid(Integer authorityid) {
        this.authorityid = authorityid;
    }

   }