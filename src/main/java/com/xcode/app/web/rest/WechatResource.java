package com.xcode.app.web.rest;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.xcode.app.security.jwt.TokenProvider;
import com.xcode.app.service.*;
import com.xcode.app.util.HeaderUtils;
import com.xcode.app.web.rest.api.WechatApi;
import com.xcode.app.web.rest.dto.CreateCollectionFolderDTO;
import com.xcode.app.web.rest.dto.UpdateCollectionItemDTO;
import com.xcode.app.web.rest.filter.PortfolioCriteria;
import com.xcode.app.web.rest.filter.PortfolioItemCriteria;
import com.xcode.app.web.rest.vm.*;
import io.github.jhipster.service.filter.StringFilter;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class WechatResource implements WechatApi {

    @Autowired
    private WechatService wechatService;

    @Autowired
    private CarouselImageService carouselImageService;

    @Autowired
    private PhotographyService photographyService;

    @Autowired
    private WxHomeFunctionService wxHomeFunctionService;

    @Autowired
    private WechatUserService wechatUserService;

    @Autowired
    private PhotographerService photographerService;

    @Autowired
    private PortfolioQueryService portfolioQueryService;

    @Autowired
    private PortfolioItemQueryService portfolioItemQueryService;

    @Autowired
    private CollectionFolderService collectionFolderService;

    @Override
    public ResponseEntity<WxMaJscode2SessionResult> code2Session(String appId, String code) {
        return ResponseEntity.ok(wechatService.code2Session(appId, code));
    }

    @Override
    public ResponseEntity<WxMaPhoneNumberInfo> getUserPhoneNumber(String appId, String code) throws WxErrorException {
        return ResponseEntity.ok(wechatService.getuserphoneummber(appId, code));
    }

    @Override
    public ResponseEntity<List<CarouselImageVM>> findAllCarouselImages() {
        return ResponseEntity.ok(carouselImageService.findAllByIsShow(true));
    }

    @Override
    public ResponseEntity<List<PhotographyVM>> findAllPhotography() {
        return ResponseEntity.ok(photographyService.findAllByIsShow(true));
    }

    @Override
    public ResponseEntity<List<WxHomeFunctionVM>> findAllWxHomeFunction() {
        return ResponseEntity.ok(wxHomeFunctionService.findAllByIsShow(true));
    }

    @Override
    public ResponseEntity<WechatUserVM> save(WechatUserVM vm) {
        return ResponseEntity.ok(wechatUserService.save(vm));
    }

    @Override
    public ResponseEntity<WechatUserVM> findOneByUnionId(String unionId) {
        return ResponseEntity.ok(wechatUserService.findOneByUnionId(unionId));
    }

    @Override
    public ResponseEntity<CollectionFolderVM> createCollection(CreateCollectionFolderDTO dto) {
        return ResponseEntity.ok(collectionFolderService.save(dto));
    }

    @Override
    public ResponseEntity<CollectionFolderVM> updateCollection(CollectionFolderVM vm) {
        return ResponseEntity.ok(collectionFolderService.update(vm));
    }

    @Override
    public ResponseEntity<Void> deleteCollection(String uuid) {
        collectionFolderService.delete(uuid);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> addItemInFolder(UpdateCollectionItemDTO dto) {
        collectionFolderService.addItemInFolder(dto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> delItemInFolder(UpdateCollectionItemDTO dto) {
        collectionFolderService.delItemInFolder(dto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CollectionFolderVM>> findCollectionByWechatUserId(Long wechatUserId) {
        return ResponseEntity.ok(collectionFolderService.findByWechatUserId(wechatUserId));
    }

    @Override
    public ResponseEntity<CollectionFolderVM> findCollectionByUuid(String uuid) {
        return ResponseEntity.ok(collectionFolderService.findOneByUuid(uuid));
    }

    @Override
    public ResponseEntity<PhotographerVM> getPhotographer() {
        return ResponseEntity.ok(photographerService.getPhotographer());
    }

    @Override
    public ResponseEntity<List<PortfolioVM>> findByCriteria(String name, Integer page, Integer size, String sortName, String sort) {
        PortfolioCriteria criteria = new PortfolioCriteria();
        if (Optional.ofNullable(name).isPresent()) {
            StringFilter stringFilter = new StringFilter();
            stringFilter.setContains(name);
            criteria.setName(stringFilter);
        }
        Page<PortfolioVM> byCriteria = portfolioQueryService.findByCriteria(criteria, generatePageable(page, size, sortName, sort));
        return ResponseEntity.ok()
            .headers(HeaderUtils.createHeadersWithTotalCount(byCriteria))
            .body(byCriteria.getContent());
    }

    @Override
    public ResponseEntity<List<PortfolioItemVM>> findPortfolioItemByCriteria(String uuid, Integer page, Integer size, String sortName, String sort) {
        PortfolioItemCriteria criteria = new PortfolioItemCriteria();
        if (Optional.ofNullable(uuid).isPresent()) {
            StringFilter stringFilter = new StringFilter();
            stringFilter.setContains(uuid);
            criteria.setPortfolioUuid(stringFilter);
        }
        Page<PortfolioItemVM> byCriteria = portfolioItemQueryService.findByCriteria(criteria, generatePageable(page, size, sortName, sort));
        return ResponseEntity.ok()
            .headers(HeaderUtils.createHeadersWithTotalCount(byCriteria))
            .body(byCriteria.getContent());
    }

    private Pageable generatePageable(Integer page, Integer size, String sortName, String sort) {
        if (page == null || page < 0) {
            page = 0;
        }
        if (size == null || size <= 0) {
            size = 10;
        }
        Sort sortObj;
        if (sortName != null && !sortName.isEmpty()) {
            Sort.Direction direction = (sort != null && sort.equalsIgnoreCase(sort))
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
            sortObj = Sort.by(direction, sortName);
        } else {
            sortObj = Sort.unsorted();
        }
        Pageable pageable = PageRequest.of(page, size, sortObj);
        return pageable;
    }
}
