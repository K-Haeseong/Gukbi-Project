package com.uliieumi.customized.policy.domain;

import lombok.Getter;

import java.time.LocalDate;


@Getter
public class Policy {

//    private Long id;
//
//    private String name;
//
//    private Integer age;
//
//    private String category;
//
//    private String educationLevel;
//
//    private String jobState;
//
//    private String region;
//
//    private String specificClass;

    private Long id;//아이디

    private String Title; //제목

    private String filePath;//파일경로

    private String thumbnail;//썸네일

    //private String posterImage;//관련 포스터이미지

    private String shortDescription;//짧은소개

    private LocalDate publishedDateTime;// 오픈시간

    private LocalDate closedDateTime;  //  마감시간

    private Integer recruitsNumber;//모집인원

    private Integer minAge;//최소연령

    private Integer maxAge;//최대연령

    private String region;//지역

    private String category;//정책분야

    private String jobState;//취업상태

    private String educationLevel;//학력

    private String specificClass;//특정계층

    private String proposal;//신청방법

    private String content;//에디터컨텐츠


}
