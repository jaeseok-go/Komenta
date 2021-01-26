package com.komenta.be.controller;

import com.komenta.be.model.member.AuthEmailDTO;
import com.komenta.be.model.member.AuthPhoneDTO;
import com.komenta.be.model.member.MemberDTO;
import com.komenta.be.service.JwtService;
import com.komenta.be.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value ="/member")
public class MemberController{

    @Autowired
    MemberService mservice;

    @Autowired
    JwtService jwtService;

    @ApiOperation(value = "회원가입", notes = "회원 정보를 받아서 create 후 결과 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "member", value = "회원 정보", dataType = "MemberDTO", required = true)
    })
    @PostMapping("/join")
    public int createMember(MemberDTO member){
        int result = mservice.joinMember(member);
        return result;
    }



    @ApiOperation(value = "로그인", notes = "성공 시 jwt 토큰을 헤더에 넣어서 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_email", value = "유저 이메일", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_password", value = "유저 비밀번호", dataType = "String", required = true)
    })
    @PostMapping("/login")
    public boolean loginMember(String u_email, String u_password, HttpServletResponse response){
        MemberDTO member=  mservice.getInfoUser(u_email);
        if(member.getU_pw().equals(u_password)){
            // 성공하면 jwt token create
            String token = jwtService.create(member);
            response.setHeader("auth-token", token);
            return true;
        }
        return false;
    }



    @ApiOperation(value = "회원정보 수정", notes = "회원 정보를 받아서 update 후 결과 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "member", value = "회원 정보", dataType = "MemberDTO", required = true)
    })
    @PutMapping("/update")
    public int updateMember(MemberDTO member){
        return mservice.updateMember(member);
    }



    @ApiOperation(value = "회원정보 삭제", notes = "회원 정보를 받아서 delete 후 결과 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_email", value = "회원 아이디", dataType = "String", required = true)
    })
    @DeleteMapping("/delete")
    public int deleteMember(String u_email){
        return mservice.deleteMember(mservice.getInfoUser(u_email).getU_id());
    }


}
