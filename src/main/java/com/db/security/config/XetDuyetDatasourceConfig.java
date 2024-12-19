package com.db.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.datasource.xetduyet")
@Getter
@Setter
public class XetDuyetDatasourceConfig {
    private String url;
    private String username;
    private String password;
}
