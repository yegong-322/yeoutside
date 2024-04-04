package com.framework.springboot.yeoutside.common.model;

import lombok.Getter;

/**
 * 권한 enum
 */
@Getter
public enum UserRole {
    U("NOR_ADMIN"),  // 기본 관리자
    M("SYS_ADMIN");  // 시스템 관리자

    private final String roleNm;

    UserRole(String roleNm) {
        this.roleNm = roleNm;
    }
}
