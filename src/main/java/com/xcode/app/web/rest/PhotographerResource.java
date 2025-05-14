package com.xcode.app.web.rest;

import com.xcode.app.service.PhotographerService;
import com.xcode.app.web.rest.api.PhotographerApi;
import com.xcode.app.web.rest.vm.PhotographerVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class PhotographerResource implements PhotographerApi {

    @Autowired
    private PhotographerService service;

    @Override
    public ResponseEntity<PhotographerVM> save(PhotographerVM vm) {
        return ResponseEntity.ok(service.save(vm));
    }

    @Override
    public ResponseEntity<PhotographerVM> getPhotographer() {
        return ResponseEntity.ok(service.getPhotographer());
    }

}
