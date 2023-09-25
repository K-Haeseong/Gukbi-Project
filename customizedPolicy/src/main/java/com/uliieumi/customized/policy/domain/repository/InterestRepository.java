package com.uliieumi.customized.policy.domain.repository;

public interface InterestRepository {

    void addInterestList(Long memberId, Long policyId);

    void removeInterestList(Long memberId, Long policyId);

    Integer existLikeHistory(Long memberId, Long policyId);
}
