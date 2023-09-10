package com.uliieumi.customized.policy.domain.repository.mybatis;

import com.uliieumi.customized.policy.domain.entity.Policy;
import com.uliieumi.customized.policy.web.dto.PolicySearchForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PolicyMapper {

    List<Policy> findAll(PolicySearchForm form, @Param("size") int size, @Param("startPostNum")int startPostNum);

    int boardCount(PolicySearchForm form);
}