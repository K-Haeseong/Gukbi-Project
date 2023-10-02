package com.uliieumi.customized.policy.domain.service;

import com.uliieumi.customized.policy.domain.entity.Policy;
import com.uliieumi.customized.policy.web.dto.DetailPolicyDto;
import com.uliieumi.customized.policy.web.dto.PageDTO;
import com.uliieumi.customized.policy.web.dto.PolicySearchForm;

import java.util.List;

public interface PolicyService {

    // 정책 조건 검색
    List<Policy> searchPolicy(PolicySearchForm form, int size, int page, boolean sort);


    // 기본 정책 리스트 페이징
    PageDTO pagingSearchParam(PolicySearchForm form, int size, int page);


    // 정책 id로 정책 1개 찾기
    DetailPolicyDto findPolicyById(Long id);


    // 조회수 증가
    void updateHit(int updateHit, Long id);

    // 내 관심 정책 리스트에서 추가
    void addInterestToList(Long memberId, Long policyId);


    // 내 관심 정책 리스트에서 제거
    void removeInterestFromList(Long memberId, Long policyId);


    // 내 관심 정책에 추가 되어 있는지 확인
    Integer existLikeHistory(Long memberId, Long policyId);

    // 내 관심 정책 리스트
    List<Policy> getInterestList(Long memberId, int size, int page);

    // 내 관심 정책 페이징
    PageDTO interestPaging(Long memberId, int size, int page);
}
