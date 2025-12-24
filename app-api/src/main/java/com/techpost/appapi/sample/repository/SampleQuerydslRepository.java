package com.techpost.appapi.sample.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.techpost.appapi.domain.sample.entity.QSample;
import com.techpost.appapi.sample.entity.Sample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class SampleQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Sample findBySampleSeqByQuerydsl(Long sampleSeq) {
        return jpaQueryFactory.selectFrom(QSample.sample)
                .where(QSample.sample.sampleSeq.eq(sampleSeq))
                .fetchFirst();
    }
}
