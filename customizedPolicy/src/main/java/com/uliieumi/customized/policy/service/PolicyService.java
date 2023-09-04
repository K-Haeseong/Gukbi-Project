package com.uliieumi.customized.policy.service;

import com.uliieumi.customized.policy.domain.Policy;
import com.uliieumi.customized.policy.dto.PageDTO;
import com.uliieumi.customized.policy.dto.PolicySearchForm;

import java.util.List;

public interface PolicyService {

    List<Policy> searchPolicy(PolicySearchForm form, int size, int page);

    PageDTO pagingParam(PolicySearchForm form, int size, int page);

}
