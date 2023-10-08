package com.uliieumi.customized.policy.web.dto;

import com.uliieumi.customized.policy.domain.data.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
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

    private List<PolicyCategory> area;

    private List<EducationLevel> educationLevel;

    private List<JobState> jobState;

    private List<PolicyRegion> region;

    private List<SpecificClass> specificClass;

}
