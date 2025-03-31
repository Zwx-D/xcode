package com.xcode.app.config.wechat;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@EnableConfigurationProperties(WxMaMiniProperties.class)
public class WxMaMiniConfiguration {

    private final WxMaMiniProperties properties;

    @Autowired
    public WxMaMiniConfiguration(WxMaMiniProperties properties) {
        this.properties = properties;
    }

    @Bean
    public WxMaService wxMaService() {
        List<MiniConfig> miniConfigs = this.properties.getMiniConfig();
        if (miniConfigs == null) {
            throw new WxRuntimeException("没有可用配置！");
        }
        log.info("======>>>加载配置：{}", miniConfigs);
        WxMaService maService = new WxMaServiceImpl();
        maService.setMultiConfigs(
            miniConfigs.stream()
                .map(a -> {
                    WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
                    config.setApiHostUrl(a.getApiHost());
                    config.setAppid(a.getAppid());
                    config.setSecret(a.getSecreat());
                    //config.setToken(a.getToken());
                    //config.setAesKey(a.getAesKey());
                    //config.setMsgDataFormat(a.getMsgDataFormat());
                    log.info("======>>>config:{}", config);
                    return config;
                }).collect(Collectors.toMap(WxMaDefaultConfigImpl::getAppid, a -> a, (o, n) -> o)));
        return maService;
    }

}
