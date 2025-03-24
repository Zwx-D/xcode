package com.xcode.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "deepseek")
public class DeepSeekProperties {

    private String baseUrl;

    private String appKey;

    private String model;

    private String chatUrl;

}
