package com.xcode.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "system.jwt")
public class JwtConfig {

    private String secret;
    private Long expirationMs;

}
