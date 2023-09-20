package com.uliieumi.customized.policy.domain.service;

import com.uliieumi.customized.policy.domain.repository.PolicyRepository;
import com.uliieumi.customized.policy.web.dto.PageDTO;
import com.uliieumi.customized.policy.web.dto.PolicyDto;
import com.uliieumi.customized.policy.web.dto.PolicySearchForm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
@Slf4j
class PolicySearchServiceTest {

    @InjectMocks
    private PolicySearchService policySearchService;

    @Mock
    private PolicyRepository policyRepository;


    @Test
    @DisplayName("startPostNum 값 확인")
    void startPostNumCheck() {
        //given
        int size = 6;
        int page = 1;

        //then
        int startPostNum = CreateStartPostNum(6, 1);

        //when
        assertThat(startPostNum).isEqualTo(0);
    }



    @Test
    @DisplayName("페이징 번호 생성 확인")
    void pagingSearchParam() {
        //given
        PolicySearchForm policySearchForm = policySearchForm();

        doReturn(new PageDTO().getBoardCount())
                .when(policyRepository)
                .searchBoardCount(any(PolicySearchForm.class));

        PageDTO pageDTO = CreatePageDTO();
//        PageDTO pageDTO = policySearchService.pagingSearchParam(policySearchForm, 6, 1); 이거 사용?

        //when

        int boardCount = policyRepository.searchBoardCount(policySearchForm);
        PageDTO ResultPageDTO = processPaging(boardCount, 6, 1);


        //then
        assertThat(ResultPageDTO).isEqualTo(pageDTO); //PageDTO에 EqualsAndHashCode를 적용해서 해결
        assertThat(ResultPageDTO).usingRecursiveComparison().isEqualTo(pageDTO);


//        assertThat(ResultPageDTO).isEqualTo(pageDTO); //이렇게 비교 하는게 맞는건가?
    }




    private int CreateStartPostNum(int size, int page) {
        return size * ( page - 1 );
    }

    private PolicySearchForm policySearchForm(){
        PolicySearchForm policySearchForm = new PolicySearchForm();
        List<String> areaList = new ArrayList<>();
        areaList.add("finance");
        areaList.add("startup");
        policySearchForm.setArea(areaList);

        return policySearchForm;
    }

    private PageDTO CreatePageDTO() {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(1);
        pageDTO.setMaxPage(1);
        pageDTO.setStartPage(1);
        pageDTO.setEndPage(1);
        pageDTO.setBoardCount(2);
        return pageDTO;
    }


    private PageDTO processPaging(int boardCount, int size, int  page) {

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

}