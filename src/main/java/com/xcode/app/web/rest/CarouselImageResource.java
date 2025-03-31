package com.xcode.app.web.rest;

import com.xcode.app.service.CarouselImageQueryService;
import com.xcode.app.service.CarouselImageService;
import com.xcode.app.util.HeaderUtils;
import com.xcode.app.web.rest.api.CarouselImageApi;
import com.xcode.app.web.rest.filter.CarouselImageCriteria;
import com.xcode.app.web.rest.vm.CarouselImageVM;
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
public class CarouselImageResource implements CarouselImageApi {

    @Autowired
    private CarouselImageService service;

    @Autowired
    private CarouselImageQueryService queryService;

    @Override
    public ResponseEntity<CarouselImageVM> save(CarouselImageVM vm) {
        return ResponseEntity.ok(service.save(vm));
    }

    @Override
    public ResponseEntity<List<CarouselImageVM>> findByCriteria(CarouselImageCriteria criteria,
                                                                Pageable pageable) {
        Page<CarouselImageVM> byCriteria = queryService.findByCriteria(criteria, pageable);
        return ResponseEntity.ok()
            .headers(HeaderUtils.createHeadersWithTotalCount(byCriteria))
            .body(byCriteria.getContent());
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
