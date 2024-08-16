package com.java.project.api.entity.board.Impl;

import com.java.project.api.dto.SearchDto;
import com.java.project.api.dto.board.BoardDto;
import com.java.project.api.entity.board.Board;
import com.java.project.api.entity.board.QBoard;
import com.java.project.api.entity.board.QBoardConfig;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepositoryImpl {
    private final JPAQueryFactory query;
    private QBoard board = new QBoard("board");
    private QBoardConfig config = new QBoardConfig("config");

    public Page<BoardDto> boardList(SearchDto dto, Pageable pageable){

        List<Board> fetch =
                query.select(board)
                .from(board)
                .where(deleteNo(),keywordDto(dto))
                .orderBy(board.id.desc())
                .fetch();

        List<BoardDto> fetchDto = fetch.stream()
                .map(b -> new BoardDto(b))
                .toList();
        long size = fetch.size();

        return new PageImpl<>(fetchDto, pageable,size);
    }

    // deleteNo
    private Predicate deleteNo(){
        return board.isDelete.eq(false);
    }

    // 키워드
    private Predicate keywordDto(SearchDto dto){
        BooleanExpression qK = null;

        if(dto.searchType() != null){
            if(dto.searchType().equals("content")){
                // 내용
                qK = board.content.contains(dto.searchValue());
            }else if (dto.searchType().equals("userName")){
                // 사용자
                qK = board.userName.contains(dto.searchValue());
            }else {
                // 기본값 (제목)
                qK = board.subject.contains(dto.searchValue());
            }
        }
        return qK == null ? null : qK;
    }


}
