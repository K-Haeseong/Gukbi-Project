package com.uliieumi.customized.policy.web.security.jwt;

import com.uliieumi.customized.policy.BeanUtils;
import com.uliieumi.customized.policy.domain.data.Role;
import com.uliieumi.customized.policy.domain.repository.JpaAdminRepository;
import com.uliieumi.customized.policy.domain.repository.JpaEnterpriseRepository;
import com.uliieumi.customized.policy.domain.repository.JpaMemberRepository;

import com.uliieumi.customized.policy.web.exception.NotFoundException;
import com.uliieumi.customized.policy.web.security.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import java.util.Map;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    private final JpaMemberRepository memberRepository;

    private final JpaEnterpriseRepository enterpriseRepository;

    private final JpaAdminRepository adminRepository;

    public JwtAuthorizationFilter() {
        this.tokenProvider = (TokenProvider) BeanUtils.getBean("tokenProvider");
        this.memberRepository = (JpaMemberRepository) BeanUtils.getBean("jpaMemberRepository");
        this.enterpriseRepository = (JpaEnterpriseRepository) BeanUtils.getBean("jpaEnterpriseRepository");
        this.adminRepository = (JpaAdminRepository) BeanUtils.getBean("jpaAdminRepository");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws  IOException, ServletException {

        String accessToken = getAccessToken(request);

        Account user = parseUserSpecification(accessToken);
        if(user != null){
            AbstractAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(user, accessToken, user.getAuthorities());
            authenticated.setDetails(new WebAuthenticationDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticated);
        }

        filterChain.doFilter(request, response);
    }

    private String getAccessToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null) return null;

        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("accessToken"))
                .findAny()
                .map(cookie -> cookie.getValue())
                .orElse(null);
    }

    private Account parseUserSpecification(String token) {

        Map<String, String> claims = Optional.ofNullable(token)
                .filter(subject -> subject.length() >= 10)
                .map(it -> tokenProvider.validateTokenAndGetClaims(it))
                .orElse(null);

        if(claims == null) return null;
        else{
            long id = Long.parseLong(claims.get("id"));
            Role role = Role.valueOf(claims.get("role"));
            if(Role.MEMBER.equals(role)){
                return new Account(memberRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Invalid AccessToken '" + id + "'"))
                );
            }else if(Role.ENTERPRISE.equals(role)){
                return new Account(enterpriseRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Invalid AccessToken '" + id + "'"))
                );
            }else if(Role.ADMIN.equals(role)){
                return new Account(adminRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Invalid AccessToken '" + id + "'"))
                );
            }else{
                return null;
            }
        }

    }
}