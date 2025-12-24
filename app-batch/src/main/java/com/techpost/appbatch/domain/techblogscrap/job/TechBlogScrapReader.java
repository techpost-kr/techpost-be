package com.techpost.appbatch.domain.techblogscrap.job;

import com.techpost.appbatch.domain.techblogscrap.enums.PublisherScrapEnum;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.Queue;

@Component
@RequiredArgsConstructor
@Slf4j
public class TechBlogScrapReader implements ItemReader<PublisherScrapEnum>, StepExecutionListener {

    private Queue<PublisherScrapEnum> techBlogQueue;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        techBlogQueue = new LinkedList<>(EnumSet.allOf(PublisherScrapEnum.class));
    }

    @Override
    public PublisherScrapEnum read() {

        log.info("start scrap reader");

        return techBlogQueue.poll();
    }
}
