package com.uliieumi.customized.policy.web.controller;


import com.uliieumi.customized.policy.domain.data.Role;
import com.uliieumi.customized.policy.domain.entity.Member;
import com.uliieumi.customized.policy.domain.repository.JpaMemberRepository;
import com.uliieumi.customized.policy.domain.service.PolicyService;
import com.uliieumi.customized.policy.web.dto.*;
import com.uliieumi.customized.policy.web.dto.ErrorResult;
import com.uliieumi.customized.policy.web.security.UserInfo;
import com.uliieumi.customized.policy.web.security.jwt.AuthUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/policy/")
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService policyService;
    private final JpaMemberRepository memberRepository;


    @ExceptionHandler
    public ResponseEntity<ErrorResult> CustomValidationException(CustomValidationException e) {
        log.info("e ={}", e.getErrorResult());
        return ResponseEntity
                .badRequest()
                .body(e.getErrorResult());
    }

    @ExceptionHandler
    public ResponseEntity<String> IllegalArgumentException(IllegalArgumentException e) {
        log.info("e ={}", e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }


    @GetMapping("list")
    public String list(Model model, @AuthUser UserInfo userInfo) {
        List<PolicyDto> policies = policyService.searchPolicy(new PolicySearchForm(), 6, 1, true)
                .stream()
                .map(policy -> new PolicyDto(policy))
                .collect(Collectors.toList());

        PageDTO paging = policyService.pagingSearchParam(new PolicySearchForm(), 6, 1);


        @Data
        @AllArgsConstructor
        class MemberInterest {
            private int age;

            private String category; //정책분야

            private String region; //지역

            private String jobState; //취업상태

            private String educationLevel; //학력

            private String specificClass; //특정계층
        }

        log.info("userId = {}", userInfo != null ? userInfo.getId() : "없음");

        // 로그인 유무 확인
        MemberInterest memberInterest = null;
        if (userInfo != null && userInfo.getRole().equals(Role.MEMBER)) {
            Member member = memberRepository.findById(userInfo.getId())
                    .orElseThrow(() -> new IllegalArgumentException("위조된 데이터"));

            memberInterest = new MemberInterest(
                    member.getAge(),
                    member.getInterestCategory().param,
                    member.getRegion().param,
                    member.getJobState().param,
                    member.getEducationLevel().param,
                    member.getSpecificClass().param
            );
        }


        model.addAttribute("memberInterest", memberInterest);
        model.addAttribute("paging", paging);
        model.addAttribute("policies", policies);
        return "policy/list";
    }


    @PostMapping(value = "list", produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public ResponseEntity<Object> policyList(@RequestBody @Validated PolicySearchForm form,
                                             BindingResult bindingResult,
                                             @RequestParam int size,
                                             @RequestParam int page,
                                             @RequestParam boolean sort) {

        log.info("List 매핑 시 들어오는 데이터 form, size, page, sort = {} {} {} {}", form, size, page, sort);

        if (bindingResult.hasFieldErrors()) {
            FieldError error = bindingResult.getFieldErrors().get(0);
            ErrorResult errorResult = new ErrorResult(HttpServletResponse.SC_BAD_REQUEST, error.getDefaultMessage(), error.getCode(), error.getField());
            throw new CustomValidationException(errorResult);
        }


        List<PolicyDto> policies = policyService.searchPolicy(form, size, page, sort).stream()
                .map(policy -> new PolicyDto(policy))
                .collect(Collectors.toList());

        log.info("POST 매핑 시 들어오는 데이터 form, size, page, sort = {} {} {} {}", form, size, page, sort);

        PageDTO paging = policyService.pagingSearchParam(form, size, page);

        PolicyPageDTO policyPageDTO = new PolicyPageDTO(policies, paging);

        return new ResponseEntity<>(policyPageDTO, HttpStatus.OK);
    }



    @GetMapping("detail/{id}")
    public String detail(@PathVariable("id") Long policyId,
                         @AuthUser UserInfo userInfo,
                         Model model) {

        boolean hasInterest = false;

        if (userInfo != null && userInfo.getRole().equals(Role.MEMBER)) {
            Long memberId = userInfo.getId();
            Integer result = policyService.existLikeHistory(memberId, policyId);
            hasInterest = (result == 1) ? true : false;
        }


        DetailPolicyDto foundPolicy = policyService.findPolicyById(policyId);
        int updateHit = foundPolicy.getHit() + 1;
        policyService.updateHit(updateHit, policyId);


        model.addAttribute("hasInterest", hasInterest);
        model.addAttribute("foundPolicy", foundPolicy);
        return "policy/detail";
    }



    @PostMapping("detail/{id}")
    @ResponseBody
    public ResponseEntity<Object> detail(@PathVariable("id") Long policyId,
                                         @RequestParam("interest") boolean interestValue,
                                         @AuthUser UserInfo userInfo) {

        log.info("interestValue = {}", interestValue);

        Long memberId = userInfo.getId();
        if (userInfo != null && userInfo.getRole().equals(Role.MEMBER)) {
            if (interestValue) {
                policyService.removeInterestFromList(memberId, policyId);
            } else {
                policyService.addInterestToList(memberId, policyId);
            }
            return ResponseEntity.ok().build();
        } else {
            throw new IllegalArgumentException("위조된 데이터");
        }
    }


    @GetMapping("interest")
    public String interestList(Model model, @AuthUser UserInfo userInfo) {

        Long memberId = userInfo.getId();
        List<PolicyDto> policies = policyService.getInterestList(memberId, 6, 1)
                .stream()
                .map(policy -> new PolicyDto(policy))
                .collect(Collectors.toList());

        PageDTO paging = policyService.interestPaging(memberId, 6, 1);


        model.addAttribute("paging", paging);
        model.addAttribute("policies", policies);
        return "policy/interest";
    }


    @PostMapping("interest")
    @ResponseBody
    public ResponseEntity<Object> interestList(@AuthUser UserInfo userInfo,
                                               @RequestParam int size,
                                               @RequestParam int page) {

        log.info("List 매핑 시 들어오는 데이터 form, size, page, sort = {} {} {} {}", userInfo, size, page);


        Long memberId = userInfo.getId();
        List<PolicyDto> policies = policyService.getInterestList(memberId, size, page).stream()
                .map(policy -> new PolicyDto(policy))
                .collect(Collectors.toList());

        PageDTO paging = policyService.interestPaging(memberId, size, page);

        PolicyPageDTO policyPageDTO = new PolicyPageDTO(policies, paging);

        return new ResponseEntity<>(policyPageDTO, HttpStatus.OK);
    }


}// controller 끝