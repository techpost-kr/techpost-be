package com.techpost.appbatch.post.scrap.step;

import com.techpost.appbatch.post.scrap.enums.PublisherScrapEnum;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.listener.StepExecutionListener;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.Queue;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostScrapReader implements ItemReader<PublisherScrapEnum>, StepExecutionListener {

    private Queue<PublisherScrapEnum> publisherQueue;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        publisherQueue = new LinkedList<>(EnumSet.allOf(PublisherScrapEnum.class));
    }

    @Override
    public PublisherScrapEnum read() {

        log.info("start scrap reader");

        return publisherQueue.poll();
    }
}
