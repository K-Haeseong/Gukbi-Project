package com.uliieumi.customized.policy.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Getter
@Setter
@ToString
public class PolicySearchForm {

    @Length(max = 20)
    private String name;

    @PositiveOrZero
    private Integer age;

    private List<String> area;

    private List<String> educationLevel;

    private List<String> jobState;

    private List<String> region;

    private List<String> specificClass;

}
