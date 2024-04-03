package com.framework.springboot.yeoutside.common.service;

import com.framework.springboot.yeoutside.common.repository.UserRepository;
import com.framework.springboot.yeoutside.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            userRepository.save(user);  // 회원 정보 저장
        }
        catch(Exception e)
        {
            e.printStackTrace();

            throw new Exception(e.getMessage());
        }
    }
}
