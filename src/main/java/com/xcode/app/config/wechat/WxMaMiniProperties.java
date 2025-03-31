package com.xcode.app.config.wechat;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "system.wechat")
public class WxMaMiniProperties {

    private List<MiniConfig> miniConfig;

}
