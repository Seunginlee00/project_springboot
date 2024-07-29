package com.project.java.api.entity.board;

import com.project.java.api.common.entity.BaseEntity;
import com.project.java.api.dto.BoardDto;
import com.project.java.api.entity.enums.AnswerYN;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.SQLDelete;
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE tb_board SET is_delete = true, modified_date = now() WHERE board_id = ?")
@Table(name = "TB_BOARD")
public class Board extends BaseEntity {

    @Column(name = "board_id")
    private Long id;

    @Column(length = 100)

    private String classify; //분류

    private String subject; // 제목

    @Column(columnDefinition = "LONGTEXT")
    private String content; // 내용

    @Column(length = 100)
    private String userName; // 회원명

    private int views; // 조회수

    private AnswerYN isDelete;  // 삭제 여부

    @ManyToOne
    @JoinColumn(name = "board_config_id")
    private BoardConfig boardConfig;


    public void update(BoardDto dto){
        if(dto.classify() != null)
            this.classify = dto.classify();
        if(dto.subject() != null)
            this.subject = dto.subject();
        if(dto.content() != null)
            this.content = dto.content();
    }


    public void viewUpdate(){
        this.views++;
    }


}
