package com.uliieumi.customized.policy.web.dto;


import com.uliieumi.customized.policy.domain.data.PolicyRegion;
import com.uliieumi.customized.policy.domain.entity.Policy;
import lombok.Data;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PolicyDto {

    private Long id;//아이디

    private String name; //제목

    private String filePath; //파일경로

    private String shortDescription; //짧은소개

    private LocalDate publishedDateTime; //오픈시간

    private LocalDate closedDateTime; //마감시간

    private Integer recruitsNumber; //모집인원

    private List<String> region; //지역

    private String category; //정책분야

    private String deadline; //신청기간


    public PolicyDto(Policy policy) {
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
    }
}
