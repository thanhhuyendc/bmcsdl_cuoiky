package com.db.security.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="spring.datasource.primary")
@Getter
@Setter
public class PrimaryDatasourceConfig {
    private String url;
    private String username;
    private String password;
}
