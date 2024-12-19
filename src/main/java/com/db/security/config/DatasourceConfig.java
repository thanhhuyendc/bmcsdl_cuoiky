package com.db.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
@DependsOn("datasourceRoutingConfig")
public class DatasourceConfig {
    private final DatasourceRoutingConfig datasourceRoutingConfig;
    @Bean
    @Primary
    public DataSource dataSource() {
        return datasourceRoutingConfig;
    }
}
