package com.test.examplelogin.controller;

import com.test.examplelogin.model.MemberDTO;
import com.test.examplelogin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController{

    @Autowired
    MemberService mservice;

    @GetMapping("/login")
    public String checkIDAndPw(String userid, String userpw){

        MemberDTO result = mservice.getPw(userid);
        String resPw = result.getUserpw();
        if(resPw == null) {
            return "아이디가 존재하지 않습니다.";
        }
        else if(resPw.equals(userpw)) {
            return "로그인 성공!";
        } else {
            return "비밀번호가 틀렸습니다.";
        }
    }


}
