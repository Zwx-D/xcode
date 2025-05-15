package com.xcode.app.web.rest;

import com.xcode.app.service.PortfolioItemQueryService;
import com.xcode.app.service.PortfolioQueryService;
import com.xcode.app.service.PortfolioService;
import com.xcode.app.util.HeaderUtils;
import com.xcode.app.web.rest.api.PortfolioApi;
import com.xcode.app.web.rest.dto.CreatePortfolioItemDTO;
import com.xcode.app.web.rest.filter.PortfolioCriteria;
import com.xcode.app.web.rest.filter.PortfolioItemCriteria;
import com.xcode.app.web.rest.vm.PortfolioItemVM;
import com.xcode.app.web.rest.vm.PortfolioVM;
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
public class PortfolioResource implements PortfolioApi {

    @Autowired
    private PortfolioService service;

    @Autowired
    private PortfolioQueryService queryService;

    @Autowired
    private PortfolioItemQueryService portfolioItemQueryService;

    @Override
    public ResponseEntity<List<PortfolioVM>> findByCriteria(PortfolioCriteria criteria, Pageable pageable) {
        Page<PortfolioVM> byCriteria = queryService.findByCriteria(criteria, pageable);
        return ResponseEntity.ok()
            .headers(HeaderUtils.createHeadersWithTotalCount(byCriteria))
            .body(byCriteria.getContent());
    }

    @Override
    public ResponseEntity<List<PortfolioItemVM>> findPortfolioItemByCriteria(PortfolioItemCriteria criteria, Pageable pageable) {
        Page<PortfolioItemVM> byCriteria = portfolioItemQueryService.findByCriteria(criteria, pageable);
        return ResponseEntity.ok()
            .headers(HeaderUtils.createHeadersWithTotalCount(byCriteria))
            .body(byCriteria.getContent());
    }

    @Override
    public ResponseEntity<PortfolioVM> save(PortfolioVM vm) {
        return ResponseEntity.ok(service.save(vm));
    }

    @Override
    public ResponseEntity<PortfolioVM> update(PortfolioVM vm) {
        return ResponseEntity.ok(service.update(vm));
    }

    @Override
    public ResponseEntity<Void> changIsShow(String uuid, Boolean isShow) {
        service.changIsShow(uuid,isShow);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> delete(String uuid) {
        service.delete(uuid);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<PortfolioVM> findOneByUuid(String uuid) {
        return ResponseEntity.ok(service.findOneByUuid(uuid));
    }

    @Override
    public ResponseEntity<Void> createPortfolioItem(CreatePortfolioItemDTO dto) {
        service.createPortfolioItem(dto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> changItemIsShow(String uuid, Boolean isShow) {
        service.changItemIsShow(uuid,isShow);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deletePortfolioItem(String uuid) {
        service.deletePortfolioItem(uuid);
        return ResponseEntity.ok().build();
    }
}
