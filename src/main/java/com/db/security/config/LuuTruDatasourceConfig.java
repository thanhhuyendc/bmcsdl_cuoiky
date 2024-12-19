package com.db.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.datasource.luutru")
@Getter
@Setter
public class LuuTruDatasourceConfig {
    private String url;
    private String username;
    private String password;
}
