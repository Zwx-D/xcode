package com.xcode.app.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "system")
public class SystemConfig {
    private JwtConfig jwt;
    private String filesBaseUrl;
    private UserInitializationConfig userInitialization;
}
