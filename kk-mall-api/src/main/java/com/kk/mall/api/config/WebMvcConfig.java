package com.kk.mall.api.config;

import com.kk.mall.common.interceptor.CorsInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/3/25 3:37 下午
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {



    @Bean
    public CorsInterceptor corsInterceptor() {
        return new CorsInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor());
    }
}

