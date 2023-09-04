package com.uliieumi.customized.policy.repository.mybatis;

import com.uliieumi.customized.policy.domain.Policy;
import com.uliieumi.customized.policy.dto.PolicySearchForm;
import com.uliieumi.customized.policy.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisPolicyRepository implements PolicyRepository {

    private final PolicyMapper policyMapper;

    @Override
    public List<Policy> findAll(PolicySearchForm form, int size, int startPostNum) {
        return policyMapper.findAll(form, size, startPostNum);
    }

    @Override
    public int boardCount(PolicySearchForm form) {

        return policyMapper.boardCount(form);
    }
}
