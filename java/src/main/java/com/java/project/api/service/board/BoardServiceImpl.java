package com.java.project.api.service.board;

import com.java.project.api.common.exception.ApiException;
import com.java.project.api.common.exception.ExceptionData;
import com.java.project.api.dto.SearchDto;
import com.java.project.api.dto.board.BoardConfigDto;
import com.java.project.api.entity.board.BoardConfigRepository;
import com.java.project.api.entity.board.BoardRepository;
import com.java.project.api.dto.board.BoardDto;
import com.java.project.api.entity.board.Board;
import com.java.project.api.entity.board.BoardConfig;
import com.java.project.api.entity.board.Impl.BoardRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardConfigRepository configRepository;
    private final BoardRepository boardRepository;
    private final BoardRepositoryImpl boardImpl;

    @Transactional
    @Override
    public void boardInsert(BoardDto dto) {
        Board board = null;
        BoardConfig config = configRepository.findByBoardType(dto.boardType());

        if(config == null){
            // 해당 분류가 없다면
            BoardConfigDto cDto = new BoardConfigDto(dto.boardType(),true, dto.topExpoCount());

            config = configRepository.save(cDto.toEntity());
            board = dto.toEntity("jwt추출",config);

        }else {
            // 있다면
            board = dto.toEntity("jwt추출",config);
        }

        boardRepository.save(board);
    }

    @Transactional
    @Override
    public void boardUpdate(BoardDto dto) {
        Board board =  boardRepository.findById(dto.boardId()).orElseThrow(() -> new ApiException(ExceptionData
                .NOT_FOUND_BOARD));
    // 수정
        board.update(dto);

    }

    @Override
    public void boardDelete(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    @Override
    public Object boardInquiry(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(() -> new ApiException(ExceptionData
                .NOT_FOUND_BOARD));
    }

    @Override
    public Object boardList(SearchDto dto, Pageable pageable) {
        return boardImpl.boardList(dto,pageable);
    }
}
