package com.java.api.utils;

import com.java.api.dto.BoardRequest;
import com.java.api.entity.Board;
import com.java.api.entity.interfaces.BoardEntity;

public class ConvertorUtil {

    public static BoardEntity toBoard(BoardRequest request){
        return Board.builder()
                .boardTitle(request.getBoardTitle())
                .boardContent(request.getBoardContent())
                .boardHit(0)
                .boardType(request.getBoardType())
                .build();
    }

}
