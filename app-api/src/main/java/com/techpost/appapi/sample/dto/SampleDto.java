package com.techpost.appapi.sample.dto;

import com.techpost.appapi.sample.entity.Sample;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SampleDto {
    private Long sampleSeq;
    private String content;

    public static SampleDto from(Sample sample) {
        return new SampleDto(sample.getSampleSeq(), sample.getContent());
    }
}
