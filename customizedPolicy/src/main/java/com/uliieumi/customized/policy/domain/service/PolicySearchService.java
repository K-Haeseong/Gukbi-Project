package com.uliieumi.customized.policy.domain.service;

import com.uliieumi.customized.policy.domain.entity.Policy;
import com.uliieumi.customized.policy.domain.repository.InterestRepository;
import com.uliieumi.customized.policy.domain.repository.PolicyRepository;
import com.uliieumi.customized.policy.web.dto.DetailPolicyDto;
import com.uliieumi.customized.policy.web.dto.PageDTO;
import com.uliieumi.customized.policy.web.dto.PolicySearchForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PolicySearchService implements PolicyService {

    private final PolicyRepository policyRepository;
    private final InterestRepository interestRepository;


    // 정책 조건 검색
    @Override
    public List<Policy> searchPolicy(PolicySearchForm form, int size, int page, boolean sort) {
//        offset  0~5   6~11   12~17
//        page     1      2      3
//        size     6 or 12
        int startPostNum = size*(page-1);
        return policyRepository.findByCondition(form, size, startPostNum, sort);
    }

    // 기본 정책 리스트 페이징
    @Override
    public PageDTO pagingSearchParam(PolicySearchForm form, int size, int page) {

        // 조회된 게시글 수
        int boardCount = policyRepository.searchBoardCount(form);

        int remainder = boardCount % size;
        int pageCount = boardCount / size;

        // 전체 페이지 개수
        int maxPage = (remainder==0) ? pageCount : pageCount + 1;

        // 보여줄 페이지 번호의 개수
        int pageSize = 5;

        // 현재 페이지의 시작 페이지 번호
        int startPage = page-(page-1) % pageSize;

        // 현재 페이지의 마지막 페이지 번호
        int endPage =  maxPage <  startPage + (pageSize-1) ? maxPage : startPage + (pageSize-1);

        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setStartPage(startPage);
        pageDTO.setEndPage(endPage);
        pageDTO.setBoardCount(boardCount);
        return pageDTO;
    }


    // 정책 id로 정책 1개 찾기
    @Override
    public DetailPolicyDto findPolicyById(Long id) {
        Policy foundPolicy = policyRepository.findById(id);

        return new DetailPolicyDto(foundPolicy);
    }

    // 조회수 증가
    @Override
    public void updateHit(int updateHit, Long id) {
        policyRepository.updatePolicy(updateHit, id);
    }

    // 내 관심 정책 리스트에서 추가
    @Override
    public void addInterestToList(Long memberId, Long policyId) {
        interestRepository.addInterestList(memberId, policyId);
    }

    // 내 관심 정책 리스트에서 제거
    @Override
    public void removeInterestFromList(Long memberId, Long policyId) {
        interestRepository.removeInterestList(memberId, policyId);
    }

    // 내 관심 정책에 추가 되어 있는지 확인
    @Override
    public Integer existLikeHistory(Long memberId, Long policyId) {
        Integer result = interestRepository.existLikeHistory(memberId, policyId);
        return result;
    }

    // 내 관심 정책 리스트
    @Override
    public List<Policy> getInterestList(Long memberId, int size, int page) {
        int startPostNum = size*(page-1);
        return interestRepository.findByCondition(memberId, size, startPostNum);
    }

    // 내 관심 정책 페이징
    @Override
    public PageDTO interestPaging(Long memberId, int size, int page) {
        // 조회된 게시글 수
        int boardCount = interestRepository.searchBoardCount(memberId);

        int remainder = boardCount % size;
        int pageCount = boardCount / size;

        // 전체 페이지 개수
        int maxPage = (remainder==0) ? pageCount : pageCount + 1;

        // 보여줄 페이지 번호의 개수
        int pageSize = 5;

        // 현재 페이지의 시작 페이지 번호
        int startPage = page-(page-1) % pageSize;

        // 현재 페이지의 마지막 페이지 번호
        int endPage =  maxPage <  startPage + (pageSize-1) ? maxPage : startPage + (pageSize-1);

        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setStartPage(startPage);
        pageDTO.setEndPage(endPage);
        pageDTO.setBoardCount(boardCount);
        return pageDTO;
    }


}// service 끝