package com.xcode.app.service;

import com.xcode.app.web.rest.dto.deepseek.req.ChatReqDTO;
import com.xcode.app.web.rest.dto.deepseek.resp.ChatRespDTO;

public interface Bridge4DeepSeekService {

    ChatRespDTO chat(ChatReqDTO req);

}
