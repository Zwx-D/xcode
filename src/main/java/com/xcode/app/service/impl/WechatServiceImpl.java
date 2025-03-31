package com.xcode.app.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.common.collect.ImmutableMap;
import com.google.common.reflect.TypeToken;
import com.xcode.app.service.WechatService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WechatServiceImpl implements WechatService {

    @Autowired
    private WxMaService wxMaService;

    @Override
    public WxMaJscode2SessionResult code2Session(String appId, String appletCode) {
        if (!wxMaService.switchover(appId)) {
            throw new IllegalArgumentException("无效的APPID，服务器未知！");
        }
        WxMaJscode2SessionResult session = null;
        try {
            session = wxMaService.switchoverTo(appId).getUserService().getSessionInfo(appletCode);
        } catch (WxErrorException e) {
            throw new RuntimeException("服务器异常！" + e.getMessage());
        }
        return session;
    }

    @Override
    public WxMaPhoneNumberInfo getuserphoneummber(String appId, String code) throws WxErrorException {
        if (!wxMaService.switchover(appId)) {
            throw new IllegalArgumentException("无效的APPID，服务器未知！");
        }
        String responseText = wxMaService.switchoverTo(appId).post("https://api.weixin.qq.com/wxa/business/getuserphonenumber", ImmutableMap.of("code", code));
        log.info("======>>>responseText:{}", responseText);
        WxMaPhoneNumberInfo wxMaPhoneNumberInfo = WxMaGsonBuilder.create().fromJson(GsonParser.parse(responseText).getAsJsonObject("phone_info"), new TypeToken<WxMaPhoneNumberInfo>() {
        }.getType());
        log.info("======>>>wxMaPhoneNumberInfo: {}", wxMaPhoneNumberInfo);
        return wxMaPhoneNumberInfo;
    }

}
