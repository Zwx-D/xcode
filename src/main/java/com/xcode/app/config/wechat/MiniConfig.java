package com.xcode.app.config.wechat;

import lombok.Data;

@Data
public class MiniConfig {

    private String apiHost;

    private String appid; // 微信小程序的appid

    private String secreat; // 微信小程序的Secret

    //private String token; // 微信小程序消息服务器配置的token

    //private String aesKey; // 微信小程序消息服务器配置的EncodingAESKey

    //private String msgDataFormat; // 消息格式，XML或者JSON

}
