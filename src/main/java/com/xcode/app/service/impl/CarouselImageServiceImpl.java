package com.xcode.app.service.impl;

import com.xcode.app.domain.CarouselImage;
import com.xcode.app.repository.CarouselImageRepository;
import com.xcode.app.service.CarouselImageService;
import com.xcode.app.service.mapper.CarouselImageMapper;
import com.xcode.app.web.rest.vm.CarouselImageVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CarouselImageServiceImpl implements CarouselImageService {

    @Autowired
    private CarouselImageRepository repository;

    @Autowired
    private CarouselImageMapper mapper;


    @Override
    public CarouselImageVM save(CarouselImageVM vm) {
        CarouselImage carouselImages = mapper.toEntity(vm);
        CarouselImage save = repository.save(carouselImages);
        return mapper.toVM(save);
    }

    @Override
    public List<CarouselImageVM> findAll() {
        List<CarouselImage> all = repository.findAll();
        return mapper.toVMList(all);
    }

}
