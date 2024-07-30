package com.java.project.api.service.board;

import com.java.project.api.dto.BoardConfigDto;
import com.java.project.api.entity.board.BoardConfigRepository;
import com.java.project.api.entity.board.BoardRepository;
import com.java.project.api.dto.BoardDto;
import com.java.project.api.entity.board.Board;
import com.java.project.api.entity.board.BoardConfig;
import com.java.project.api.entity.enums.AnswerYN;
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
        AnswerYN isView = AnswerYN.Y;
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
