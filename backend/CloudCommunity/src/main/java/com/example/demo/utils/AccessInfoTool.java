package com.example.demo.utils;

import com.example.demo.entity.Access;

import java.util.ArrayList;

public class AccessInfoTool {
    private int statusCode;// 返回码
    private ArrayList<AccessInfo> data;// 数据源


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ArrayList<AccessInfo> getData() {
        return data;
    }

    public void setData(ArrayList<AccessInfo> data) {
        this.data = data;
    }
}
