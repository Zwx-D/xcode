package com.xcode.app.service;

import com.xcode.app.web.rest.vm.WechatUserVM;

public interface WechatUserService {

    WechatUserVM save(WechatUserVM vm);

    WechatUserVM findOneByUnionId(String unionId);

}
