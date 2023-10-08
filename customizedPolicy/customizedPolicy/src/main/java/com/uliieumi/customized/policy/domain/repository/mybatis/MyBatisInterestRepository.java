package com.uliieumi.customized.policy.domain.repository.mybatis;

import com.uliieumi.customized.policy.domain.entity.Policy;
import com.uliieumi.customized.policy.domain.repository.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisInterestRepository implements InterestRepository {


    private final InterestListMapper interestListMapper;


    @Override
    public void addInterestList(Long memberId, Long policyId) {
        interestListMapper.addInterestList(memberId, policyId);
    }

    @Override
    public void removeInterestList(Long memberId, Long policyId) {
        interestListMapper.removeInterestList(memberId, policyId);
    }

    @Override
    public Integer existLikeHistory(Long memberId, Long policyId) {
        Integer result = interestListMapper.existByMemberIdAndPolicyId(memberId, policyId);
        return result;
    }

    @Override
    public List<Policy> findByCondition(Long memberId, int size, int startPostNum) {
        return interestListMapper.findByCondition(memberId, size, startPostNum);
    }

    @Override
    public int searchBoardCount(Long memberId) {
        return interestListMapper.searchBoardCount(memberId);
    }
}
