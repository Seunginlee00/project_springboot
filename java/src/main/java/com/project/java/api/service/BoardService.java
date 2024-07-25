package com.project.java.api.service;

import com.project.java.api.dto.BoardDto;

import java.awt.print.Pageable;

public interface BoardService {

    void insert(BoardDto dto);
    void update();
    void delete(Long boardId);
    Object boardInquiry(Long boardId);
    Object boardList(String keyword, String keywordType, Pageable pageable);
}
