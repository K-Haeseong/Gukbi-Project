package com.uliieumi.customized.policy.domain.service;

import com.uliieumi.customized.policy.domain.entity.Policy;
import com.uliieumi.customized.policy.web.dto.DetailPolicyDto;
import com.uliieumi.customized.policy.web.dto.PageDTO;
import com.uliieumi.customized.policy.web.dto.PolicySearchForm;

import java.util.List;

public interface PolicyService {


    List<Policy> searchPolicy(PolicySearchForm form, int size, int page, boolean sort);

    PageDTO pagingSearchParam(PolicySearchForm form, int size, int page);

    DetailPolicyDto findPolicyById(Long id);

    void updateHit(int updateHit, Long id);

    void addInterestToList(Long memberId, Long policyId);

    void removeInterestFromList(Long memberId, Long policyId);

    Integer existLikeHistory(Long memberId, Long policyId);

    List<Policy> getInterestList(Long memberId, int size, int page);

    PageDTO interestPaging(Long memberId, int size, int page);
}
