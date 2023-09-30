package com.uliieumi.customized.policy.domain.entity;

import com.uliieumi.customized.policy.domain.data.*;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String birth;

    private Boolean sex;

    private String loginId;

    private String password;

    private String email;

    private String phone;

    private String address;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private PolicyRegion region;

    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;

    @Enumerated(EnumType.STRING)
    private SpecificClass specificClass;

    @Enumerated(EnumType.STRING)
    private JobState jobState;

    @Enumerated(EnumType.STRING)
    private PolicyCategory interestCategory;


    public Member() {
    }


    public Member(String name, String birth, Boolean sex, String loginId, String password,
                  String email, String phone, String address, Integer age, PolicyRegion region,
                  EducationLevel educationLevel, SpecificClass specificClass, JobState jobState, PolicyCategory interestCategory) {

        this.name = name;
        this.birth = birth;
        this.sex = sex;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.age = age;
        this.region = region;
        this.educationLevel = educationLevel;
        this.specificClass = specificClass;
        this.jobState = jobState;
        this.interestCategory = interestCategory;
    }
}
