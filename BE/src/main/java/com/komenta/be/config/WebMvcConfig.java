package com.komenta.be.config;

import com.komenta.be.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/member/login/**", "/member/join/**", "/check/**", "/auth/**",
                        "/static/", "/swagger-ui.html", "/webjars/**", "admin/**", "/v2/api-docs", "/configuration/security",
                        "/configuration/ui", "/swagger-resources/**", "/member/dup_id_chk/**", "/member/dup_nickname_chk/**",
                        "/member/find_id/**", "/member/chk_id/**", "/member/change_pw/**");
    }
}
