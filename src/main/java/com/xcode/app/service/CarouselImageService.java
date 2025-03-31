package com.xcode.app.service;

import com.xcode.app.domain.CarouselImage;
import com.xcode.app.web.rest.vm.CarouselImageVM;

import java.util.List;

public interface CarouselImageService {

    CarouselImageVM save(CarouselImageVM vm);

    List<CarouselImageVM> findAll();

    void delete(String uuid);

    void updateIsShow(String uuid, Boolean isShow);
}
