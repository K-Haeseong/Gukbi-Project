package com.uliieumi.customized.policy.web.controller;

import com.uliieumi.customized.policy.domain.data.Role;
import com.uliieumi.customized.policy.web.security.Account;
import com.uliieumi.customized.policy.web.security.jwt.JwtAuthSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final JwtAuthSuccessHandler jwtAuthSuccessHandler;

    @GetMapping("/login/member")
    public String loginMem(HttpServletRequest request, HttpServletResponse response) {
        Account account = new Account(1L, "member", Role.MEMBER);
        jwtAuthSuccessHandler.authenticationSuccess(request, response, account);
        return "redirect:/policy/list";
    }

    @GetMapping("/login/enterprise")
    public String loginEnt(HttpServletRequest request, HttpServletResponse response) {
        Account account = new Account(1L, "enterprise", Role.ENTERPRISE);
        jwtAuthSuccessHandler.authenticationSuccess(request, response,account);
        return "redirect:/policy/list";
    }
}
