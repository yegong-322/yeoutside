package com.framework.springboot.yeoutside.board.controller;

import com.framework.springboot.yeoutside.board.dto.BoardDto;
import com.framework.springboot.yeoutside.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 게시판 Controller class
 */
@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    /**
     * 게시판 페이지
     */
    @GetMapping("/board/list")
    public String boardList(Model model) {

        List<BoardDto> boardDtoList = boardService.boardList();  // 게시판 리스트 조회
        model.addAttribute("boardList", boardDtoList);

        return "board/list";
    }

    /**
     * 게시글 작성 페이지
     */
    @GetMapping("/board/write")
    public String boardWrite() {

        return "board/write";
    }

    /**
     * 게시글 상세 페이지
     */
    @GetMapping("/board/detail/{id}")
    public String boardDetail(@PathVariable("id") int id, Model model) {

        BoardDto boardDto = boardService.boardDetail(id);  // 게시글 상세 조회
        model.addAttribute("boardDto", boardDto);

        return "board/detail";
    }

    /**
     * 게시글 저장
     * @param boardDto - 게시글 정보
     *                   <p>title : 제목</p>
     *                   <p>title : 내용</p>
     */
    @PostMapping("/api/board/write")
    public String boardWrite(BoardDto boardDto) throws Exception {

        boardService.boardWrite(boardDto);

        return "redirect:/board/list";
    }
}
