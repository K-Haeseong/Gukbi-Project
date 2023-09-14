package com.uliieumi.customized.policy.domain.entity;

import com.uliieumi.customized.policy.domain.data.*;
import lombok.Getter;

import java.util.Date;

@Getter
public class Member {

//    private Long id;

    private String name;

    private int age;

    private String loginId;

    private String password;

    private String email;

    private String phone;

    private Boolean sex;

    private Date birth;

    private PolicyRegion interestCategory; //지역

    private PolicyCategory category; //정책분야

    private JobState jobState; //취업상태

    private EducationLevel educationLevel; //학력

    private SpecificClass specificClass; //특정계층


    public Member(String name, String loginId, String password, String email, String phone, Boolean sex, Date birth, PolicyRegion interestCategory, PolicyCategory category, JobState jobState, EducationLevel educationLevel, SpecificClass specificClass) {
//        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.birth = birth;
        this.birth = birth;
        this.interestCategory = interestCategory;
        this.category = category;
        this.jobState = jobState;
        this.educationLevel = educationLevel;
        this.specificClass = specificClass;
    }
}
