package com.framework.springboot.yeoutside.board.dto;

import com.framework.springboot.yeoutside.board.model.Board;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 게시판 DTO class
 * - DB와 ENTITY가 직접적으로 접근하는건 위험하여, 캡슐화를 위해 DTO 생성함
 */
@Data
@ToString
@NoArgsConstructor
public class BoardDto {
    /** 게시판 고유번호 */
    private int id;
    /** 제목 */
    private String title;
    /** 내용 */
    private String content;
    /** 작성자 */
    private String writer;

    public Board toEntity() {
        Board build = Board.builder()
                .id(id)
                .title(title)
                .content(content)
                .writer(writer)
                .build();
        return build;
    }

    @Builder
    public BoardDto(int id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
