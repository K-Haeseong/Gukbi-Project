package com.uliieumi.customized.policy.web.security;


import com.uliieumi.customized.policy.domain.data.Role;
import com.uliieumi.customized.policy.domain.entity.Admin;
import com.uliieumi.customized.policy.domain.entity.Enterprise;
import com.uliieumi.customized.policy.domain.entity.Member;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;


import java.util.List;

@Getter
public class Account extends User {



    private final UserInfo userInfo;


    public Account(Admin admin) {
        super(admin.getLoginId(), admin.getPassword(), List.of(new SimpleGrantedAuthority(Role.ADMIN.getRole())));
        this.userInfo = new UserInfo(admin);
    }

    public Account(Member member) {
        super(member.getLoginId(), member.getPassword(), List.of(new SimpleGrantedAuthority(Role.MEMBER.getRole())));
        this.userInfo = new UserInfo(member);
    }

    public Account(Enterprise enterprise) {
        super(enterprise.getLoginId(), enterprise.getPassword(), List.of(new SimpleGrantedAuthority(Role.ENTERPRISE.getRole())));
        this.userInfo = new UserInfo(enterprise);
    }


}
