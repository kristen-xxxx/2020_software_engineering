package com.example.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "access")
public class Access {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "user_id")
    private Integer userId;
    //@Column(name = "user_tel")
    private String userTel;
    //@Column(name = "access_date")
    private String accessDate;
    //@Column(name = "access_cdt")
    private Integer accessCdt;

    public Access(){

    }

    public Integer getId() {
        return userId;
    }
    public void setId(Integer id) {
        this.userId = id;
    }

    public String getTel() {
        return userTel;
    }
    public void setTel(String userTel) {
        this.userTel = userTel;
    }

    public String getDate() {
        return accessDate;
    }
    public void setDate(String accessDate) {
        this.accessDate = accessDate;
    }

    public Integer getAccessCdt() {
        return accessCdt;
    }

    public void setAccessCdt(Integer accessCdt) {
        this.accessCdt = accessCdt;
    }
}
