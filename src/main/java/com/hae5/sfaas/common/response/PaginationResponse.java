package com.hae5.sfaas.common.response;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaginationResponse<T> {
    private int totalRecordCount;     // 전체 데이터 수
    private int totalPageCount;       // 전체 페이지 수
    private int nowPage;                // 현재 페이지 번호
    private int limitStart;           // LIMIT 시작 위치
    private boolean hasPrevious;    // 이전 페이지 존재 여부
    private boolean hasNext;    // 다음 페이지 존재 여부
    private List<T> content; // 내용

    public static <T> PaginationResponse<T> create(Page<?> page, List<T> content) {
        return PaginationResponse.<T>builder()
                .totalPageCount(page.getNumberOfElements())
                .totalPageCount(page.getTotalPages())
                .nowPage(page.getNumber())
                .hasPrevious(page.hasPrevious())
                .hasNext(page.hasNext())
                .content(content)
                .build();
    }
}

