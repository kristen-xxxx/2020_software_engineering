package com.example.demo.respository;

import com.example.demo.entity.Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public interface AccessRepository extends JpaRepository<Access,Long> {

    @Query("select s from Access s where s.userTel = ?1 and s.accessDate = ?2")
    Access findByUserTelAndDate (String userTel, String accessDate);

//    @Query("select s from Access s where s.userTel = ?1")
//    Access findByUserTel (String userTel);

    @Query("select s from Access s where s.userTel = ?1")
    ArrayList<Access> findByUserTel (String userTel);

    @Query("select s from Access s")
    ArrayList<Access> findAll ();

}