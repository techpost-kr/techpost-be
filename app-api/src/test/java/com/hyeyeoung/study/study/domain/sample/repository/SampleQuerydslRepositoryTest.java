package com.hyeyeoung.study.study.domain.sample.repository;

import com.hyeyeoung.study.appapi.domain.sample.entity.Sample;
import com.hyeyeoung.study.appapi.domain.sample.repository.SampleQuerydslRepository;
import com.hyeyeoung.study.study.domain.MockTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class SampleQuerydslRepositoryTest extends MockTest {

    @Mock
    private SampleQuerydslRepository sampleQuerydslRepository;

    private final Long sampleSeq = 1L;

    // 테스트용 더미 엔티티
    private final Sample mockSample = Sample.builder()
            .sampleSeq(sampleSeq)
            .content("테스트 샘플")
            .build();

    @Test
    @DisplayName("Querydsl 조회 테스트 - Mock")
    void testFindBySampleSeqByQuerydsl() {
        // given
        given(sampleQuerydslRepository.findBySampleSeqByQuerydsl(sampleSeq))
                .willReturn(mockSample);

        // when
        Sample result = sampleQuerydslRepository.findBySampleSeqByQuerydsl(sampleSeq);

        // then
        assertNotNull(result, "조회된 샘플이 null이 아니어야 한다");
        assertEquals(sampleSeq, result.getSampleSeq(), "샘플 시퀀스가 일치해야 한다");
    }
}
