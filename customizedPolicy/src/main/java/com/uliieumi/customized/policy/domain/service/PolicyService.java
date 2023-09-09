package com.uliieumi.customized.policy.domain.service;

import com.uliieumi.customized.policy.domain.entity.Policy;
import com.uliieumi.customized.policy.web.dto.PageDTO;
import com.uliieumi.customized.policy.web.dto.PolicySearchForm;

import java.util.List;

public interface PolicyService {

    List<Policy> searchPolicy(PolicySearchForm form, int size, int page);

    PageDTO pagingParam(PolicySearchForm form, int size, int page);
}
