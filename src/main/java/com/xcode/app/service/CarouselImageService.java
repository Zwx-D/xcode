package com.xcode.app.service;

import com.xcode.app.web.rest.vm.CarouselImageVM;

import java.util.List;

public interface CarouselImageService {

    CarouselImageVM save(CarouselImageVM vm);

    List<CarouselImageVM> findAllByIsShow(Boolean isShow);

    void delete(String uuid);

    void updateIsShow(String uuid, Boolean isShow);
}
