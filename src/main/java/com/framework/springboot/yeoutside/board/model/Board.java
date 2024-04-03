package com.framework.springboot.yeoutside.board.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 게시판 모델 class
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 무분별한 객체 생성 방지
@Entity
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  // 게시판 고유번호

    @Column(name = "title")
    private String title;  // 제목

    @Column(name = "content")
    private String content;  // 내용

    @Column(name = "writer")
    private String writer;  // 작성자

    @Builder
    public Board(int id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
