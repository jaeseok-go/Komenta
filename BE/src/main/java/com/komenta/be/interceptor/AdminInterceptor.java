package com.komenta.be.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.komenta.be.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    public static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info(request.getMethod() + " : " + request.getServletPath());
        String token = request.getHeader("auth-token");
        return getUidFromJwt(token);
    }

    public Map<String, Object> get(String jwt) {
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey("VUETOKEN".getBytes()).parseClaimsJws(jwt);
        } catch (final Exception e) {
            throw new RuntimeException();
        }

        logger.info("claims : {}", claims);
        // Claims는 Map의 구현체이다.
        return claims.getBody();
    }

    public boolean getUidFromJwt(String jwt){
        boolean admin = (boolean) get(jwt).get("is_admin");
        return admin;
    }
}
