package com.xcode.app.web.rest.api;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.xcode.app.web.rest.vm.CarouselImageVM;
import com.xcode.app.web.rest.vm.PhotographyVM;
import com.xcode.app.web.rest.vm.WechatUserVM;
import com.xcode.app.web.rest.vm.WxHomeFunctionVM;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface WechatApi {

    @GetMapping("/weChat/public/code2Session/{appId}/{code}")
    ResponseEntity<WxMaJscode2SessionResult> code2Session(@PathVariable("appId") String appId, @PathVariable("code") String code);

    @GetMapping("/weChat/public/getUserPhoneNumber/{appId}/{code}")
    ResponseEntity<WxMaPhoneNumberInfo> getUserPhoneNumber(@PathVariable("appId") String appId, @PathVariable("code") String code) throws WxErrorException;

    @GetMapping("/weChat/public/findAllCarouselImages")
    ResponseEntity<List<CarouselImageVM>> findAllCarouselImages();

    @GetMapping("/weChat/public/findAllPhotography")
    ResponseEntity<List<PhotographyVM>> findAllPhotography();

    @GetMapping("/weChat/public/findAllWxHomeFunction")
    ResponseEntity<List<WxHomeFunctionVM>> findAllWxHomeFunction();

    @PostMapping("/wechatUser")
    ResponseEntity<WechatUserVM> save(@RequestBody WechatUserVM vm);

    @GetMapping("/wechatUser/{unionId}")
    ResponseEntity<WechatUserVM> findOneByUnionId(@PathVariable String unionId);

}
