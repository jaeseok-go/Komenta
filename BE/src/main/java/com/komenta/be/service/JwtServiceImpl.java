package com.komenta.be.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.komenta.be.model.member.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService{

    public static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    private String signature = "VUETOKEN";
    private Long expireMin = 5L;

    //	로그인 성공시 사용자 정보를 기반으로 JWTToken을 생성하여 반환.
    public String create(MemberDTO member) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        Map<String, Object> payloads = new HashMap<>();
        Long expiredTime = 1000 * 60 * expireMin;
        Date now = new Date();
        now.setTime(now.getTime()+expiredTime);
        payloads.put("u_id", member.getU_id());
        payloads.put("u_email", member.getU_email());
        payloads.put("u_phone_number", member.getU_phone_number());
        payloads.put("u_nickname", member.getU_nickname());
        payloads.put("u_expire_member", member.getU_expire_member());
        payloads.put("is_admin", member.isU_is_admin());
        payloads.put("u_profile_pic", member.getU_profile_pic());

        String jwt = Jwts.builder().setHeader(headers).setClaims(payloads).signWith(SignatureAlgorithm.HS256, signature.getBytes()).compact();
        System.out.println(jwt);
        return jwt;
//        JwtBuilder jwtBuilder = Jwts.builder();
////		JWT Token = Header + Payload + Signature
//
////		Header 설정
//        jwtBuilder.setHeaderParam("typ", "JWT"); // 토큰의 타입으로 고정 값.
//
////		Payload 설정
//        jwtBuilder
//                .setSubject("로그인토큰") // 토큰의 제목 설정
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin)) // 유효기간 설정
//                .claim("u_id", member.getU_id())
//                .claim("is_admin", member.isU_is_admin());
//                /*
//                    is_admin 으로 회원 정보를 바꿨을 때, jwt의 정보가 바뀌지않아서 문제가 생길수있다..?
//                    토큰은 클라이언트에 저장되어 데이터베이스에서 사용자 정보를 조작하더라도 토큰에 직접 적용할 수 없습니다.
//                    (http://www.opennaru.com/opennaru-blog/jwt-json-web-token/)
//                 */
////		signature 설정
//        jwtBuilder.signWith(SignatureAlgorithm.HS256, signature.getBytes());
//        System.out.println(jwtBuilder.toString());
////		마지막 직렬화 처리
//        String jwt = jwtBuilder.compact();
//        logger.info("jwt : {}", jwt);
//
//        return jwt;
    }

    //	전달 받은 토큰이 제대로 생성된것이니 확인 하고 문제가 있다면 RuntimeException을 발생.
    public void checkValid(String jwt) {
//		예외가 발생하지 않으면 OK
        Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
    }

    //	JWT Token을 분석해서 필요한 정보를 반환.
    public Map<String, Object> get(String jwt) {
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
        } catch (final Exception e) {
            throw new RuntimeException();
        }

        logger.info("claims : {}", claims);
        // Claims는 Map의 구현체이다.
        return claims.getBody();
    }

}