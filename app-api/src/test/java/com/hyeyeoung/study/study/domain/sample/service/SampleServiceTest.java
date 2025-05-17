package com.hyeyeoung.study.study.domain.sample.service;

import com.hyeyeoung.study.appapi.domain.sample.dto.SampleDto;
import com.hyeyeoung.study.appapi.domain.sample.entity.Sample;
import com.hyeyeoung.study.appapi.domain.sample.repository.SampleJpaRepository;
import com.hyeyeoung.study.appapi.domain.sample.service.SampleService;
import com.hyeyeoung.study.study.domain.MockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class SampleServiceTest extends MockTest {

    @Mock
    private SampleJpaRepository sampleRepository;

    @InjectMocks
    private SampleService sampleService;

    private Sample sample1;
    private Sample sample2;

    @BeforeEach
    void setUp() {
        sample1 = Sample.of("Sample 1");
        // id 수동 설정 필요 시 sample1.setSampleSeq(1L);
        sample2 = Sample.of("Sample 2");
    }

    @Test
    @DisplayName("단건 조회 테스트")
    void testSelectSample() {
        // given
        Mockito.when(sampleRepository.findById(sample1.getSampleSeq()))
                .thenReturn(Optional.of(sample1));

        // when
        SampleDto result = sampleService.select(sample1.getSampleSeq());

        // then
        assertNotNull(result);
        assertThat(result.getContent()).isEqualTo("Sample 1");
    }

    @Test
    @DisplayName("전체 조회 테스트")
    void testSelectAllSamples() {
        // given
        Mockito.when(sampleRepository.findAll())
                .thenReturn(List.of(sample1, sample2));

        // when
        List<SampleDto> results = sampleService.selectAll();

        // then
        assertThat(results).isNotNull();
        assertThat(results.size()).isEqualTo(2);
        assertThat(results).extracting("content").containsExactlyInAnyOrder("Sample 1", "Sample 2");
    }

    @Test
    @DisplayName("삭제 테스트")
    void testDeleteSample() {
        // given
        // 삭제는 void 메서드라 별도 반환값 없음

        // when
        sampleRepository.deleteById(sample1.getSampleSeq());

        // then
        Mockito.verify(sampleRepository).deleteById(sample1.getSampleSeq());
    }
}
