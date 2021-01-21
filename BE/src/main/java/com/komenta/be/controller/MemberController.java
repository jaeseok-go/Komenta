package com.komenta.be.controller;

import com.komenta.be.model.member.MemberDTO;
import com.komenta.be.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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

    @ApiOperation(value = "로그인", notes = "성공 시 jwt 토큰을 헤더에 넣어서 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_email", value = "유저 이메일", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_password", value = "유저 비밀번호", dataType = "String", required = true)
    })
    @GetMapping("/login")
    public String checkIDAndPw(String u_email, String u_password){

        return u_email;
    }


}
