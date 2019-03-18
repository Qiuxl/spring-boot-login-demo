package com.example.qzz.mvcdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebSecurityConfig extends WebMvcConfigurerAdapter {


    @Bean
    public SecurityInterceptor securityInterceptor(){
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration interceptorRegistration = registry.addInterceptor(securityInterceptor());
        // 排除拦截选项
        interceptorRegistration.excludePathPatterns("/login**");
        interceptorRegistration.excludePathPatterns("/health**");
        interceptorRegistration.excludePathPatterns("/error**");
        interceptorRegistration.addPathPatterns("/**");
    }
}
