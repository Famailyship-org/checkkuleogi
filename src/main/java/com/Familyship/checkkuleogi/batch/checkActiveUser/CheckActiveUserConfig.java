package com.Familyship.checkkuleogi.batch.checkActiveUser;

import com.Familyship.checkkuleogi.book.implementation.BookCacheManager;
import com.Familyship.checkkuleogi.child.jpa.Child;
import com.Familyship.checkkuleogi.child.jpa.repository.ChildRepository;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class CheckActiveUserConfig {

    private static final Logger logger = LoggerFactory.getLogger(CheckActiveUserConfig.class);

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final EntityManagerFactory entityManagerFactory;
    private final ChildRepository childRepository;
    private final BookCacheManager bookCacheManager;

    @Bean
    public Job updateUserActivityStatusJob() {
        logger.info("Starting updateUserActivityStatusJob...");

        return new JobBuilder("updateUserActivityStatusJob", jobRepository)
                .incrementer(new RunIdIncrementer()) // 매번 새로운 Job 인스턴스를 위해 추가
                .start(updateUserActivityStatusStep())
                .build();
    }

    @Bean
    public Step updateUserActivityStatusStep() {
        return new StepBuilder("updateUserActivityStatusStep", jobRepository)
                .<Child, Child>chunk(100, transactionManager)
                .reader(inactiveUserReader()) // Reader에서 Redis 값을 동적으로 가져오도록 수정
                .writer(inactiveUserWriter())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<Child> inactiveUserReader() {
        // 매 실행 시 Redis에서 데이터를 동적으로 가져오기 위해 StepScope 내에서 호출
        Set<String> keys = bookCacheManager.getRecentlyViewedBookKeys();
        Set<Long> activeUserIds = keys.stream()
                .map(key -> Long.parseLong(key.split(":")[2]))
                .collect(Collectors.toSet());

        return new JpaPagingItemReaderBuilder<Child>()
                .name("inactiveUserReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT c FROM Child c WHERE c.idx NOT IN :activeUserIds")
                .parameterValues(Map.of("activeUserIds", activeUserIds))
                .pageSize(100)
                .build();
    }

    @Bean
    public ItemWriter<Child> inactiveUserWriter() {
        return items -> {
            // 활성 유저 상태 업데이트
            Set<String> keys = bookCacheManager.getRecentlyViewedBookKeys();
            Set<Long> activeUserIds = keys.stream()
                    .map(key -> Long.parseLong(key.split(":")[2]))
                    .collect(Collectors.toSet());

            childRepository.markUsersAsActive(activeUserIds);
            logger.info("Marked {} users as active", activeUserIds.size());

            // 비활성 유저 상태 업데이트
            childRepository.markUsersAsInactiveNotIn(activeUserIds);
            logger.info("Marked other users as inactive");
        };
    }
}
