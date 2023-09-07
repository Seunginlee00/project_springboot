package com.java.api.entity;

import java.time.LocalDateTime;

public class Member {

    // 우선은 lms 프로젝트를 많이 해봤으니 그런느낌 살려서 해볼까~ 생각 중..

    private Long memberId;
    private String userId;

    private String password;

    private String email;

    private String memberState; // 회원  상태 list) 0:이메일 미인증, 1: 정상, 2: 휴먼, 3:정지, 4:탈퇴 등

    private String userType; //  학생, 선생님

    private LocalDateTime lastLoginDate; // 마지막 로그인


    private String pwdCertifiNumber;    // 비밀번호 찾기 인증번호(인증 후 삭제)

    private LocalDateTime pwdCertifiNumberExpireDate;   // 비밀번호 찾기 인증번호 만료 시간 -> 1시간 생각 중..

    private String emailCertifiCode;    // 이메일 인증 코드

    private LocalDateTime emailCertifiCodeExpireDate;   // 이메일 승인 메일 만료 시간 하루


}
