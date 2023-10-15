package com.java.api.dto;

import lombok.*;

import javax.persistence.Column;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequest {

    private String boardTitle; // 글 제목
    private String boardContent; // 글 내용
    private String boardType; // 글 타입 ex ) 홍보, 이벤트, 안내 .. 등

}
