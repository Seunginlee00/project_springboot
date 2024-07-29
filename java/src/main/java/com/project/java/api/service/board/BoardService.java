package com.project.java.api.service.board;

import com.project.java.api.dto.BoardDto;

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
