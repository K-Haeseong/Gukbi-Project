package com.uliieumi.customized.policy.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PolicySearchForm {

    private String name;

    private Integer age;

    private List<String> area;

    private List<String> educationLevel;

    private List<String> jobState;

    private List<String> region;

    private List<String> specialArea;

}
