package com.framework.springboot.yeoutside.security.service;

import com.framework.springboot.yeoutside.common.model.User;
import com.framework.springboot.yeoutside.common.model.UserRole;
import com.framework.springboot.yeoutside.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 로그인 Service class
 */
@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Value("${system.admin.userId}")
    private String[] sysAdminArray;  // 시스템 관리자 리스트

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = this.userRepository.findByUserId(username);  // 회원 정보 조회

        if(!user.isPresent())
        {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        User userInfo = user.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        List<String> sysAdminList = new ArrayList<>(Arrays.asList(sysAdminArray));

        // 권한 부여
        if(sysAdminList.contains(username))
        {
            authorities.add(new SimpleGrantedAuthority(UserRole.M.getRoleNm()));
        }
        else
        {
            authorities.add(new SimpleGrantedAuthority(UserRole.U.getRoleNm()));
        }

        return new org.springframework.security.core.userdetails.User(userInfo.getUserId(), userInfo.getUserPw(), authorities);
    }
}
