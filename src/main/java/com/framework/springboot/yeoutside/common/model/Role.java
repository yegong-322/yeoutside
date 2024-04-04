package com.framework.springboot.yeoutside.common.model;

/**
 * 회원 권한 enum
 */
public enum Role {
    U("NOR_ADMIN"), M("SYS_ADMIN");

    private final String roleNm;

    Role(String roleNm) {
        this.roleNm = roleNm;
    }
}
