package com.uliieumi.customized.policy.domain.repository;

import com.uliieumi.customized.policy.domain.entity.Policy;
import com.uliieumi.customized.policy.web.dto.PolicySearchForm;

import java.util.List;

public interface PolicyRepository {

    List<Policy> findAll(PolicySearchForm form, int size, int startPostNum, boolean sort);

    List<Policy> findByCondition(PolicySearchForm form, int size, int startPostNum, boolean sort);

    int basicBoardCount(PolicySearchForm form);

    int searchBoardCount(PolicySearchForm form, int size, int startPostNum);

    Policy findById(Long id);

    void updatePolicy(int updateHit, Long id);
}
