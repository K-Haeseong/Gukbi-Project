package com.uliieumi.customized.policy.domain.repository;

import com.uliieumi.customized.policy.domain.entity.Policy;
import com.uliieumi.customized.policy.web.dto.PolicySearchForm;

import java.util.List;

public interface PolicyRepository {


    List<Policy> findByCondition(PolicySearchForm form, int size, int startPostNum, boolean sort);


    int searchBoardCount(PolicySearchForm form);

    Policy findById(Long id);

    void updatePolicy(int updateHit, Long id);

}
