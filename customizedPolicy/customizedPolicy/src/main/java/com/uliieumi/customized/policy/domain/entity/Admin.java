package com.uliieumi.customized.policy.domain.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity @Getter
public class Admin {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String loginId;

    private String password;

    public Admin() {
    }

    public Admin(String name, String loginId, String password) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
    }
}