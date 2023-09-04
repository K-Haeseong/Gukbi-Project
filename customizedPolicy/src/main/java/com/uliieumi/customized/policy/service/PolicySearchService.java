package com.uliieumi.customized.policy.service;

import com.uliieumi.customized.policy.domain.Policy;
import com.uliieumi.customized.policy.dto.PageDTO;
import com.uliieumi.customized.policy.dto.PolicySearchForm;
import com.uliieumi.customized.policy.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PolicySearchService implements PolicyService{

    private final PolicyRepository policyRepository;
    @Override
    public List<Policy> searchPolicy(PolicySearchForm form, int size, int page) {
//        offset  0~5   6~11   12~17
//        page     1      2      3
//        size     6 or 12
        int startPostNum = size*(page-1);
        return policyRepository.findAll(form, size, startPostNum);
    }

    @Override
    public PageDTO pagingParam(PolicySearchForm form, int size, int page) {

        // 조회된 게시글 수
        int boardCount = policyRepository.boardCount(form);

        int remainder = boardCount % size;
        int pageCount = boardCount / size;

        // 전체 페이지 개수
        int maxPage = (remainder==0) ? pageCount : pageCount + 1;

        // 보여줄 페이지 번호의 개수
        int pageSize = 5;

        // 현재 페이지의 시작 페이지 번호
        int startPage = page-(page-1) % pageSize;

        // 현재 페이지의 마지막 페이지 번호
        int endPage =  startPage + (pageSize-1);

        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setStartPage(startPage);
        pageDTO.setEndPage(endPage);
        return pageDTO;
    }
}