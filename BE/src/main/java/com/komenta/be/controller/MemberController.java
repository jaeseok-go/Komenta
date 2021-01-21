package com.komenta.be.controller;

import com.komenta.be.model.member.MemberDTO;
import com.komenta.be.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value ="/member")
public class MemberController{

    @Autowired
    MemberService mservice;

    @ApiOperation(value = "login", notes = "로그인")
    @GetMapping("/login")
    public ResponseEntity checkIDAndPw(String userid, String userpw){

        return ResponseEntity.ok(userid);
    }


}
