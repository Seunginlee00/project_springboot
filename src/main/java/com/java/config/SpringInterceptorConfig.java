package com.java.config;

import com.java.web.DefaultInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 스프링 인터셉터의 설정과 등록
 * <br/> URL pattern 및 설정
 *
 */
@Configuration
public class SpringInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DefaultInterceptor())
                .excludePathPatterns("/resources/**", "/css/**", "/images/**", "/js/**");
    }
}
