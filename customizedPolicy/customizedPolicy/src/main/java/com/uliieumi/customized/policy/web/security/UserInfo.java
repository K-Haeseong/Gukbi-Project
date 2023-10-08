package com.uliieumi.customized.policy.web.security;


import com.uliieumi.customized.policy.domain.data.Role;
import com.uliieumi.customized.policy.domain.entity.Admin;
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

    public UserInfo(Admin admin) {
        this.id = admin.getId();
        this.name = admin.getName();
        this.role = Role.ADMIN;
    }

}
