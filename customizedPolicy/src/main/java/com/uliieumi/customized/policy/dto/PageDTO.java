package com.uliieumi.customized.policy.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDTO {

    private int page;       // 현재 페이지
    private int maxPage;    // 전체 페이지 개수
    private int startPage;  // 현재 페이지의 시작 페이지 번호
    private int endPage;    // 현재 페이지의 마지막 페이지 번호
}
