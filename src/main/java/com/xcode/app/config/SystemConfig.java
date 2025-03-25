package com.xcode.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "system")
public class SystemConfig {
    private JwtConfig jwt;
    private String filesBaseUrl;
    private UserInitializationConfig userInitialization;

}
