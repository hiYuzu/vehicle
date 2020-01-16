package com.plr.vehicle.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:17
 */
@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {
    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
        // 排除配置
        addInterceptor.excludePathPatterns("/", "/login**", "/static/**", "/js/**", "/iconfont/**", "/css/**", "/images/**", "/favicon/**", "/login404**");

    }
}
