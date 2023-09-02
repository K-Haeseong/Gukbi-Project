package com.uliieumi.customized.policy.service;

import com.uliieumi.customized.policy.domain.Policy;
import com.uliieumi.customized.policy.dto.PolicySearchForm;
import com.uliieumi.customized.policy.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PolicySearchService implements PolicyService{

    private final PolicyRepository policyRepository;
    @Override
    public List<Policy> searchPolicy(PolicySearchForm form, int size, int page) {
//        offset  0   6   12
//        page    1   2   3
//        size    6 or 12
        int offset = size*(page-1);
        return policyRepository.findAll(form, size, offset);
    }
}