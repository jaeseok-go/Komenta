package com.komenta.be.service;
import java.util.Map;

import com.komenta.be.model.member.MemberDTO;
import io.jsonwebtoken.Claims;

public interface JwtService {

    //	로그인 성공시 사용자 정보를 기반으로 JWTToken을 생성하여 반환.
    public String create(MemberDTO member);

    //	전달 받은 토큰이 제대로 생성된것이니 확인 하고 문제가 있다면 RuntimeException을 발생.
    public void checkValid(String jwt);

    //	JWT Token을 분석해서 필요한 정보를 반환.
    public Map<String, Object> get(String jwt);

    public int getUidFromJwt(String jwt);

}
