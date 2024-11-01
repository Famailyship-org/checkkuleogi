package com.Familyship.checkkuleogi.batchFunc.deleteMBTIHard;

import com.Familyship.checkkuleogi.domains.child.domain.ChildMBTILog;
import com.Familyship.checkkuleogi.domains.child.domain.repository.ChildMBTILogRepository;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;
import java.util.Map;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class DeleteHardConfig {

    private static final Logger logger = LoggerFactory.getLogger(DeleteHardConfig.class);

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final EntityManagerFactory entityManagerFactory;
    private final ChildMBTILogRepository childMBTILogRepository;

    private LocalDateTime getThresholdTime() {
        return LocalDateTime.now().minusMonths(1);
    }

    @Bean
    public Job deleteOldRecordsJob() {
        logger.info("Starting deleteOldRecordsJob...");
        return new JobBuilder("deleteOldRecordsJob", jobRepository)
                .start(deleteOldRecordsStep())
                .build();
    }

    @Bean
    public Step deleteOldRecordsStep() {
        return new StepBuilder("deleteOldRecordsStep", jobRepository)
                .<ChildMBTILog, ChildMBTILog>chunk(100, transactionManager)
                .reader(deleteOldRecordsReader())
                .writer(deleteOldRecordsWriter())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<ChildMBTILog> deleteOldRecordsReader() {
        return new JpaPagingItemReaderBuilder<ChildMBTILog>()
                .name("deleteOldRecordsReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT c FROM ChildMBTILog c WHERE c.isDeleted = true AND c.isDeletedAt < :dateTime")
                .parameterValues(Map.of("dateTime", getThresholdTime()))
                .pageSize(100)
                .build();
    }

    public ItemWriter<ChildMBTILog> deleteOldRecordsWriter() {
        return items -> {
            childMBTILogRepository.deleteAll(items);
            logger.info("Deleted {} records", items.size());
        };
    }


}


