package com.techpost.appapi.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaConfig {
    @Bean
    public AuditorAware<Long> auditorProvider() {
        // TODO: SecurityContext 에서 실제 사용자 ID 꺼내 도록 교체
        return () -> Optional.of(1L);
    }
}
