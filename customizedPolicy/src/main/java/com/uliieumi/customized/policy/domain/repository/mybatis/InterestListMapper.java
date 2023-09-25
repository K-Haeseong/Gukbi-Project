package com.uliieumi.customized.policy.domain.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InterestListMapper {

    void addInterestList(@Param("memberId") Long memberId, @Param("policyId")Long policyId);

    void removeInterestList(@Param("memberId") Long memberId, @Param("policyId")Long policyId);

    Integer existByMemberIdAndPolicyId(@Param("memberId") Long memberId, @Param("policyId")Long policyId);
}
