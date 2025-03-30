package com.xcode.app.service;

import com.xcode.app.web.rest.filter.CarouselImageCriteria;
import com.xcode.app.web.rest.vm.CarouselImageVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarouselImageQueryService {

    Page<CarouselImageVM> findByCriteria(CarouselImageCriteria criteria, Pageable pageable);

}
