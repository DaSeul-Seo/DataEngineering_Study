package com.example.basic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.basic.config.interceptor.BasicInterceptor;

// WebMvcConfigurer를 사용하기 위한 설정
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(new BasicInterceptor())
                .addPathPatterns("/**") // "/**" : 모든 url
                .excludePathPatterns("/admin/**"); // 제외 : admin으로 들어온 것은 Interceptor 적용 X
    }
    
}
