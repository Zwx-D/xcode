package com.xcode.app.web.rest.api;

import com.xcode.app.web.rest.vm.CarouselImageVM;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CarouselImageApi {

    @PostMapping("/carouselImage")
    ResponseEntity<List<CarouselImageVM>> save(@RequestBody List<CarouselImageVM> vm);

    @GetMapping("/carouselImage")
    ResponseEntity<List<CarouselImageVM>> findAll();

}
