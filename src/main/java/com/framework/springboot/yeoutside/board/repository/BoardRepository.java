package com.framework.springboot.yeoutside.board.repository;

import com.framework.springboot.yeoutside.board.model.Board;
import com.framework.springboot.yeoutside.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 게시판 repository
 */
public interface BoardRepository extends JpaRepository<Board, String> {

}
