package com.java.project.api.dto.board;

import com.java.project.api.entity.board.Board;
import com.java.project.api.entity.board.Comment;
import com.java.project.api.entity.enums.BoardType;

import java.time.LocalDateTime;

public record BoardInquiryDto(
       Board board,
       Comment comment
) {
}
