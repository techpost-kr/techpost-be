package com.techpost.appapi.sample.controller;

import com.techpost.appapi.sample.dto.SampleDto;
import com.techpost.appapi.sample.dto.response.SampleResponse;
import com.techpost.appapi.sample.service.SampleService;
import com.techpost.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sample")
public class SampleController {

    private final SampleService sampleService;

    @GetMapping(value = "/{sampleSeq}")
    public ApiResponse<SampleResponse> selectSample(@PathVariable Long sampleSeq) {
        SampleDto sampleDto = sampleService.select(sampleSeq);
        SampleResponse sampleResponse = SampleResponse.from(sampleDto);
        return ApiResponse.success(sampleResponse);
    }

}
