package com.xcode.app.service;

import com.xcode.app.web.rest.vm.CarouselImageVM;

import java.util.List;

public interface CarouselImageService {

    List<CarouselImageVM> save(List<CarouselImageVM> vm);

    List<CarouselImageVM> findAll();

}
