package com.techpost.appbatch.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@Slf4j
@RequiredArgsConstructor
public class PostScrapScheduler {

    private final JobLauncher jobLauncher;

    private final Job postScrapJob;

    @Scheduled(cron = "${spring.scheduler.cron.tech-blog-scrap}")
    public void scrap() {
        try {
            log.info("Starting post scrap job...");

            // TODO: 동일파라미터에 대해 성공 이력이 있으면 배치는 실행되지 않는 이슈
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("timestamp", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();

            jobLauncher.run(postScrapJob, jobParameters);

            log.info("Post scrap job completed.");

        } catch (Exception e) {
            log.error("Error during post scrap job execution", e);
        }
    }
}
