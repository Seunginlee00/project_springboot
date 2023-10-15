package com.java.api.controller;

import com.java.api.dto.ApiResultNotification;
import com.java.api.dto.BoardRequest;
import com.java.api.entity.Board;
import com.java.api.entity.interfaces.BoardEntity;
import com.java.api.service.BoardService;
import com.java.api.utils.ConvertorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController("/api")
public class BoardController {

    @Qualifier("boardService")
    @Autowired
    public BoardService boardService;

    //글 등록
    @PostMapping("/board")
    protected ResponseEntity<ApiResultNotification> insertBoard(
            @RequestBody BoardRequest boardRequest
    ) {

        ApiResultNotification result = null;

        try {
            BoardEntity board = ConvertorUtil.toBoard(boardRequest);
            Object data = boardService.BoardInsert(board);

            log.info("inserBoard:  {} board ", data);

            result = ApiResultNotification.builder()
                    .notification("Successful")
                    .alert_message("게시글 입력 성공")
                    .status_code(200)
                    .status("successful")
                    .status_message("board insert successful")
                    .data(data)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(result);



        } catch (Exception e) {
            log.error("registLicense: exception {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }

    }
}
