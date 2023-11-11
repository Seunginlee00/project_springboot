package com.java.api.entity;

import com.java.api.entity.interfaces.BoardEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@Table(name = "BOARD")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseDate implements BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId; // 자동 번호 = 고유키

    @Column(length = 1000, name="boardTitle")
    private String boardTitle; // 글 제목

    @Column(length = 2000, name="boardContent")
    private String boardContent; // 글 내용

    private Integer boardHit; // 글 조회수

    private String boardFileLink; // 글 업로드 파일 링크

    private String boardType; // 글 타입 ex ) 홍보, 이벤트, 안내 .. 등

    // 제목, 내용, 작성자,(추후) 조회수, 파일기능 정도?



}
