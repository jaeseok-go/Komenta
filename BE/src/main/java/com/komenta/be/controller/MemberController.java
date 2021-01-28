package com.komenta.be.controller;

import com.komenta.be.model.member.MemberDTO;
import com.komenta.be.service.JwtService;
import com.komenta.be.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value ="/member")
public class MemberController{

    @Autowired
    MemberService mservice;

    @Autowired
    JwtService jwtService;

    @ApiOperation(value = "회원가입", notes = "회원 정보를 받아서 create 후 결과 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_id", value = "회원 아이디(이메일 아님)", dataType = "int"),
            @ApiImplicitParam(name = "u_email", value = "회원 이메일", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_pw", value = "회원 비밀번호", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_phone_number", value = "회원 휴대전화 번호", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_nickname", value = "회원 닉네임", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_expire_member", value = "멤버쉽 종료일자", dataType = "String"),
            @ApiImplicitParam(name = "u_is_admin", value = "회원 관리자 여부", dataType = "boolean"),
            @ApiImplicitParam(name = "u_profile_pic", value = "회원 프로필 사진 경로", dataType = "String"),
            @ApiImplicitParam(name = "u_is_blocked", value = "회원 댓글기능 차단 여부", dataType = "boolean")
    })
    @PostMapping("/join")
    public int createMember(@RequestBody MemberDTO member){
        return mservice.joinMember(member);
    }





    @ApiOperation(value = "회원정보", notes = "회원 일련 번호 정보를 받아서 보여주기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_id", value = "회원 아이디(이메일 아님)", dataType = "int", required = true),
    })
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getInfoUser(@RequestBody int u_id, HttpServletRequest req){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try{
            MemberDTO info = mservice.getInfoUser(u_id);
            resultMap.putAll(jwtService.get(req.getHeader("auth-token")));
            resultMap.put("status", true);
            resultMap.put("info", info);
            resultMap.put("request_body", u_id);
            status = HttpStatus.ACCEPTED;
        }
        catch (RuntimeException e){
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }





    @ApiOperation(value = "로그인", notes = "성공 시 jwt 토큰을 헤더에 넣어서 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_id", value = "회원 아이디(이메일 아님)", dataType = "int"),
            @ApiImplicitParam(name = "u_email", value = "회원 이메일", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_pw", value = "회원 비밀번호", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_phone_number", value = "회원 휴대전화 번호", dataType = "String"),
            @ApiImplicitParam(name = "u_nickname", value = "회원 닉네임", dataType = "String"),
            @ApiImplicitParam(name = "u_expire_member", value = "멤버쉽 종료일자", dataType = "String"),
            @ApiImplicitParam(name = "u_is_admin", value = "회원 관리자 여부", dataType = "boolean"),
            @ApiImplicitParam(name = "u_profile_pic", value = "회원 프로필 사진 경로", dataType = "String"),
            @ApiImplicitParam(name = "u_is_blocked", value = "회원 댓글기능 차단 여부", dataType = "boolean")
    })
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginMember(@RequestBody MemberDTO member, HttpServletResponse response){
       HttpStatus status = null;
        Map<String, Object> resultMap = new HashMap<>();
        try{
            MemberDTO getMember = mservice.getMyInfo(member.getU_email());
            if(getMember.getU_pw().equals(member.getU_pw())) {
                String token = jwtService.create(member);
                
                response.setHeader("auth-token", token);
                resultMap.put("status", true);
                resultMap.put("data", getMember);
                resultMap.put("auth-token", token);
                status = HttpStatus.ACCEPTED;
                System.out.println(response.getHeader("auth-token"));
            }
            else{
                System.out.println("아디 비번 다름");
            }
        }
        catch(RuntimeException e){
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }




    @ApiOperation(value = "회원정보 수정", notes = "회원 정보를 받아서 update 후 결과 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_id", value = "회원 아이디(이메일 아님)", dataType = "int", required = true),
            @ApiImplicitParam(name = "u_email", value = "회원 이메일", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_pw", value = "회원 비밀번호", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_phone_number", value = "회원 휴대전화 번호", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_nickname", value = "회원 닉네임", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_expire_member", value = "멤버쉽 종료일자", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_is_admin", value = "회원 관리자 여부", dataType = "boolean", required = true),
            @ApiImplicitParam(name = "u_profile_pic", value = "회원 프로필 사진 경로", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_is_blocked", value = "회원 댓글기능 차단 여부", dataType = "boolean", required = true)
    })
    @PutMapping("/update")
    public int updateMember(@RequestBody MemberDTO member){
        return mservice.updateMember(member);
    }





    @ApiOperation(value = "회원정보 삭제", notes = "회원 정보를 받아서 delete 후 결과 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_id", value = "회원 아이디(이메일 아님)", dataType = "int", required = true)
    })
    @DeleteMapping("/delete")
    public int deleteMember(@RequestBody int u_id){
        return mservice.deleteMember(u_id);
    }


}
