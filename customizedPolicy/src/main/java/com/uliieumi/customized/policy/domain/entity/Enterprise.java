package com.uliieumi.customized.policy.domain.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity @Getter
public class Enterprise {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String loginId;

    private String password;

    private String ceo;

    private String address;

    private int bNum;

    private String email;

    private String tel;



    public Enterprise() {
    }

    public Enterprise(String name, String loginId, String password, String ceo, String address, int bNum, String email, String tel) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.ceo = ceo;
        this.address = address;
        this.bNum = bNum;
        this.email = email;
        this.tel = tel;
    }
}
