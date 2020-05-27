package com.example.demo.controller;

import com.example.demo.entity.Access;
import com.example.demo.entity.User;
import com.example.demo.service.AccessService;
import com.example.demo.service.UserService;
import com.example.demo.utils.AccessInfo;
import com.example.demo.utils.AccessInfoTool;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/access")
public class AccessController {

    @Autowired
    private UserService userService;
    @Autowired
    private AccessService accessService;
    private Access access = new Access();
    private User user=new User();

    @RequestMapping(value = "/report")
    public AccessInfoTool accessReport(HttpServletRequest request) throws ParseException {
        AccessInfoTool reportresult=new AccessInfoTool();
        Integer accessValue=0;

        String userTel =request.getParameter("tel");
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String accessDate = format.format(date);


        Integer accessCdt=0;
        if(request.getParameter("cdt")!=null){
            accessCdt= Integer.parseInt(request.getParameter("cdt"));}


        //判断user是否存在
        user = userService.findByUserTel(userTel);
        if(user==null){
            reportresult.setStatusCode(accessValue);
            return reportresult;
        }

        access=accessService.findByUserTelAndDate(userTel,accessDate);
            Access newaccess = new Access();
            newaccess.setTel(userTel);
            newaccess.setDate(accessDate);
            newaccess.setAccessCdt(accessCdt);
            accessService.save(newaccess);
            accessValue=1;//上报成功


        reportresult.setStatusCode(accessValue);
        return reportresult;
    }

    @RequestMapping(value = "/query")
    public AccessInfoTool query(HttpServletRequest request){
        AccessInfoTool queryResult=new AccessInfoTool();
        Integer queryValue=0;
        String userTel =request.getParameter("tel");

        //获得日期
//        Date date = new Date();
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        String accessDate = format.format(date);

        ArrayList<Access> accesss;
        accesss=accessService.findByUserTel (userTel);
        ArrayList<AccessInfo> accessInfos=new ArrayList<>();

        if(accesss.size()>0){
            for (Access eachaccess:accesss) {
                AccessInfo accessInfo=new AccessInfo();
                String date=eachaccess.getDate();
                String tel=eachaccess.getTel();
                // Float temp=eachaccess.getTemp();
                Integer cdt=eachaccess.getAccessCdt();
                accessInfo.setInfo(date,tel,cdt);
                accessInfos.add(accessInfo);
            }
            queryResult.setData(accessInfos);
            queryValue=1;

        }

        queryResult.setStatusCode(queryValue);
        return queryResult;
    }

    @RequestMapping(value = "/queryall")
    public AccessInfoTool queryall(HttpServletRequest request){
        AccessInfoTool queryResult=new AccessInfoTool();
        Integer queryValue=0;
//        String userTel =request.getParameter("tel");

        //获得日期
//        Date date = new Date();
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        String accessDate = format.format(date);

        ArrayList<Access> accesss;
        accesss=accessService.findAll ();
        ArrayList<AccessInfo> accessInfos=new ArrayList<>();

        if(accesss.size()>0){
            for (Access eachaccess:accesss) {
                AccessInfo accessInfo=new AccessInfo();
                String date=eachaccess.getDate();
                String tel=eachaccess.getTel();
                // Float temp=eachaccess.getTemp();
                Integer cdt=eachaccess.getAccessCdt();
                accessInfo.setInfo(date,tel,cdt);
                accessInfos.add(accessInfo);
            }
            queryResult.setData(accessInfos);
            queryValue=1;

        }

        queryResult.setStatusCode(queryValue);
        return queryResult;
    }


}
