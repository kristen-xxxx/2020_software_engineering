package com.example.demo.utils;


public class AccessInfo {
    private String accessDate;
    private Integer accessCdt;
    private String userTel;

    public void setInfo(String accessDate, String userTel,Integer accessCdt){
        this.accessDate = accessDate;
        this.userTel = userTel;
        this.accessCdt = accessCdt;
    }

    public String getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(String accessDate) {
        this.accessDate = accessDate;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public Integer getAccessCdt() {
        return accessCdt;
    }

    public void setAccessCdt(Integer accessCdt) {
        this.accessCdt = accessCdt;
    }
}
