package com.java.config;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        try{
            http
                    .formLogin().permitAll()
                    .loginPage("/logins")
                    .defaultSuccessUrl("/")
                    .loginProcessingUrl("/login"); // 로그인 액션처리를 /login url이 담당


            // 권한 설정
            http.authorizeRequests()
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .antMatchers("/","/font/**","/img/**","/css/**","/index.html","/error","/api/login").permitAll()

//                    .antMatchers("/admin/**").hasRole("ADMIN") -> 관리자 차후 추가
                    .anyRequest().authenticated();
            // 로그아웃 관련
            http.logout().permitAll()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .deleteCookies("JSESSIONID", "namwonweb") // 남원에서 사용중인 namwonweb 쿠키 해당 쿠키를 지우겠다는 url
                    .invalidateHttpSession(true)
                    .clearAuthentication(true);


            return http.build();

        }catch (Exception e) {
            // TODO:
            log.error("exception : failed security configuration");
            e.printStackTrace();
        }
        return null;
    }
}
