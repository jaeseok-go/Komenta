package com.komenta.be.controller;

import com.komenta.be.model.member.AuthPhoneDTO;
import com.komenta.be.model.member.MemberDTO;
import com.komenta.be.service.JwtService;
import com.komenta.be.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


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
    public AuthPhoneDTO getIdbyPhoneNumber(String u_phone_number){

        // 인증 번호 생성
        Random rand = new Random();
        String auth_number = "";
        for(int i=0; i<4; i++){
            String ran = Integer.toString(rand.nextInt(10));
            auth_number += ran;
        }

        // SMS 인증 서비스 API env
        String api_key = "테스트할때는 쓰기 아깝네요";
        String api_secret = "허허허";
        net.nurigo.java_sdk.api.Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", u_phone_number);    // 수신전화번호
        params.put("from", "번호쓰는곳");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", " 인증번호는 " + "[ "+auth_number+" ]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

        AuthPhoneDTO result = new AuthPhoneDTO(Integer.parseInt(auth_number), mservice.findId(u_phone_number));

        return result;
    }






    @ApiOperation(value = "비밀번호 바꾸기", notes = "바꿀 비밀번호를 입력받아 update후 결과 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_email", value = "이메일", dataType = "String", required = true),
            @ApiImplicitParam(name = "u_pw", value = "비밀번호", dataType = "String", required = true)
    })
    @PutMapping("/change_pw")
    public int updatePassword(@RequestBody MemberDTO member){
        return mservice.updatePassword(member);
    }






    @ApiOperation(value = "아이디 가입 여부 확인" , notes = "DB에 등록된 아이디인지 확인")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_email", value = "회원 이메일", dataType = "String", required = true),
    })
    @GetMapping("/chk_id")
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
            System.out.println("controller : " + getMember);
            if(getMember.getU_pw().equals(member.getU_pw())) {
                String token = jwtService.create(getMember);
                
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
    public ResponseEntity<Map<String, Object>> updateMember(@RequestBody MemberDTO member, HttpServletResponse response){
        HttpStatus status = null;
        Map<String, Object> resultMap = new HashMap<>();
        try{
            int result = mservice.updateMember(member);
            if(result == 1) {
                String token = jwtService.create(member);

                response.setHeader("auth-token", token);
                resultMap.put("status", true);
                resultMap.put("data", result);
                resultMap.put("auth-token", token);
                status = HttpStatus.ACCEPTED;
                System.out.println(response.getHeader("auth-token"));
            }
            else{
                resultMap.put("status", false);
                resultMap.put("data", 0);
                resultMap.put("auth-token", null);

                System.out.println("업데이트 실패");
            }
        }
        catch(RuntimeException e){
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
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
