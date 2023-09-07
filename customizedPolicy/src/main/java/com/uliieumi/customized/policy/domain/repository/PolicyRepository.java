package com.uliieumi.customized.policy.domain.repository;

import com.uliieumi.customized.policy.domain.entity.Policy;
import com.uliieumi.customized.policy.web.dto.PolicySearchForm;

import java.util.List;

public interface PolicyRepository {

    List<Policy> findAll(PolicySearchForm form, int size, int startPostNum);

    int boardCount(PolicySearchForm form);
}
