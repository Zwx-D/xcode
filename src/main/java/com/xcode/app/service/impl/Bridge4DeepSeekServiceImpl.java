package com.xcode.app.service.impl;

import com.xcode.app.config.DeepSeekProperties;
import com.xcode.app.service.Bridge4DeepSeekService;
import com.xcode.app.util.JsonUtil;
import com.xcode.app.util.WebClientUtil;
import com.xcode.app.web.rest.dto.deepseek.req.ChatReqDTO;
import com.xcode.app.web.rest.dto.deepseek.resp.ChatRespDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class Bridge4DeepSeekServiceImpl implements Bridge4DeepSeekService {

    @Autowired
    private WebClientUtil webClientUtil;

    @Autowired
    private DeepSeekProperties deepSeekProperties;

    @Override
    public ChatRespDTO chat(ChatReqDTO req) {
        webClientUtil.post(deepSeekProperties.getBaseUrl() + deepSeekProperties.getChatUrl(),
            generateHeaders(null),
            JsonUtil.toJsonString(req),
            ChatRespDTO.class).subscribe(resp -> {
        }, error -> {
                throw new RuntimeException("请求DeepSeek异常");
        });
        return null;
    }


    private Map<String, String> generateHeaders(Map<String, String> map) {
        Map<String, String> headers;
        if (Optional.ofNullable(map).isEmpty()) {
            headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            headers.put("Accept", "application/json");
            headers.put("Authorization", deepSeekProperties.getAppKey());
        } else {
            headers = map;
        }

        return headers;
    }
}
