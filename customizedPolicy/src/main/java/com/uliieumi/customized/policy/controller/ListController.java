package com.uliieumi.customized.policy.controller;

import com.uliieumi.customized.policy.dto.ErrorResult;
import com.uliieumi.customized.policy.dto.PolicyDto;
import com.uliieumi.customized.policy.dto.PolicySearchForm;
import com.uliieumi.customized.policy.service.PolicyService;
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

@Slf4j
@Controller
@RequestMapping("/policy/")
@RequiredArgsConstructor
public class ListController {

    private final PolicyService policyService;

    @GetMapping("list")
    public String list(Model model) {
        List<PolicyDto> results = policyService.searchPolicy(new PolicySearchForm(),1,1).stream()
                .map(policy -> new PolicyDto(policy))
                .collect(Collectors.toList());

        model.addAttribute("policies", results);

        return "policy/list";
    }


    @PostMapping("list")
    @ResponseBody
    public ResponseEntity<Object> specificList(@RequestBody @Validated PolicySearchForm form,
                                       BindingResult bindingResult, @RequestParam int size, @RequestParam int page) {

        log.info("size,page = {} {}",size, page);

        if(bindingResult.hasErrors()) {
            ArrayList<ErrorResult> errorResults = new ArrayList<>();
            bindingResult.getAllErrors().forEach(error -> errorResults.add(new ErrorResult(error.getDefaultMessage(), error.getCode())));
            return new ResponseEntity<>(errorResults, HttpStatus.BAD_REQUEST);
        }

        List<PolicyDto> results = policyService.searchPolicy(form,size,page).stream()
                .map(policy -> new PolicyDto(policy))
                .collect(Collectors.toList());

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("detail/{id}")
    public String detail(@PathVariable("id") Long id) {
        return "policy/detail";
    }

}
