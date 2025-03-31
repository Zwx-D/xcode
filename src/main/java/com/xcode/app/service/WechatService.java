package com.xcode.app.service;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import me.chanjar.weixin.common.error.WxErrorException;

public interface WechatService {

    WxMaJscode2SessionResult code2Session(String appId, String appletCode);

    WxMaPhoneNumberInfo getuserphoneummber(String appId, String code) throws WxErrorException;

}
