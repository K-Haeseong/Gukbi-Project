package com.uliieumi.customized.policy.domain.repository.mybatis;

import com.uliieumi.customized.policy.domain.entity.Policy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InterestListMapper {

    void addInterestList(@Param("memberId") Long memberId, @Param("policyId")Long policyId);

    void removeInterestList(@Param("memberId") Long memberId, @Param("policyId")Long policyId);

    Integer existByMemberIdAndPolicyId(@Param("memberId") Long memberId, @Param("policyId")Long policyId);

    List<Policy> findByCondition(@Param("memberId")Long memberId, @Param("size")int size, @Param("startPostNum")int startPostNum);

    int searchBoardCount(@Param("memberId")Long memberId);
}
