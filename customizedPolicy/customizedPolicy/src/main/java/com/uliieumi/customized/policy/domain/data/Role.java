package com.uliieumi.customized.policy.domain.data;

import lombok.Getter;

@Getter
public enum Role {

    MEMBER("ROLE_MEMBER"),
    ENTERPRISE("ROLE_ENTERPRISE"),
    ADMIN("ROLE_ADMIN");

    private String role;

    Role(String role) {
        this.role = role;
    }
}
