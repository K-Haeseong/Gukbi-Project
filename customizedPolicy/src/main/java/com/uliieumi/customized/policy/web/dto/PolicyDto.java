package com.uliieumi.customized.policy.web.dto;


import com.uliieumi.customized.policy.domain.data.PolicyCategory;
import com.uliieumi.customized.policy.domain.entity.Policy;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PolicyDto {

    private Long id;//아이디

    private String name; //제목

    private String filePath;//파일경로

    private String thumbnail;//썸네일

    //private String posterImage;//관련 포스터이미지

    private String shortDescription;//짧은소개

    private LocalDate publishedDateTime;// 오픈시간

    private LocalDate closedDateTime;  //마감시간

    private Integer recruitsNumber;//모집인원

    private String region;//지역

    private String category;//정책분야

    private String deadline;//신청기간


    public PolicyDto(Policy policy) {
        this.id = policy.getId();
        this.name = policy.getName();
        this.filePath = policy.getFilePath();
        this.thumbnail = policy.getThumbnail();
        this.shortDescription = policy.getShortDescription();
        this.publishedDateTime = policy.getPublishedDateTime();
        this.closedDateTime = policy.getClosedDateTime();
        this.recruitsNumber = policy.getRecruitsNumber();
        this.region = policy.getRegion();
        this.category = policy.getCategory().name;
        this.deadline = policy.getDeadline();
    }
}
