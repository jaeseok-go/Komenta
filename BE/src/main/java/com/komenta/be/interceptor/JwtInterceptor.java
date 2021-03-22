package com.komenta.be.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.komenta.be.service.JwtService;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.net.http.HttpHeaders;
import java.util.Optional;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    public static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getMethod().equals("OPTIONS")) {
            return true;
        }
        // request의 param에서 auth-token을 찾아본다.
        String token = request.getHeader("auth-token");
        
        // 유효한 토큰이면 진행, 그렇지 않으면 예외 발생
        if (token != null && token.length() > 0) {
            Jwts.parser().setSigningKey("VUETOKEN".getBytes()).parseClaimsJws(token);
            return true;
        }
        else {
            throw new RuntimeException("인증 토큰이 없습니다.");
            return false;
        }
    }
}
