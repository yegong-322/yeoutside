package com.framework.springboot.yeoutside.common.repository;

import com.framework.springboot.yeoutside.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 사용자 정보 repository
 */
public interface UserRepository extends JpaRepository<User, String> {

}
