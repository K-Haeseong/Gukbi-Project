package com.uliieumi.customized.policy.domain.service;

import com.uliieumi.customized.policy.domain.entity.Policy;
import com.uliieumi.customized.policy.web.dto.DetailPolicyDto;
import com.uliieumi.customized.policy.web.dto.PageDTO;
import com.uliieumi.customized.policy.web.dto.PolicySearchForm;

import java.util.List;

public interface PolicyService {

    List<Policy> searchAllPolicy(PolicySearchForm form, int size, int page, boolean sort);

    List<Policy> searchPolicy(PolicySearchForm form, int size, int page, boolean sort);

    PageDTO pagingBasicParam(PolicySearchForm form, int size, int page, boolean sort);

    PageDTO pagingSearchParam(PolicySearchForm form, int size, int page, boolean sort);

    DetailPolicyDto findPolicyById(Long id);

    void updateHit(int updateHit, Long id);
}
