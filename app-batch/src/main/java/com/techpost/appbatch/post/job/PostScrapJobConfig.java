package com.techpost.appbatch.post.job;

import com.techpost.appbatch.common.constant.JobConstants;
import com.techpost.appbatch.post.enums.PublisherScrapEnum;
import com.techpost.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class PostScrapJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    private final PostScrapReader reader;
    private final PostScrapProcessor processor;
    private final PostScrapWriter writer;

    @Bean(name = JobConstants.POST_SCRAP_JOB)
    public Job job(Step postScrapJobStep1) {
        return new JobBuilder(JobConstants.POST_SCRAP_JOB, jobRepository)
                .start(postScrapJobStep1)
                .build();
    }

    @Bean
    public Step postScrapJobStep1() {
        return new StepBuilder("postScrapJobStep1", jobRepository)
                .<PublisherScrapEnum, List<Post>>chunk(1, platformTransactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
