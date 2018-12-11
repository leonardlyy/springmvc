package com.lewei.production.model;

import java.util.Date;

/**
 * Created by 22901 on 2017/3/9.
 */
public class Other {

    private String code;

    private  String dsca;

    private  String grid;

    private String companyNo;

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDsca() {
        return dsca;
    }

    public void setDsca(String dsca) {
        this.dsca = dsca;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Other other = (Other) o;

        if (code != null ? !code.equals(other.code) : other.code != null) return false;
        if (dsca != null ? !dsca.equals(other.dsca) : other.dsca != null) return false;
        return companyNo != null ? companyNo.equals(other.companyNo) : other.companyNo == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (dsca != null ? dsca.hashCode() : 0);
        result = 31 * result + (companyNo != null ? companyNo.hashCode() : 0);
        return result;
    }
}
