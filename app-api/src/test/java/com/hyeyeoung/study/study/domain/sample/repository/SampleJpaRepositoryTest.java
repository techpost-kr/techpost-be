package com.hyeyeoung.study.study.domain.sample.repository;

import com.hyeyeoung.study.appapi.domain.sample.entity.Sample;
import com.hyeyeoung.study.appapi.domain.sample.repository.SampleJpaRepository;
import com.hyeyeoung.study.study.domain.MockTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

public class SampleJpaRepositoryTest extends MockTest {

    @Mock
    private SampleJpaRepository sampleJpaRepository;

    private final Long sampleSeq = 1L;

    private final Sample mockSample = Sample.builder()
            .sampleSeq(sampleSeq)
            .content("테스트 샘플")
            .build();

    @Test
    @DisplayName("Query method 조회 - mock 테스트")
    void testCase1() {
        // given
        given(sampleJpaRepository.findBySampleSeq(sampleSeq)).willReturn(mockSample);

        // when
        Sample result = sampleJpaRepository.findBySampleSeq(sampleSeq);

        // then
        assertNotNull(result);
        assertEquals(sampleSeq, result.getSampleSeq());
    }

    @Test
    @DisplayName("JPQL 조회 - mock 테스트")
    void testCase2() {
        // given
        given(sampleJpaRepository.findBySampleSeqByJPQL(sampleSeq)).willReturn(mockSample);

        // when
        Sample result = sampleJpaRepository.findBySampleSeqByJPQL(sampleSeq);

        // then
        assertNotNull(result);
        assertEquals(sampleSeq, result.getSampleSeq());
    }

    @Test
    @DisplayName("Native 조회 - mock 테스트")
    void testCase3() {
        // given
        given(sampleJpaRepository.findBySampleSeqByNative(sampleSeq)).willReturn(mockSample);

        // when
        Sample result = sampleJpaRepository.findBySampleSeqByNative(sampleSeq);

        // then
        assertNotNull(result);
        assertEquals(sampleSeq, result.getSampleSeq());
    }
}
