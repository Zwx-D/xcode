package com.xcode.app.web.rest.api;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.xcode.app.web.rest.dto.CreateCollectionFolderDTO;
import com.xcode.app.web.rest.dto.UpdateCollectionItemDTO;
import com.xcode.app.web.rest.filter.PortfolioCriteria;
import com.xcode.app.web.rest.filter.PortfolioItemCriteria;
import com.xcode.app.web.rest.vm.*;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @PostMapping("/weChat/public/wechatUser")
    ResponseEntity<WechatUserVM> save(@RequestBody WechatUserVM vm);

    @GetMapping("/wechatUser/{unionId}")
    ResponseEntity<WechatUserVM> findOneByUnionId(@PathVariable String unionId);

    @GetMapping("/weChat/public/wechatUser/collection/{wechatUserId}")
    ResponseEntity<List<CollectionFolderVM>> findCollectionByWechatUserId(@PathVariable Long wechatUserId);

    @PostMapping("/weChat/public/wechatUser/collection")
    ResponseEntity<CollectionFolderVM> createCollection(@RequestBody CreateCollectionFolderDTO dto);

    @PutMapping("/weChat/public/wechatUser/collection")
    ResponseEntity<CollectionFolderVM> updateCollection(@RequestBody CollectionFolderVM vm);

    @DeleteMapping("/weChat/public/wechatUser/collection/{uuid}")
    ResponseEntity<Void> deleteCollection(@PathVariable String uuid);

    @PostMapping("/weChat/public/wechatUser/collectionItem")
    ResponseEntity<Void> addItemInFolder(@RequestBody UpdateCollectionItemDTO dto);

    @DeleteMapping("/weChat/public/wechatUser/collectionItem")
    ResponseEntity<Void> delItemInFolder(@RequestBody UpdateCollectionItemDTO dto);

    @GetMapping("/weChat/public/photographer")
    ResponseEntity<PhotographerVM> getPhotographer();

    @GetMapping("/weChat/public/portfolio")
    ResponseEntity<List<PortfolioVM>> findByCriteria(@RequestParam(required = false) String name,
                                                     @RequestParam(required = false) Integer page,
                                                     @RequestParam(required = false) Integer size,
                                                     @RequestParam(required = false) String sortName,
                                                     @RequestParam(required = false) String sort);

    @GetMapping("/weChat/public/portfolioItem")
    ResponseEntity<List<PortfolioItemVM>> findPortfolioItemByCriteria(@RequestParam String uuid,
                                                                      @RequestParam(required = false) Integer page,
                                                                      @RequestParam(required = false) Integer size,
                                                                      @RequestParam(required = false) String sortName,
                                                                      @RequestParam(required = false) String sort);

}
