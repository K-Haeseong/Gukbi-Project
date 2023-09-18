package com.uliieumi.customized.policy.domain.repository.mybatis;

import com.uliieumi.customized.policy.domain.entity.Policy;
import com.uliieumi.customized.policy.domain.repository.PolicyRepository;
import com.uliieumi.customized.policy.web.dto.PolicySearchForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisPolicyRepository implements PolicyRepository {

    private final PolicyMapper policyMapper;


    @Override
    public List<Policy> findByCondition(PolicySearchForm form, int size, int startPostNum, boolean sort) {
        return policyMapper.findByCondition(form, size, startPostNum, sort);
    }


    @Override
    public int searchBoardCount(PolicySearchForm form) {
        return policyMapper.searchBoardCount(form);
    }

    @Override
    public Policy findById(Long id) {
        return policyMapper.findById(id);
    }

    @Override
    public void updatePolicy(int updateHit, Long id) {
        policyMapper.updatePolicy(updateHit, id);
    }


}
