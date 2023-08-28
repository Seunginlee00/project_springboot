package com.java.config;
import com.java.web.DefaultServletFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;


/**
 * 필터의 상세 설정을 위한 설정 클래스
 * <br/> 사용 시 주석 제거 후 설정 빈으로 등록
 *
 */
@Configuration
public class FilterConfig {

    //	@Bean
    public FilterRegistrationBean<DefaultServletFilter> firstFilter(){
        FilterRegistrationBean<DefaultServletFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new DefaultServletFilter());
        registrationBean.addUrlPatterns("/");
        registrationBean.setOrder(1);
        registrationBean.setName("servlet-filter");
        return registrationBean;
    }

}
