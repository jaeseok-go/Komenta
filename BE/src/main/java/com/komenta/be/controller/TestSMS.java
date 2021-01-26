package com.komenta.be.controller;


import com.komenta.be.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
@RestController
@RequestMapping(value ="/check")
public class TestSMS {
    @ApiOperation(value = "휴대폰 인증", notes = "인증번호를 만들어서 아이디와 함께 클라이언트에 반환하고 휴대폰에는 문자 전송")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u_phone_number", value = "회원 휴대전화 번호", dataType = "String", required = true)
    })
    @GetMapping("/sendSMS")
    public String sendSMS(String phoneNumber){
        Random rand = new Random();
        String numStr = "";
        for(int i=0; i<4; i++){
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }

        System.out.println("수신자 번호 : "+phoneNumber);
        System.out.println("인증 번호 : "+numStr);
        certifiedPhoneNumber(phoneNumber,numStr);

        return numStr;
    }
    public void certifiedPhoneNumber(String phoneNumber, String cerNum) {

        String api_key = "테스트할때는 쓰기 아깝네요";
        String api_secret = "허허허";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("from", "번호쓰는곳");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "신기해~~~~ : 인증번호는" + "["+cerNum+"]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

    }

}
