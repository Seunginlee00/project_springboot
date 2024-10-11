package com.java.project.api.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum Role {
    ADMIN("관리자"),
    MEMBER("일반 회원");

    private String label;
}
