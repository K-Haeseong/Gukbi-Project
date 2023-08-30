package com.uliieumi.customized.policy.controller;

import com.uliieumi.customized.policy.dto.PolicySearchForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/policy/")
public class ListController {

    @GetMapping("list")
    public String list() {
        return "policy/list.html";
    }


    @PostMapping("list")
    public String specificList(@RequestBody PolicySearchForm form)
    {
        log.info("넘어오는 데이터 : {}" , form);
        System.out.println(form.getAge());
        System.out.println(form.getArea());
        return "policy/list.html";
    }

    @GetMapping("detail")
    public String detail() {
        return "policy/detail.html";
    }

}
