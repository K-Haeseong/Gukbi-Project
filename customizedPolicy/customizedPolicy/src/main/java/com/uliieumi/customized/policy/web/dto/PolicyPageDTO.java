package com.uliieumi.customized.policy.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PolicyPageDTO {

    private List<PolicyDto> policyDTO;

    private PageDTO pageDTO;


    public PolicyPageDTO(List<PolicyDto> policyDTO, PageDTO pageDTO) {
        this.policyDTO = policyDTO;
        this.pageDTO = pageDTO;
    }
}
