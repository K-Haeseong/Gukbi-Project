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
    public List<Policy> findAll(PolicySearchForm form, int size, int startPostNum, boolean sort) {
        return policyMapper.findAll(form, size, startPostNum, sort);
    }

    @Override
    public List<Policy> findByCondition(PolicySearchForm form, int size, int startPostNum, boolean sort) {
        return policyMapper.findByCondition(form, size, startPostNum, sort);
    }

    @Override
    public int basicBoardCount(PolicySearchForm form) {
        return policyMapper.basicBoardCount(form);
    }

    @Override
    public int searchBoardCount(PolicySearchForm form, int size, int startPostNum) {
        return policyMapper.searchBoardCount(form, size, startPostNum);
    }

    @Override
    public Policy findById(Long id) {
        return policyMapper.findById(id);
    }


}
