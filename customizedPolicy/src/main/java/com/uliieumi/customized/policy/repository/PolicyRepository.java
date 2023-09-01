package com.uliieumi.customized.policy.repository;

import com.uliieumi.customized.policy.domain.Policy;
import com.uliieumi.customized.policy.dto.PolicySearchForm;

import java.util.List;

public interface PolicyRepository {

    List<Policy> findAll(PolicySearchForm form, int size, int offset);
}
