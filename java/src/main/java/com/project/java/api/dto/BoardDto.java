package com.project.java.api.dto;

import com.project.java.api.entity.Board;
import com.project.java.api.entity.BoardConfig;
import com.project.java.api.entity.enums.AnswerYN;
import com.project.java.api.entity.enums.BoardType;

import java.time.LocalDateTime;

public record BoardDto(
        Long boardId,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate,
        BoardType boardType,
        String classify,
        String subject,
        String content,
        String views,
        String userName,
        String isDelete,
        AnswerYN isViewUse,
        int topExpoCount
) {

    public BoardConfig toEntity(){
        return BoardConfig.builder()
                .boardType(boardType)
                .isViewUse(AnswerYN.Y)
                .topExpoCount(5)
                .build();
    }


    public Board toEntity(String userName, Board board, BoardConfig boardConfig){
        return Board.builder()
                .classify(classify)
                .subject(subject)
                .content(content)
                .views(0)
                .userName(userName)
                .isDelete(AnswerYN.N)
                .boardConfig(boardConfig)
                .build();
    }


//    public BoardDto(Board board){
//        this(board.getId(),board.getCreatedDate(),board.getModifiedDate(), null,)
//    }


}
