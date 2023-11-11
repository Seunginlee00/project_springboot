package com.java.api.service;

import com.java.api.entity.interfaces.BoardEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {



    @Override
    public int BoardInsert(BoardEntity entity) throws Exception {
        return 0;
    }
}
