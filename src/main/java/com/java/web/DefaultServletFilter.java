package com.java.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * 필터의 상세 설정을 원하는 경우
 * <br> FilterRegistrationBean 설정 클래스 활용
 * <br> +	@Configuration 클래스를 별도로 만들어야 하지만 @ServletComponentScan 이나 필터 클래스에 적용한 어노테이션이 필요 없음.
 *
 * @see FilterConfig
 *
 */
@Slf4j
//@WebFilter(urlPatterns="/")	// 기본(자동)설정은 모든 URL 에 매핑
@Component
public class DefaultServletFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        log.info("Request: {}", request.getRemoteAddr());
        log.info("--> doFilter");
        chain.doFilter(request, response);
        log.info("Response: {}", response.getCharacterEncoding());
        log.info("<-- doFilter");
    }

}
