package com.uliieumi.customized.policy.controller;

import com.uliieumi.customized.policy.domain.Policy;
import com.uliieumi.customized.policy.dto.ErrorResult;
import com.uliieumi.customized.policy.dto.PolicyDto;
import com.uliieumi.customized.policy.dto.PolicySearchForm;
import com.uliieumi.customized.policy.service.PolicySearchService;
import com.uliieumi.customized.policy.service.PolicyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/policy/")
@RequiredArgsConstructor
public class ListController {

    private final PolicyService policyService;

    @GetMapping("list")
    public String list() {
        return "policy/list.html";
    }


    @PostMapping("list")
    @ResponseBody
    public ResponseEntity<Object> specificList(@RequestBody @Validated PolicySearchForm form,
                                       BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            ArrayList<ErrorResult> errorResults = new ArrayList<>();
            bindingResult.getAllErrors().forEach(error -> errorResults.add(new ErrorResult(error.getDefaultMessage(), error.getCode())));
            return new ResponseEntity<>(errorResults, HttpStatus.BAD_REQUEST);
        }

        List<PolicyDto> results = policyService.searchPolicy(form).stream()
                .map(policy -> new PolicyDto(policy))
                .collect(Collectors.toList());


        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("detail")
    public String detail() {
        return "policy/detail.html";
    }

}
