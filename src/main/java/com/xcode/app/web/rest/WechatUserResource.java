package com.xcode.app.web.rest;

import com.xcode.app.service.WechatUserQueryService;
import com.xcode.app.util.HeaderUtils;
import com.xcode.app.web.rest.api.WechatUserApi;
import com.xcode.app.web.rest.filter.WechatUserCriteria;
import com.xcode.app.web.rest.vm.WechatUserVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api")
public class WechatUserResource implements WechatUserApi {

    @Autowired
    private WechatUserQueryService queryService;

    @Override
    public ResponseEntity<List<WechatUserVM>> findByCriteria(WechatUserCriteria criteria, Pageable pageable) {
        Page<WechatUserVM> byCriteria = queryService.findByCriteria(criteria, pageable);
        return ResponseEntity.ok()
            .headers(HeaderUtils.createHeadersWithTotalCount(byCriteria))
            .body(byCriteria.getContent());
    }

}
