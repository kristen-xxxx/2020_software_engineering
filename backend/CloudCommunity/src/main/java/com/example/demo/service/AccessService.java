package com.example.demo.service;

import com.example.demo.entity.Access;
import com.example.demo.respository.AccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AccessService {

    @Autowired
    private AccessRepository accessRepository;

    public Access findByUserTelAndDate(String tel, String accessDate)
    {
        return accessRepository.findByUserTelAndDate (tel,accessDate);
    }

    public ArrayList<Access> findByUserTel(String tel)
    {
        return accessRepository.findByUserTel (tel);
    }

    public ArrayList<Access> findAll()
    {
        return accessRepository.findAll();
    }

    public void save (Access access)
    {
        accessRepository.save (access);
    }
    public void update (Access access)
    {
        accessRepository.save (access);
    }
}
