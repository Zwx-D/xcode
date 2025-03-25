package com.xcode.app.web.rest;

import com.xcode.app.service.CarouselImageService;
import com.xcode.app.web.rest.api.CarouselImageApi;
import com.xcode.app.web.rest.vm.CarouselImageVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public ResponseEntity<List<CarouselImageVM>> save(List<CarouselImageVM> vm) {
        return ResponseEntity.ok(service.save(vm));
    }

    @Override
    public ResponseEntity<List<CarouselImageVM>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

}
