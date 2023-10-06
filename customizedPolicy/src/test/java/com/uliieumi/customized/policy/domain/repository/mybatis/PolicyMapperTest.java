package com.uliieumi.customized.policy.domain.repository.mybatis;

import com.uliieumi.customized.policy.domain.entity.Policy;
import com.uliieumi.customized.policy.web.dto.PolicySearchForm;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PolicyMapperTest {

    @Autowired
    private PolicyMapper policyMapper;

    @Test
    void testFindByCondition() {
        //given
        PolicySearchForm policySearchForm = policySearchForm();
        int size = 6;
        int startPostNum = 0;
        boolean sort = true;

        //when
        List<Policy> foundPolicies = policyMapper.findByCondition(policySearchForm, 6, 0, true);

        //then
        assertThat(foundPolicies.size()).isEqualTo(6);
    }


    @Test
    void testSearchBoardCount() {
        //given
        PolicySearchForm policySearchForm = policySearchForm();

        //when
        int boardCount = policyMapper.searchBoardCount(policySearchForm);

        //then
        assertThat(boardCount).isEqualTo(10);
    }


    @Test
    void testFindById() {
    //given
    Long id = 11L;

    //when
    Policy foundPolicy = policyMapper.findById(id);

    //then
    assertThat(foundPolicy.getId()).isEqualTo(id);
    }


    @Test
    void testUpdatePolicy() {
    //given
    Long id = 11L;
    int hit = 25;

    //when
    policyMapper.updatePolicy(hit, id);
    Policy foundPolicy = policyMapper.findById(11L);

    //then
    assertThat(foundPolicy.getHit()).isEqualTo(25);
    }


    private PolicySearchForm policySearchForm(){
        PolicySearchForm policySearchForm = new PolicySearchForm();
        List<String> areaList = new ArrayList<>();
        areaList.add("finance");
        areaList.add("startup");
        policySearchForm.setArea(areaList);

        return policySearchForm;
    }
}