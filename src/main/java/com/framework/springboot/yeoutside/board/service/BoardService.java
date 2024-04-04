package com.framework.springboot.yeoutside.board.service;

import com.framework.springboot.yeoutside.board.dto.BoardDto;
import com.framework.springboot.yeoutside.board.model.Board;
import com.framework.springboot.yeoutside.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * 게시판 Service class
 */
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    /**
     * 전체 게시글 조회
     */
    public List<BoardDto> boardList() {

        List<Board> boards = boardRepository.findAll();  // 전체 게시글 조회
        List<BoardDto> boardDtos = new ArrayList<>();

        for(Board board : boards) {
            BoardDto boardDto = BoardDto.builder()
                                        .id(board.getId())
                                        .title(board.getTitle())
                                        .content(board.getContent())
                                        .writer(board.getWriter())
                                        .build();

            boardDtos.add(boardDto);
        }

        return boardDtos;
    }

    /**
     * 게시글 등록
     * @param boardDto - 유저 정보
     *                   <p>id : 게시판 고유번호</p>
     *                   <p>title : 제목</p>
     *                   <p>content : 내용</p>
     *                   <p>writer : 작성자</p>
     */
    @Transactional(rollbackOn = Exception.class)
    public void boardWrite(BoardDto boardDto) throws Exception {

        try
        {
            boardRepository.save(boardDto.toEntity()).getId();  // 게시글 저장
        }
        catch(Exception e)
        {
            e.printStackTrace();

            throw new Exception(e.getMessage());
        }
    }

    /**
     * 게시글 상세 조회
     * @param id 게시글 고유번호
     */
    public BoardDto boardDetail(int id) {

        Optional<Board> boardInfo = boardRepository.findById(String.valueOf(id));  // 게시글 조회

        if(boardInfo.isPresent())
        {
             Board board = boardInfo.get();

            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writer(board.getWriter())
                    .build();

            return boardDto;
        }
        else
        {
            throw new NoSuchElementException();
        }
    }
}
