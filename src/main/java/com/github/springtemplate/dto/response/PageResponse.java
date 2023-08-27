package com.github.springtemplate.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(title = "페이지네이션 조회 결과")
public record PageResponse<T>(
        @Schema(title = "다음 페이지의 존재 유무")
        boolean hasNext,
        @Schema(title = "이전 페이지의 존재 유무")
        boolean hasPrevious,
        @Schema(title = "전체 페이지 개수")
        int totalPages,
        @Schema(title = "전체 데이터 개수")
        long totalElements,
        @Schema(title = "조회된 데이터 리스트")
        List<T> contents
) {
}
