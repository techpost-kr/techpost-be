package com.tp.appapi.domain.sample.dto;

import com.tp.appapi.domain.sample.entity.Sample;
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
