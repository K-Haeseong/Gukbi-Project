package com.uliieumi.customized.policy.web.dto;


import com.uliieumi.customized.policy.domain.data.PolicyRegion;
import com.uliieumi.customized.policy.domain.entity.Policy;
import lombok.Data;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DetailPolicyDto {

    private Long id;//아이디

    private String name; //제목

    private String filePath; // 이미지파일경로
    // 에디터 컨텐츠 이미지 파일 안 나올 경우 대비

    private String shortDescription; //짧은소개

    private LocalDate publishedDateTime; //오픈시간

    private LocalDate closedDateTime; //마감시간

    private Integer recruitsNumber; //모집인원

    private List<String> region; //지역

    private String category; //정책분야

    private String deadline; //신청기간

    private String content; //에디터컨텐츠

    private Integer hit; // 조회수


    public DetailPolicyDto(Policy policy) {
        this.id = policy.getId();
        this.name = policy.getName();
        this.filePath = policy.getFilePath();
        this.shortDescription = policy.getShortDescription();
        this.publishedDateTime = policy.getPublishedDateTime();
        this.closedDateTime = policy.getClosedDateTime();
        this.recruitsNumber = policy.getRecruitsNumber();
        String[] regions = policy.getRegion().split(",");
        this.region = Arrays.stream(regions).map((region) ->
                        PolicyRegion.findByName(region)
                        .text
        ).collect(Collectors.toList());
        this.category = policy.getCategory().text;
        this.deadline = policy.getDeadline();
        this.content = policy.getContent();
        this.hit = policy.getHit();
    }
}
