package com.komenta.be.config;

import com.komenta.be.interceptor.AdminInterceptor;
import com.komenta.be.interceptor.JwtInterceptor;
import com.komenta.be.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/member/login", "/member/join");
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin");
    }
}
