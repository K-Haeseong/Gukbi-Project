package com.uliieumi.customized.policy.domain.entity;

import com.uliieumi.customized.policy.domain.data.PolicyCategory;
import lombok.Getter;

import java.time.LocalDate;


@Getter
public class Policy {

    private Long id;//아이디

    private String name; //제목

    private String filePath;//파일경로

    private String thumbnail;//썸네일

    //private String posterImage;//관련 포스터이미지

    private String shortDescription;//짧은소개

    private LocalDate publishedDateTime;// 오픈시간

    private LocalDate closedDateTime;  //마감시간

    private Integer recruitsNumber;//모집인원

    private Integer minAge;//최소연령

    private Integer maxAge;//최대연령

    private String region;//지역

    private PolicyCategory category;//정책분야

    private String jobState;//취업상태

    private String educationLevel;//학력

    private String specificClass;//특정계층

    private String proposal;//신청방법

    private Integer hit;//조회수

    private String deadline;//신청기간

    private String createdDate; //게시물 등록 날짜

    private String content;//에디터컨텐츠

}
