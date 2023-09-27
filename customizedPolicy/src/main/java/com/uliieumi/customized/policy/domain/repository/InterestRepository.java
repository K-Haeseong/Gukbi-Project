package com.uliieumi.customized.policy.domain.repository;

import com.uliieumi.customized.policy.domain.entity.Policy;

import java.util.List;

public interface InterestRepository {

    void addInterestList(Long memberId, Long policyId);

    void removeInterestList(Long memberId, Long policyId);

    Integer existLikeHistory(Long memberId, Long policyId);

    List<Policy> findByCondition(Long memberId, int size, int startPostNum);

    int searchBoardCount(Long memberId);
}
