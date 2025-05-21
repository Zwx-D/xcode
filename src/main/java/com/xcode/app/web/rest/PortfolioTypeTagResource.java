package com.xcode.app.web.rest;

import com.xcode.app.service.PortfolioTypeTagQueryService;
import com.xcode.app.service.PortfolioTypeTagService;
import com.xcode.app.util.HeaderUtils;
import com.xcode.app.web.rest.api.PortfolioTypeTagApi;
import com.xcode.app.web.rest.filter.PortfolioTypeTagCriteria;
import com.xcode.app.web.rest.vm.PortfolioTypeTagVM;
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
public class PortfolioTypeTagResource implements PortfolioTypeTagApi {

    @Autowired
    private PortfolioTypeTagQueryService queryService;

    @Autowired
    private PortfolioTypeTagService service;

    @Override
    public ResponseEntity<List<PortfolioTypeTagVM>> findByCriteria(PortfolioTypeTagCriteria criteria, Pageable pageable) {
        Page<PortfolioTypeTagVM> byCriteria = queryService.findByCriteria(criteria, pageable);
        return ResponseEntity.ok()
            .headers(HeaderUtils.createHeadersWithTotalCount(byCriteria))
            .body(byCriteria.getContent());
    }

    @Override
    public ResponseEntity<PortfolioTypeTagVM> save(PortfolioTypeTagVM vm) {
        return ResponseEntity.ok(service.save(vm));
    }

    @Override
    public ResponseEntity<PortfolioTypeTagVM> update(PortfolioTypeTagVM vm) {
        return ResponseEntity.ok(service.update(vm));
    }

    @Override
    public ResponseEntity<Void> delete(String uuid) {
        service.delete(uuid);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<PortfolioTypeTagVM>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

}
