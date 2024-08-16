package com.java.project.api.common.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Getter
@ToString
@NoArgsConstructor
public class ApiResult {
    private String status;
    private String message;
    private Object data;

    @Builder
    public ApiResult(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
