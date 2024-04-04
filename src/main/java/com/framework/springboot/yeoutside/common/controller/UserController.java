package com.framework.springboot.yeoutside.common.controller;

import com.framework.springboot.yeoutside.board.dto.BoardDto;
import com.framework.springboot.yeoutside.common.service.UserService;
import com.framework.springboot.yeoutside.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 회원 Controller class
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 회원가입 페이지
     */
    @GetMapping("/user/join")
    public String userJoin() {

        return "user/join";
    }

    /**
     * 회원 가입
     * @param user - 회원 가입 정보
     *               <p>userId - 회원 PW</p>
     *               <p>userPW - 회원 PW</p>
     *               <p>userNm - 회원명</p>
     *               <p>userRole - 회원 권한</p>
     */
    @PostMapping("/api/user/join")
    public String create(User user) throws Exception {

        userService.join(user);

        return "redirect:/user/login";
    }

    /**
     * 로그인 페이지
     */
    @GetMapping("/user/login")
    public String userLogin() {

        return "user/login";
    }

    /**
     * 로그인 체크
     */
    @PostMapping("/api/user/login/check")
    @ResponseBody
    public String userLoginCheck(User user) throws Exception {

        if(StringUtils.isEmpty(user.getUserId()))
        {
            throw new Exception("ID는 필수값 입니다.");
        }
        if(StringUtils.isEmpty(user.getUserPw()))
        {
            throw new Exception("비밀번호는 필수값 입니다.");
        }

        return userService.loginCheck(user);  // 로그인 체크
    }
}
