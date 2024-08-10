package com.java.project.api.dto.board;

import com.java.project.api.entity.board.Board;
import com.java.project.api.entity.board.BoardConfig;
import com.java.project.api.entity.enums.BoardType;

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
        Boolean isViewUse,
        int topExpoCount
) {

    public Board toEntity(String userName, BoardConfig boardConfig){
        return Board.builder()
                .classify(classify)
                .subject(subject)
                .content(content)
                .views(0)
                .userName(userName)
                .isDelete(false)
                .boardConfig(boardConfig)
                .build();
    }

//    public BoardDto toDto(Board board){
//        this(board.getId(), board.)
//    }


}
