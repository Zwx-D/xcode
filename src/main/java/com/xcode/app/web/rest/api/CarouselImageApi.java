package com.xcode.app.web.rest.api;

import com.xcode.app.web.rest.filter.CarouselImageCriteria;
import com.xcode.app.web.rest.vm.CarouselImageVM;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CarouselImageApi {

    @PostMapping("/carouselImage")
    ResponseEntity<CarouselImageVM> save(@RequestBody CarouselImageVM vm);

    @GetMapping("/carouselImage")
    ResponseEntity<List<CarouselImageVM>> findByCriteria(@SpringQueryMap CarouselImageCriteria criteria,
                                                         @PageableDefault(page = 0, size = 10) Pageable pageable);

}
