package com.framework.springboot.yeoutside.common.repository;

import com.framework.springboot.yeoutside.common.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 회원 정보 repository
 */
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * 회원 ID로 전체 정보 조회
     * @param userId 회원 ID
     */
    Optional<User> findByUserId(String userId);
}
