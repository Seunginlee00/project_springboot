package com.project.java.api.service;

import com.project.java.api.dto.BoardDto;
import com.project.java.api.entity.Board;
import com.project.java.api.entity.BoardConfig;
import com.project.java.api.entity.BoardConfigRepository;
import com.project.java.api.entity.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.awt.print.Pageable;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardConfigRepository configRepository;
    private final BoardRepository boardRepository;


    @Override
    public void insert(BoardDto dto) {

        BoardConfig config = configRepository.save(dto.toEntity());

        Board board = dto.toEntity("작성자",config);
        boardRepository.save(board);
    }

    @Override
    public void update(BoardDto dto) {
        Board board = boardRepository.findById(dto.boardId())
                .orElseThrow(IllegalAccessError.)
    }

    @Override
    public void delete(Long boardId) {

    }

    @Override
    public Object boardInquiry(Long boardId) {
        return null;
    }

    @Override
    public Object boardList(String keyword, String keywordType, Pageable pageable) {
        return null;
    }
}
