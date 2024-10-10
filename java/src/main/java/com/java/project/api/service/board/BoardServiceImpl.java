package com.java.project.api.service.board;

import com.java.project.api.common.exception.ApiException;
import com.java.project.api.common.exception.ExceptionData;
import com.java.project.api.dto.SearchDto;
import com.java.project.api.dto.board.BoardConfigDto;
import com.java.project.api.dto.board.BoardInquiryDto;
import com.java.project.api.dto.board.CommentDto;
import com.java.project.api.entity.board.*;
import com.java.project.api.dto.board.BoardDto;
import com.java.project.api.entity.board.Impl.BoardRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardConfigRepository configRepository;
    private final BoardRepository boardRepository;
    private final BoardRepositoryImpl boardImpl;
    private final CommentRepository commentRepository;

    @Override
    public void boardInsert(BoardDto dto) {
        Board board = null;
        BoardConfig config = configRepository.findByBoardType(dto.boardType());

        if(config == null){
            // 해당 분류가 없다면
            BoardConfigDto cDto = new BoardConfigDto(dto.boardType(),true,100);

            config = configRepository.save(cDto.toEntity());
            board = dto.toEntity("jwt추출",config);

        }else {
            // 있다면
            board = dto.toEntity("jwt추출",config);
        }

        boardRepository.save(board);
    }

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
    @Transactional(readOnly = true)
    @Override
    public Object boardInquiry(Long boardId) {
        Board board = boardRepository.findByIdAndIsDelete(boardId,false).orElseThrow(() -> new ApiException(ExceptionData
                .NOT_FOUND_BOARD));
        board.viewUpdate();

        Comment comment = commentRepository.findByBoard(board).orElseThrow(()
                -> new ApiException(ExceptionData.NOT_FOUND_COMMENT));
        BoardInquiryDto dto = new BoardInquiryDto(board,comment);

        return dto;
    }

    @Transactional(readOnly = true)
    @Override
    public Object boardList(SearchDto dto, Pageable pageable) {
        return boardImpl.boardList(dto,pageable);
    }


    @Override
    public void commentInsert(CommentDto dto) {
        Board board = boardRepository.findById(dto.boardId()).orElseThrow(() -> new ApiException(ExceptionData.NOT_FOUND_BOARD));
        Comment comment = dto.toEntity("추후토큰값",board);

        commentRepository.save(comment);
        board.setAnswerType();
    }

    @Override
    public void commentUpdate(CommentDto dto) {
        Board board = boardRepository.findById(dto.boardId()).orElseThrow(()
                -> new ApiException(ExceptionData.NOT_FOUND_BOARD));

        Comment comment = commentRepository.findByBoard(board).orElseThrow(()
        -> new ApiException(ExceptionData.NOT_FOUND_COMMENT));
        comment.update(dto);
    }

    @Override
    public void commentDelete(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
