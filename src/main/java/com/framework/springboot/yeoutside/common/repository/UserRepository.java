package com.framework.springboot.yeoutside.common.repository;

import com.framework.springboot.yeoutside.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 회원 정보 repository
 */
public interface UserRepository extends JpaRepository<User, String> {

}
