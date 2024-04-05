package com.framework.springboot.yeoutside.common.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * 회원 모델 class
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;  // 회원 ID

    @Column(name = "user_pw")
    private String userPw;  // 회원 비밀번호

    @Column(name = "user_nm")
    private String userNm;  // 회원명

    @Column(name = "user_role")
    private String userRole;  // 회원 권한

    @Builder
    public User(String userId, String userPw, String userNm, String userRole) {
        this.userId = userId;
        this.userPw = userPw;
        this.userNm = userNm;
        this.userRole = userRole;
    }
}
