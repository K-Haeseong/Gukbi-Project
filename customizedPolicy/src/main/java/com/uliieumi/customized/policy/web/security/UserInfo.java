package com.uliieumi.customized.policy.web.security;


import com.uliieumi.customized.policy.domain.data.Role;
import com.uliieumi.customized.policy.domain.entity.Enterprise;
import com.uliieumi.customized.policy.domain.entity.Member;

import lombok.Getter;

@Getter
public class UserInfo {
    private Long id;
    private String name;
    private Role role;

    public UserInfo(Enterprise enterprise) {
        this.id = enterprise.getId();
        this.name = enterprise.getName();
        this.role = Role.ENTERPRISE;
    }

    public UserInfo(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.role = Role.MEMBER;
    }

    public UserInfo(Long id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
}
