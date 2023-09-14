package com.uliieumi.customized.policy.domain.repository.mybatis;

import com.uliieumi.customized.policy.domain.entity.Policy;
import com.uliieumi.customized.policy.web.dto.PolicySearchForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PolicyMapper {

    List<Policy> findAll(@Param("form")PolicySearchForm form, @Param("size")int size, @Param("startPostNum")int startPostNum, @Param("sort")boolean sort);

    List<Policy> findByCondition(@Param("form")PolicySearchForm form, @Param("size")int size, @Param("startPostNum")int startPostNum, @Param("sort")boolean sort);

    int basicBoardCount(@Param("form") PolicySearchForm form);

    int searchBoardCount(@Param("form")PolicySearchForm form, @Param("size")int size, @Param("startPostNum")int startPostNum);

    Policy findById(@Param("id") Long id);

    void updatePolicy(@Param("hit")Integer hit, @Param("id") Long id);
}
