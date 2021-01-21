package com.komenta.be.controller;

import com.komenta.be.model.member.AuthEmailDTO;
import com.komenta.be.model.member.AuthPhoneDTO;
import com.komenta.be.model.member.MemberDTO;
import com.komenta.be.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value ="/member")
public class MemberController{

    @Autowired
    MemberService mservice;



    @ApiOperation(value = "회원가입", notes = "회원 정보를 받아서 create 후 결과 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "member", value = "회원 정보", dataType = "MemberDTO", required = true)
    })
    @PostMapping("/join")
    public boolean createMember(MemberDTO member){

        return true;
    }



    @ApiOperation(value = "로그인", notes = "성공 시 jwt 토큰을 헤더에 넣어서 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_email", value = "유저 이메일", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_password", value = "유저 비밀번호", dataType = "String", required = true)
    })
    @GetMapping("/login")
    public boolean loginMemeber(String u_email, String u_password){

        return true;
    }



    @ApiOperation(value = "회원정보 수정", notes = "회원 정보를 받아서 update 후 결과 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "member", value = "회원 정보", dataType = "MemberDTO", required = true)
    })
    @PutMapping("/update")
    public boolean updateMember(MemberDTO member){

        return true;
    }



    @ApiOperation(value = "회원정보 삭제", notes = "회원 정보를 받아서 delete 후 결과 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_email", value = "회원 아이디", dataType = "String", required = true)
    })
    @DeleteMapping("/delete")
    public boolean deleteMember(String u_email){

        return true;
    }



    @ApiOperation(value = "휴대폰 인증", notes = "인증번호를 만들어서 아이디와 함께 클라이언트에 반환하고 휴대폰에는 문자 전송")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_phone_number", value = "회원 휴대전화 번호", dataType = "String", required = true)
    })
    @GetMapping("/authPhone")
    public AuthPhoneDTO authPhone(String u_phone_number){

        return new AuthPhoneDTO();
    }



    @ApiOperation(value = "이메일 인증", notes = "인증번호를 만들어서 비밀번호와 함께 클라이언트에는 반환하고 회원 메일로는 이메일 전송")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_email", value = "회원 아이디", dataType = "String", required = true)
    })
    @GetMapping("/authEmail")
    public AuthEmailDTO authEmail(String u_email){

        return new AuthEmailDTO();
    }


}
