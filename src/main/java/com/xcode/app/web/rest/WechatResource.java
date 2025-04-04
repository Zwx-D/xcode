package com.xcode.app.web.rest;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.xcode.app.service.WechatService;
import com.xcode.app.web.rest.api.WechatApi;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class WechatResource implements WechatApi {

    @Autowired
    private WechatService wechatService;

    @Override
    public ResponseEntity<WxMaJscode2SessionResult> code2Session(String appId, String code) {
        return ResponseEntity.ok(wechatService.code2Session(appId, code));
    }

    @Override
    public ResponseEntity<WxMaPhoneNumberInfo> getuserphoneummber(String appId, String code) throws WxErrorException {
        return ResponseEntity.ok(wechatService.getuserphoneummber(appId, code));
    }
}
