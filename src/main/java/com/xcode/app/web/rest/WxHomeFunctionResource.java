package com.xcode.app.web.rest;

import com.xcode.app.service.WxHomeFunctionQueryService;
import com.xcode.app.service.WxHomeFunctionService;
import com.xcode.app.util.HeaderUtils;
import com.xcode.app.web.rest.api.WxHomeFunctionApi;
import com.xcode.app.web.rest.filter.WxHomeFunctionCriteria;
import com.xcode.app.web.rest.vm.WxHomeFunctionVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WxHomeFunctionResource implements WxHomeFunctionApi {

    @Autowired
    private WxHomeFunctionService service;

    @Autowired
    private WxHomeFunctionQueryService queryService;

    @Override
    public ResponseEntity<List<WxHomeFunctionVM>> findByCriteria(WxHomeFunctionCriteria criteria, Pageable pageable) {
        Page<WxHomeFunctionVM> byCriteria = queryService.findByCriteria(criteria, pageable);
        return ResponseEntity.ok()
            .headers(HeaderUtils.createHeadersWithTotalCount(byCriteria))
            .body(byCriteria.getContent());
    }

    @Override
    public ResponseEntity<WxHomeFunctionVM> save(WxHomeFunctionVM vm) {
        return ResponseEntity.ok(service.save(vm));
    }

    @Override
    public ResponseEntity<Void> delete(String uuid) {
        service.delete(uuid);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateIsShow(String uuid, Boolean isShow) {
        service.updateIsShow(uuid, isShow);
        return ResponseEntity.ok().build();
    }
}
