package com.java.project.api.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnswerType {
    W("답변 대기"),
    C("답변 완료");
    private String label;
}
