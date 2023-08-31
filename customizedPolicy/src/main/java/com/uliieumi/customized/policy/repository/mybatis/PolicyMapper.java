package com.uliieumi.customized.policy.repository.mybatis;

import com.uliieumi.customized.policy.domain.Policy;
import com.uliieumi.customized.policy.dto.PolicySearchForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PolicyMapper {

    List<Policy> findAll(PolicySearchForm form);

}
