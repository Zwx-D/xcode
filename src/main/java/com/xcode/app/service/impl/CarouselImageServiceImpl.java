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

    @Value("${xcode.filesBaseUrl:test}")
    private String filesBaseUrl;

    @Override
    public List<CarouselImageVM> save(List<CarouselImageVM> vm) {
        repository.deleteAll();
        List<CarouselImage> carouselImages = mapper.toEntityList(vm);
        List<CarouselImage> all = repository.saveAll(carouselImages);
        return generateLinkUrl(all);
    }

    @Override
    public List<CarouselImageVM> findAll() {
        List<CarouselImage> all = repository.findAll();
        return generateLinkUrl(all);
    }

    private List<CarouselImageVM> generateLinkUrl(List<CarouselImage> list) {
        return list.stream().map(item -> {
            CarouselImageVM carouselImageVM = mapper.toVM(item);
            carouselImageVM.setLinkUrl(filesBaseUrl + "/" + item.getImageUuid());
            return carouselImageVM;
        }).collect(Collectors.toList());
    }
}
