package com.uliieumi.customized.policy.web.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.uliieumi.customized.policy.domain.data.JobState;
import com.uliieumi.customized.policy.domain.data.SpecificClass;
import com.uliieumi.customized.policy.domain.entity.Policy;
import com.uliieumi.customized.policy.domain.service.PolicyService;
import com.uliieumi.customized.policy.web.dto.CustomValidationException;
import com.uliieumi.customized.policy.web.dto.PageDTO;
import com.uliieumi.customized.policy.web.dto.PolicySearchForm;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.uliieumi.customized.policy.domain.data.EducationLevel.HSGRAD;
import static com.uliieumi.customized.policy.domain.data.EducationLevel.UNIVENROLL;
import static com.uliieumi.customized.policy.domain.data.PolicyCategory.FINANCE;
import static com.uliieumi.customized.policy.domain.data.PolicyCategory.STARTUP;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@Slf4j
class PolicyControllerTest {

    @InjectMocks
    private PolicyController policyController;

    @Mock
    private PolicyService policyService;

    private MockMvc mockMvc; // mockMvc 생성

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(policyController).build();
    }

    @Test
    @DisplayName("정책 조건 검색")
    void policyList() throws Exception {

        //given
        PolicySearchForm policySearchForm = policySearchForm();

        doReturn(policies())
                .when(policyService)
                .searchPolicy(any(PolicySearchForm.class), anyInt(), anyInt(), anyBoolean());


        doReturn(paging())
                .when(policyService)
                .pagingSearchParam(any(PolicySearchForm.class), anyInt(), anyInt());

        //when
        List<Policy> policies = policyService.searchPolicy(policySearchForm, 6, 1, true);
        PageDTO paging = policyService.pagingSearchParam(policySearchForm, 6, 1);


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String requestBody = objectMapper.writeValueAsString(policySearchForm);


        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/policy/list")
                        .param("size", "6")
                        .param("page", "1")
                        .param("sort", "true")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        );


        //then
        MvcResult mvcResult = resultActions
                .andExpect(status().isOk())
                .andDo(print()) // 결과를 출력하여 디버깅에 도움
                .andReturn(); // ResultActions로부터 MvcResult를 얻는다.



        // MvcResult에서 MockHttpServletResponse를 얻는다.
        MockHttpServletResponse response = mvcResult.getResponse();
        response.setCharacterEncoding("UTF-8");

        // MockHttpServletResponse의 body에 있는 JSON 문자열
        String responseBody = response.getContentAsString();
        log.info("responseBody = {}", responseBody);

        // JSON 파싱
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        // "policyDTO" 배열에서 "id" 및 "region" 값을 추출
        int id = jsonNode.get("policyDTO").get(0).get("id").asInt();
        String[] regions = objectMapper.convertValue(
                jsonNode.get("policyDTO").get(0).get("region"), String[].class);

        System.out.println("regions = " + regions);
        System.out.println("policies.get(0).getRegion() = " + policies.get(0).getRegion());
        
        assertThat(policies.get(0).getId()).isEqualTo(id);
        assertThat(regions.length).isEqualTo(3);
        assertThat(paging.getPage()).isEqualTo(1);
    }



    @Test
    @DisplayName("policySearchForm 바인딩 실패")
    void bindingError() throws Exception {

        //given
        PolicySearchForm errorSearchForm = ErrorSearchForm();

        doReturn(policies())
                .when(policyService)
                .searchPolicy(any(PolicySearchForm.class), anyInt(), anyInt(), anyBoolean());


        doReturn(paging())
                .when(policyService)
                .pagingSearchParam(any(PolicySearchForm.class), anyInt(), anyInt());

        //when
        List<Policy> policies = policyService.searchPolicy(errorSearchForm, 6, 1, true);

        PageDTO paging = policyService.pagingSearchParam(errorSearchForm, 6, 1);


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String requestBody = objectMapper.writeValueAsString(errorSearchForm);

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/policy/list")
                        .param("size", "6")
                        .param("page", "1")
                        .param("sort", "true")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        );


        //then
        MvcResult mvcResult = resultActions
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Exception resolvedException = mvcResult.getResolvedException();
        // 에러 정보를 추출하거나 처리할 수 있음
        // 여기에서 발생한 에러는 'result.getResolvedException()'으로 가져올 수 있음

        Assertions.assertThat(resolvedException).isInstanceOf(CustomValidationException.class);
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


    private List<Policy> policies() {
        ArrayList<Policy> policies = new ArrayList<>();
        Policy policy01 = new Policy(11L, "국민취업지원제도", "images/4fc17a16-7b68-4a46-9621-3cac48d568b1.octet-stream",
                "images/2272a706-a0b1-4f95-a4aa-838f71c18c3e.octet-stream",
                "취업을 원하는 사람에게 취업지원서비스를 종합적으로 제공하고 저소득 구직자에게는 생계를 위한 최소화의 소득을 지원하기 위한 제도",
                LocalDate.of(2023,1,1), LocalDate.of(2023,12,31), 9999, 15, 69, "BUSAN,INCHEON,GWANGJU",
                STARTUP, JobState.NOLIMIT, HSGRAD, SpecificClass.NOLIMIT, "온라인신청",
                24, "상시", "2023-09-07 13:51:58"
                , "<p>1유형<br>- 요건심사형 : 15~69세 구직자 중</p>", "실제파일이름");


        Policy policy02 = new Policy(4L, "국민취업지원제도", "images/4fc17a16-7b68-4a46-9621-3cac48d568b1.octet-stream",
                "images/2272a706-a0b1-4f95-a4aa-838f71c18c3e.octet-stream",
                "취업을 원하는 사람에게 취업지원서비스를 종합적으로 제공하고 저소득 구직자에게는 생계를 위한 최소화의 소득을 지원하기 위한 제도",
                LocalDate.of(2023,1,1), LocalDate.of(2023,12,31), 9999, 15, 69, "BUSAN",
                FINANCE, JobState.EMPLOYER, UNIVENROLL, SpecificClass.NOLIMIT, "온라인신청",
                15, "상시", "2023-09-07 13:51:00"
                , "<p>1유형<br>- 요건심사형 : 15~69세 구직자 중</p>", "실제파일이름");

        policies.add(policy01);
        policies.add(policy02);

        return policies;
    }


    private PolicySearchForm policySearchForm(){
        PolicySearchForm policySearchForm = new PolicySearchForm();
        List<String> areaList = new ArrayList<>();
        areaList.add("finance");
        areaList.add("startup");
        policySearchForm.setArea(areaList);

        return policySearchForm;
    }


    private PolicySearchForm ErrorSearchForm(){
        PolicySearchForm errorSearchForm = new PolicySearchForm();
        List<String> areaList = new ArrayList<>();
        areaList.add("finance");
        areaList.add("startup");
        errorSearchForm.setAge(-2);
        errorSearchForm.setArea(areaList);

        return errorSearchForm;
    }

}