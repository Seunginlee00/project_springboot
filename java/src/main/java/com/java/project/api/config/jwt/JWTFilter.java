package com.java.project.api.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.project.api.config.jwt.service.CustomUserDetails;
import com.java.project.api.entity.user.Role;
import com.java.project.api.entity.user.User;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JwtGenerator generator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Header에서 Authorization 추출
        String authorization = request.getHeader("Authorization");

        // authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            filterChain.doFilter(request, response);
            // 조건이 해당되면 메소드 종료(필수)
            return;
        }
        // Bearer 부분 제거하여 토큰만 획득
        String token = authorization.split(" ")[1];

        // 토큰 소멸 시간 검증
        try {
            generator.isExpired(token);

        } catch (ExpiredJwtException e) {

//            JWTException jwtException = new JWTException(true, "토큰이 만료 되었습니다.");
//            String result = objectMapper.writeValueAsString(jwtException);
//            response.getWriter().write(result);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String type = generator.getType(token);
        if (!type.equals("access")) {
//            JWTException jwtException = new JWTException(true, "Invalid Access Token");
//            String result = objectMapper.writeValueAsString(jwtException);
//            response.getWriter().write(result);

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String username = generator.getMemberId(token);
        String role = generator.getRole(token);

        // userEntity를 생성하여 값 set
        User setUser = User.builder()
                .id(Long.valueOf(username))
                .password("temppassword")
                .role(Role.valueOf(role))
                .build();

        // UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(setUser);

        // 스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null,
                customUserDetails.getAuthorities());

        // 세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

//    private Authentication getAuthentication(HttpServletRequest request) throws MalformedJwt {
//
//        String authorizationHeader = request.getHeader("Authorization");
//
//        if (authorizationHeader == null) {
//            return null;
//        }
//
//        String token = authorizationHeader.substring("Bearer ".length());
//
//        Claims claims;
//        try {
//            claims = jwtUtil.getClaims(token);
//        } catch (JwtException e) {
//
//        }
//
//        Set<GrantedAuthority> roles = new HashSet<>();
//        String role = (String) claims.get("role");
//        roles.add(new SimpleGrantedAuthority("ROLE_" + role));
//
//        return new UsernamePasswordAuthenticationToken(new Admin(claims), null, roles);
//    }
//
}
