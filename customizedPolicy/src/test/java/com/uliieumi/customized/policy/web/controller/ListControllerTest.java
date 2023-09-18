package com.uliieumi.customized.policy.web.controller;

import com.uliieumi.customized.policy.domain.data.JobState;
import com.uliieumi.customized.policy.domain.data.SpecificClass;
import com.uliieumi.customized.policy.domain.entity.Policy;
import com.uliieumi.customized.policy.domain.service.PolicyService;
import com.uliieumi.customized.policy.web.dto.PageDTO;
import com.uliieumi.customized.policy.web.dto.PolicyDto;
import com.uliieumi.customized.policy.web.dto.PolicyPageDTO;
import com.uliieumi.customized.policy.web.dto.PolicySearchForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.uliieumi.customized.policy.domain.data.EducationLevel.HSGRAD;
import static com.uliieumi.customized.policy.domain.data.EducationLevel.UNIVENROLL;
import static com.uliieumi.customized.policy.domain.data.PolicyCategory.FINANCE;
import static com.uliieumi.customized.policy.domain.data.PolicyCategory.STARTUP;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ListControllerTest {

    @InjectMocks
    private ListController listController;

    @Mock
    private PolicyService policyService;

    @Autowired
    private MockMvc mockMvc; // mockMvc 생성

    @BeforeEach
    public void init() {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(listController).build();
    }

    @Test
    @DisplayName("정책 조건 검색")
    void policyList() throws Exception {

        //given
        List<Policy> response01 = policies();
        PageDTO response02 = paging();

        doReturn(policies())
                .when(policyService)
                .searchPolicy(any(PolicySearchForm.class), anyInt(), anyInt(), anyBoolean());


        doReturn(paging())
                .when(policyService)
                .pagingSearchParam(any(PolicySearchForm.class), anyInt(), anyInt(), anyBoolean());

        //when
        PolicySearchForm policySearchForm = new PolicySearchForm();
        List<String> areaList = new ArrayList<>();
        areaList.add("finance");
        areaList.add("startup");
        policySearchForm.setArea(areaList);

        List<Policy> policies = policyService.searchPolicy(policySearchForm, 6, 1, true);
        PageDTO paging = policyService.pagingSearchParam(policySearchForm, 6, 1, true);


//        List<PolicyDto> policyList = policies.stream().map(policy -> new PolicyDto(policy)).collect(Collectors.toList());
//        PolicyPageDTO policyPageDTO = new PolicyPageDTO(policyList, paging);
//
//        ResultActions resultActions = mockMvc.perform(
//                MockMvcRequestBuilders.post("/policy/list")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(String.valueOf(policyPageDTO))
//        );


        //then
//        resultActions.andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("policyList"))
//                .andExpect((ResultMatcher) jsonPath("paging"));

        assertThat(policies.get(0).getId()).isEqualTo(response01.get(0).getId());
        assertThat(paging.getPage()).isEqualTo(response02.getPage());
    }

    private PageDTO paging() {
        PageDTO pageDTO = new PageDTO();

        pageDTO.setPage(1);
        pageDTO.setMaxPage(1);
        pageDTO.setStartPage(1);
        pageDTO.setEndPage(1);
        pageDTO.setBoardCount(2);

        return pageDTO;
    }


    List<Policy> policies() {
        ArrayList<Policy> policies = new ArrayList<>();
        Policy policy01 = new Policy(11L, "국민취업지원제도", "images/4fc17a16-7b68-4a46-9621-3cac48d568b1.octet-stream",
                "images/2272a706-a0b1-4f95-a4aa-838f71c18c3e.octet-stream",
                "취업을 원하는 사람에게 취업지원서비스를 종합적으로 제공하고 저소득 구직자에게는 생계를 위한 최소화의 소득을 지원하기 위한 제도",
                LocalDate.of(2023,01,01), LocalDate.of(2023,12,31), 9999, 15, 69, "BUSAN,INCHEON,GWANGJU",
                STARTUP, JobState.NOLIMIT, HSGRAD, SpecificClass.NOLIMIT, "온라인신청",
                24, "상시", "2023-09-07 13:51:58"
                , "<p>1유형<br>- 요건심사형 : 15~69세 구직자 중</p>");


        Policy policy02 = new Policy(4L, "국민취업지원제도", "images/4fc17a16-7b68-4a46-9621-3cac48d568b1.octet-stream",
                "images/2272a706-a0b1-4f95-a4aa-838f71c18c3e.octet-stream",
                "취업을 원하는 사람에게 취업지원서비스를 종합적으로 제공하고 저소득 구직자에게는 생계를 위한 최소화의 소득을 지원하기 위한 제도",
                LocalDate.of(2023,01,01), LocalDate.of(2023,12,31), 9999, 15, 69, "BUSAN",
                FINANCE, JobState.EMPLOYER, UNIVENROLL, SpecificClass.NOLIMIT, "온라인신청",
                15, "상시", "2023-09-07 13:51:00"
                , "<p>1유형<br>- 요건심사형 : 15~69세 구직자 중</p>");

        policies.add(policy01);
        policies.add(policy02);

        return policies;
    }


}