package com.java.project.api.service.board;

import com.java.project.api.dto.SearchDto;
import com.java.project.api.dto.board.BoardDto;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    // 입력
    void boardInsert(BoardDto dto);

    // 수정
    void boardUpdate(BoardDto dto);

    // 삭제
    void boardDelete(Long boardId);

    // 단일조회
    Object boardInquiry(Long boardId);

    // 전체조회
    Object boardList(SearchDto dto, Pageable pageable);
}
