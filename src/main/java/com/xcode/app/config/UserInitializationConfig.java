package com.xcode.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "system.user-initialization")
public class UserInitializationConfig {

    private Boolean enabled;
    private String username;
    private String password;
    private String roleName;
    private String roleDescription;

}
