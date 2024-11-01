package com.Familyship.checkkuleogi.batchFunc.JobScheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class CheckActiveUserScheduler {

    private final JobLauncher jobLauncher;
    private final Job updateUserActivityStatusJob;

    @Scheduled(cron = "0 */2 * * * *")  // 매주 일요일 자정에 실행
    public void scheduleUpdateUserActivityStatusJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(updateUserActivityStatusJob, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
