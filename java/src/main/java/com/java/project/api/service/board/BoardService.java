package com.java.project.api.service.board;

import com.java.project.api.dto.BoardDto;

import java.awt.print.Pageable;

public interface BoardService {

    // 입력
    void insert(BoardDto dto);

    // 수정
    void update(BoardDto dto);

    // 삭제
    void delete(Long boardId);

    // 단일조회
    Object boardInquiry(Long boardId);

    // 전체조회
    Object boardList(String keyword, String keywordType, Pageable pageable);
}
