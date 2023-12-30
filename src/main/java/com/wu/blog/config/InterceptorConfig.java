package com.wu.blog.config;

import com.wu.blog.interceptors.JWTinterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //不需要拦截的接口
        registry.addInterceptor(new JWTinterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/v1/api/blog/search")
                .excludePathPatterns("/v1/api/blog/search/**")
                .excludePathPatterns("/v1/api/user/login")
                .excludePathPatterns("/v1/api/user/check")
                .excludePathPatterns("/v1/api/user/add")
                .excludePathPatterns("/v1/api/diary/check")
                .excludePathPatterns("/v1/api/diary/checktoken")
                .excludePathPatterns("/v1/api/user/check/getDiaryToken");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
