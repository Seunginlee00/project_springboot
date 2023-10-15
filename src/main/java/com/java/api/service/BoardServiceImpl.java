package com.java.api.service;

import com.java.api.entity.Board;
import com.java.api.entity.interfaces.BoardEntity;
import com.java.api.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Object BoardInsert(BoardEntity board) {

        Board dto = (Board) board; // 형변환
        boardRepository.save(dto);
        return "add";

    }


}
