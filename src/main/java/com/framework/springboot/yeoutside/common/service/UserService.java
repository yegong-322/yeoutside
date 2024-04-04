package com.framework.springboot.yeoutside.common.service;

import com.framework.springboot.yeoutside.common.repository.UserRepository;
import com.framework.springboot.yeoutside.model.User;
import com.framework.springboot.yeoutside.util.EncryptUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * 회원 Service class
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 회원 가입
     * @param user - 유저 정보
     *               <p>userId : 회원 ID</p>
     *               <p>userPw : 회원 PW</p>
     *               <p>userNm : 회원명</p>
     *               <p>userRole : 회원 권한</p>
     */
    @Transactional(rollbackOn = Exception.class)
    public void join(User user) throws Exception {

        try
        {
            // 비밀번호 암호화
            EncryptUtil encryptUtil = new EncryptUtil();
            user.setUserPw(encryptUtil.sha256(user.getUserPw()));

            userRepository.save(user);  // 회원 정보 저장
        }
        catch(Exception e)
        {
            e.printStackTrace();

            throw new Exception(e.getMessage());
        }
    }

    /**
     * 로그인 체크
     * @param user 회원 ID
     */
    public String loginCheck(User user) throws Exception {

       Optional<User> result = userRepository.findById(user.getUserId());

       if(result.isPresent())
       {
           EncryptUtil encryptUtil = new EncryptUtil();
           if(encryptUtil.sha256(user.getUserPw()).equals(result.get().getUserPw()))
           {
               return "equals";
           }
       }

        return "different";
    }
}
