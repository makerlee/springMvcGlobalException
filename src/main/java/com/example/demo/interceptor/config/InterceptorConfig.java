package com.example.demo.interceptor.config;

import com.example.demo.interceptor.CommonInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器配置
 */
@EnableWebMvc
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Bean
    CommonInterceptor commonInterceptor() {
        return new CommonInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(commonInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
