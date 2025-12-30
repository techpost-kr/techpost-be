package com.techpost.appbatch.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@Slf4j
@RequiredArgsConstructor
@SuppressWarnings("deprecation")  // Spring Batch 6.0에서 JobLauncher는 여전히 정식 지원됨
public class PostScrapScheduler {

    private final JobOperator jobOperator;
    private final Job postScrapJob;

    @Scheduled(cron = "${scheduler.cron.post-scrap}")
    public void scrap() {
        try {
            log.info("Starting post scrap job...");

            // timestamp 파라미터로 매번 새로운 실행 보장
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp", System.currentTimeMillis())
                    .toJobParameters();

            JobExecution execution = jobOperator.start(postScrapJob, jobParameters);

            log.info("Post scrap job completed with status: {}", execution.getStatus());

        } catch (Exception e) {
            log.error("Error during post scrap job execution", e);
        }
    }
}


