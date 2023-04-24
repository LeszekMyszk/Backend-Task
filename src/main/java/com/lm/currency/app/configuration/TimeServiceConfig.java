package com.lm.currency.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
class TimeServiceConfig {
    @Bean
    TimeService createTimeService() {
        return () -> LocalDateTime.now();
    }
}