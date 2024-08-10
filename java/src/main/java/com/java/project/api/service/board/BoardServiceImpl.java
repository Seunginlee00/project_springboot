package com.java.project.api.service.board;

import com.java.project.api.common.exception.ApiException;
import com.java.project.api.common.exception.ExceptionData;
import com.java.project.api.dto.board.BoardConfigDto;
import com.java.project.api.entity.board.BoardConfigRepository;
import com.java.project.api.entity.board.BoardRepository;
import com.java.project.api.dto.board.BoardDto;
import com.java.project.api.entity.board.Board;
import com.java.project.api.entity.board.BoardConfig;
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
        if(dto.isViewUse() != null)
            isView = dto.isViewUse();

        // 설정 찾기

        // 기본 값..
        BoardConfigDto cDto = new BoardConfigDto(dto.boardType(),isView,dto.topExpoCount());

        BoardConfig config = configRepository.save(cDto.toEntity());


        // 나아중에 jwt 토큰에서 가져와서 사용 할 것
        Board board = dto.toEntity("작성자",config);
        boardRepository.save(board);
    }

    @Override
    public void update(BoardDto dto) {
        Board board =  null;
    }

    @Override
    public void delete(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    @Override
    public Object boardInquiry(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new ApiException(ExceptionData.NOT_FOUND_BOARD));
       // dto 전환.. 난 데이터 굳이 전부 안보여주고 싶음..
        return board;
    }

    @Override
    public Object boardList(String keyword, String keywordType, Pageable pageable) {
        return null;
    }
}
