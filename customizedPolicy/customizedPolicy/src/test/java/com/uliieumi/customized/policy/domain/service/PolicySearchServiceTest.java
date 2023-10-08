package com.uliieumi.customized.policy.domain.service;

import com.uliieumi.customized.policy.domain.data.PolicyCategory;
import com.uliieumi.customized.policy.domain.repository.PolicyRepository;
import com.uliieumi.customized.policy.web.dto.PageDTO;
import com.uliieumi.customized.policy.web.dto.PolicySearchForm;
import lombok.Data;
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
import static org.mockito.Mockito.*;

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

        int size = 6;
        int page = 1;
        int boardCount = 2;

        doReturn(boardCount).when(policyRepository).searchBoardCount(any(PolicySearchForm.class));

        //when
        PageDTO ResultPageDTO = policySearchService.pagingSearchParam(policySearchForm, size, page);

        //then
//      assertThat(ResultPageDTO).isEqualTo(CreatePageDTO()); //PageDTO에 EqualsAndHashCode를 적용해서 해결도 가능
        assertThat(ResultPageDTO).usingRecursiveComparison().isEqualTo(CreatePageDTO());

        // PolicyRepository의 searchBoardCount 메서드가 1번 호출되었는지 검증
        verify(policyRepository, times(1)).searchBoardCount(any(PolicySearchForm.class));
    }




    private int CreateStartPostNum(int size, int page) {
        return size * ( page - 1 );
    }

    private PolicySearchForm policySearchForm(){
        PolicySearchForm policySearchForm = new PolicySearchForm();
        List<PolicyCategory> areaList = new ArrayList<>();
        areaList.add(PolicyCategory.FINANCE);
        areaList.add(PolicyCategory.STARTUP);
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

}

