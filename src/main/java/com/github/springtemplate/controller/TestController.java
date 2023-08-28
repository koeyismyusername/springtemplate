package com.github.springtemplate.controller;

import com.github.springtemplate.dto.response.ApiResponse;
import com.github.springtemplate.dto.response.PageResponse;
import com.github.springtemplate.exception.ApiException;
import com.github.springtemplate.exception.errorcode.ApiErrorCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/test")
@Tag(name = "테스트용 API", description = "테스트를 위해 작성된 API입니다.")
public class TestController {
    @GetMapping("/")
    public ApiResponse<String> test(){
//        throw ApiErrorCode.INVALID_PATH_VARIABLE.exception();
        return ApiResponse.success("응답에 성공했습니다.", "응답 데이터");
    }

    @GetMapping("/page")
    public ApiResponse<PageResponse<String>> testPage() {
        return ApiResponse.success("페이지네이션 조회에 성공했습니다.", PageResponse.<String>builder()
                .totalElements(0L)
                .totalPages(0)
                .hasNext(false)
                .hasPrevious(false)
                .contents(List.of("hello", "world"))
                .build()
        );
    }
}
