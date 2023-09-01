package com.uliieumi.customized.policy.dto;


import com.uliieumi.customized.policy.domain.Policy;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PolicyDto {

    private Long id;
    private String name;
    private String area;
    private String content;
    private LocalDateTime period;
    private String state;
    private int count;


    public PolicyDto(Policy policy) {
        this.id = policy.getId();
        this.name = policy.getName();
        this.area = policy.getCategory();
        this.content = policy.getJobState();
        this.period = LocalDateTime.now();
        this.state = "모집입니당";
        this.count = 30;
    }
}
