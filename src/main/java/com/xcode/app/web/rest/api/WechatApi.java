package com.xcode.app.web.rest.api;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface WechatApi {

    @GetMapping("/wechat/public/code2Session/{appId}/{code}")
    ResponseEntity<WxMaJscode2SessionResult> code2Session(@PathVariable String appId, String code);

    @GetMapping("/wechat/public/getuserphoneummber/{appId}/{code}")
    ResponseEntity<WxMaPhoneNumberInfo> getuserphoneummber(String appId, String code) throws WxErrorException;

}
