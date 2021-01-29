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

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


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
            @ApiImplicitParam(name = "member", value = "u_email(이메일), u_pw(비밀번호), u_nickname(닉네임), u_phone_number(휴대전화 번호)", dataType = "MemberDTO", required = true)
    })
    @PostMapping("/join")
    public int createMember(@RequestBody MemberDTO member){
        return mservice.joinMember(member);
    }







    @ApiOperation(value = "회원정보", notes = "회원 일련 번호 정보를 받아서 보여주기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_id", value = "회원 아이디(이메일 아님)", dataType = "int",required = true)
    })
    @GetMapping("/info")
    public MemberDTO getInfoUser(int u_id){
        return mservice.getInfoUser(u_id);
    }


    @ApiOperation(value = "아이디 찾기", notes = "휴대전화 인증 후 해당 요청 시 휴대폰 번호로 아이디 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_phone_number", value = "휴대전화 번호", dataType = "String", required = true)
    })
    @GetMapping("/find_id")
    public String getIdbyPhoneNumber(String u_phone_number){
        return mservice.findId(u_phone_number);
    }

    @ApiOperation(value = "비번 찾기에서 비번 바꾸기", notes = "비번 바꾸자")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_email", value = "이메일", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_pw", value = "비밀번호", dataType = "String", required = true)
    })
    @PutMapping("/change_pw")
    public int updatePassword(@RequestBody MemberDTO dto){
        return mservice.updatePassword(dto);
    }






    @ApiOperation(value = "비밀번호 찾기", notes = "DB에 등록된 아이디인지 확인")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_email", value = "회원 이메일", dataType = "String", required = true),
    })
    @GetMapping("/find_pw")
    public boolean isEamil(String u_email){

        String chkEmail = mservice.findPw(u_email);

        if(chkEmail == null || chkEmail == "") return false;
        else return true;
    }







    @ApiOperation(value = "로그인", notes = "성공 시 jwt 토큰을 헤더에 넣어서 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "member", value = "u_email(이메일), u_pw(비밀번호)", dataType = "MemberDTO", required = true)

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
            @ApiImplicitParam(name = "member", value = "u_id(회원번호), u_email(이메일), u_pw(비밀번호), u_nickname(닉네임), u_phone_number(휴대전화 번호)," +
                    " u_expire_member(멤버쉽 종료일자), u_is_admin(관리자 여부), u_profile_pic(프로필 사진 경로), u_is_blocked(댓글 기능 제한 여부)", dataType = "MemberDTO", required = true)
    })
    @PutMapping("/update")
    public int updateMember(@RequestBody MemberDTO member){
        return mservice.updateMember(member);
    }





    @ApiOperation(value = "회원정보 삭제", notes = "회원 정보를 받아서 delete 후 결과 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_id", value = "회원 번호", dataType = "int", required = true)
    })
    @DeleteMapping("/delete")
    public int deleteMember(@RequestBody int u_id){
        return mservice.deleteMember(u_id);
    }


}
