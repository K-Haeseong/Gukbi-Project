package com.uliieumi.customized.policy.domain;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
public class Policy {

    private Long id;

    private String name;

    private Integer age;

    private String category;

    private String educationLevel;

    private String jobState;

    private String region;

    private String specificClass;
}
