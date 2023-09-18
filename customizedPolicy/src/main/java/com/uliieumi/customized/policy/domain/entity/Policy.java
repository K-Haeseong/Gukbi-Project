package com.uliieumi.customized.policy.domain.entity;

import com.uliieumi.customized.policy.domain.data.*;
import lombok.Getter;

import java.time.LocalDate;


@Getter
public class Policy {

    private Long id;//아이디

    private String name; //제목

    private String thumbnail; // 포스터 이미지

    private String document; // 첨부파일

    private String shortDescription; //짧은소개

    private LocalDate publishedDateTime; // 오픈시간

    private LocalDate closedDateTime;  //마감시간

    private Integer recruitsNumber; //모집인원

    private Integer minAge; //최소연령

    private Integer maxAge; //최대연령

    private String region; //지역

    private PolicyCategory category; //정책분야

    private JobState jobState; //취업상태

    private EducationLevel educationLevel; //학력

    private SpecificClass specificClass; //특정계층

    private String proposal; //신청방법

    private Integer hit; //조회수

    private String deadline; //신청기간

    private String createdDate; //게시물 등록 날짜

    private String content; //에디터컨텐츠

    public Policy() {
    }

    public Policy(Long id, String name, String thumbnail, String document, String shortDescription, LocalDate publishedDateTime, LocalDate closedDateTime, Integer recruitsNumber, Integer minAge, Integer maxAge, String region,
                  PolicyCategory category, JobState jobState, EducationLevel educationLevel, SpecificClass specificClass, String proposal, Integer hit, String deadline, String createdDate, String content) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.document = document;
        this.shortDescription = shortDescription;
        this.publishedDateTime = publishedDateTime;
        this.closedDateTime = closedDateTime;
        this.recruitsNumber = recruitsNumber;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.region = region;
        this.category = category;
        this.jobState = jobState;
        this.educationLevel = educationLevel;
        this.specificClass = specificClass;
        this.proposal = proposal;
        this.hit = hit;
        this.deadline = deadline;
        this.createdDate = createdDate;
        this.content = content;
    }
}
