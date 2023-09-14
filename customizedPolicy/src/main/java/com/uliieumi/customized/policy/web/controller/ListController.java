package com.uliieumi.customized.policy.web.controller;


import com.uliieumi.customized.policy.domain.service.PolicyService;
import com.uliieumi.customized.policy.web.dto.*;
import com.uliieumi.customized.policy.web.dto.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.uliieumi.customized.policy.domain.data.EducationLevel.UNIVGRAD;
import static com.uliieumi.customized.policy.domain.data.JobState.JOBLESS;
import static com.uliieumi.customized.policy.domain.data.PolicyCategory.JOB;
import static com.uliieumi.customized.policy.domain.data.PolicyRegion.SEOUL;
import static com.uliieumi.customized.policy.domain.data.SpecificClass.NOLIMIT;

@Slf4j
@Controller
@RequestMapping("/policy/")
@RequiredArgsConstructor
public class ListController {

    private final PolicyService policyService;

    @GetMapping("list")
    public String list(Model model) {
        List<PolicyDto> policies = policyService.searchAllPolicy(new PolicySearchForm(), 6,1, true)
                .stream()
                .map(policy -> new PolicyDto(policy))
                .collect(Collectors.toList());

        PageDTO paging = policyService.pagingBasicParam(new PolicySearchForm(), 6,1, true);


        @Data
        @AllArgsConstructor
        class MemberInterest{
            private int age;

            private String category; //정책분야

            private String region; //지역

            private String jobState; //취업상태

            private String educationLevel; //학력

            private String specificClass; //특정계층
        }

        MemberInterest memberInterest = new MemberInterest(30, JOB.param, SEOUL.param, JOBLESS.param, UNIVGRAD.param, NOLIMIT.param);

        model.addAttribute("memberInterest", memberInterest);
        model.addAttribute("paging", paging);
        model.addAttribute("policies", policies);
        return "policy/list";
    }


    @PostMapping("list")
    @ResponseBody
    public ResponseEntity<Object> specificList(@RequestBody @Validated PolicySearchForm form, BindingResult bindingResult,
                                               @RequestParam int size, @RequestParam int page, @RequestParam boolean sort) {

        log.info("size,page = {} {} {}", form, size, page);

        if(bindingResult.hasErrors()) {
            ArrayList<ErrorResult> errorResults = new ArrayList<>();
            bindingResult.getAllErrors().forEach(error -> errorResults.add(new ErrorResult(error.getDefaultMessage(), error.getCode())));
            return new ResponseEntity<>(errorResults, HttpStatus.BAD_REQUEST);
        }


        List<PolicyDto> policies;
        PageDTO paging;
        if(form==null) {
            policies = policyService.searchAllPolicy(form,size,page,sort).stream()
                    .map(policy -> new PolicyDto(policy))
                    .collect(Collectors.toList());

            paging= policyService.pagingBasicParam(form,size,page,sort);
        }

        policies = policyService.searchPolicy(form,size,page,sort).stream()
                .map(policy -> new PolicyDto(policy))
                .collect(Collectors.toList());

        paging= policyService.pagingSearchParam(form,size,page,sort);

        PolicyPageDTO policyPageDTO = new PolicyPageDTO(policies, paging);

        return new ResponseEntity<>(policyPageDTO, HttpStatus.OK);
    }

    @GetMapping("detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {

        DetailPolicyDto foundPolicy = policyService.findPolicyById(id);
        int updateHit = foundPolicy.getHit() + 1;
        policyService.updateHit(updateHit, id);


        model.addAttribute("foundPolicy", foundPolicy);
        return "policy/detail";
    }

}
