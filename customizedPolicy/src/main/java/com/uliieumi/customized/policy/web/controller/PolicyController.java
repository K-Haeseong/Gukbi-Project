package com.uliieumi.customized.policy.web.controller;


import com.uliieumi.customized.policy.domain.data.*;
import com.uliieumi.customized.policy.domain.entity.Member;
import com.uliieumi.customized.policy.domain.repository.JpaMemberRepository;
import com.uliieumi.customized.policy.domain.service.PolicyService;
import com.uliieumi.customized.policy.web.dto.*;
import com.uliieumi.customized.policy.web.dto.ErrorResult;
import com.uliieumi.customized.policy.web.exception.CustomValidationException;
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
import org.springframework.util.StringUtils;
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
        return ResponseEntity
                .badRequest()
                .body(e.getErrorResult());
    }

    @ExceptionHandler
    public ResponseEntity<String> IllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }


    @GetMapping("list")
    public String list(Model model, @AuthUser UserInfo userInfo, @ModelAttribute("form") PolicySearchForm form) {

        // 기존의 SearchForm은 Data가 유지된 채로 model에 담겨야 하기 때문에 기존의 데이터를 유지하는 새 객체 생성

        List<PolicyDto> policies = policyService.searchPolicy(form, 6, 1, true)
                .stream()
                .map(policy -> new PolicyDto(policy))
                .collect(Collectors.toList());

        PageDTO paging = policyService.pagingSearchParam(form, 6, 1);


        @Data
        @AllArgsConstructor
        class MemberInterest {
            private int age; // 나이

            private PolicyCategory category; //정책분야

            private PolicyRegion region; //지역

            private JobState jobState; //취업상태

            private EducationLevel educationLevel; //학력

            private SpecificClass specificClass; //특정계층
        }


        // 로그인 유무 확인
        MemberInterest memberInterest = null;
        if (userInfo != null && userInfo.getRole().equals(Role.MEMBER)) {
            Member member = memberRepository.findById(userInfo.getId())
                    .orElseThrow(() -> new IllegalArgumentException("위조된 데이터"));

            memberInterest = new MemberInterest(
                    member.getAge(),
                    member.getInterestCategory(),
                    member.getRegion(),
                    member.getJobState(),
                    member.getEducationLevel(),
                    member.getSpecificClass()
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

        if (bindingResult.hasFieldErrors()) {
            FieldError error = bindingResult.getFieldErrors().get(0);
            ErrorResult errorResult = new ErrorResult(HttpServletResponse.SC_BAD_REQUEST, error.getDefaultMessage(), error.getCode(), error.getField());
            throw new CustomValidationException(errorResult);
        }

        List<PolicyDto> policies = policyService.searchPolicy(form, size, page, sort).stream()
                .map(policy -> new PolicyDto(policy))
                .collect(Collectors.toList());


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


        Long memberId = userInfo.getId();
        List<PolicyDto> policies = policyService.getInterestList(memberId, size, page).stream()
                .map(policy -> new PolicyDto(policy))
                .collect(Collectors.toList());

        PageDTO paging = policyService.interestPaging(memberId, size, page);

        PolicyPageDTO policyPageDTO = new PolicyPageDTO(policies, paging);

        return new ResponseEntity<>(policyPageDTO, HttpStatus.OK);
    }


}// controller 끝