package com.java.project.api.dto;

import com.java.project.api.entity.board.BoardConfig;
import com.java.project.api.entity.enums.AnswerYN;
import com.java.project.api.entity.enums.BoardType;

public record BoardConfigDto(
        BoardType boardType,
        AnswerYN isViewUse,
        int topExpoCount
) {
    public BoardConfig toEntity(){
        return BoardConfig.builder()
                .boardType(boardType)
                .isViewUse(isViewUse) // 기본값
                .topExpoCount(topExpoCount) //기본값
                .build();
    }
}
