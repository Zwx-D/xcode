package com.xcode.app.web.rest.api;

import com.xcode.app.web.rest.vm.PhotographerVM;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PhotographerApi {

    @PostMapping("/photographer")
    ResponseEntity<PhotographerVM> save(@RequestBody PhotographerVM vm);

    @GetMapping("/photographer")
    ResponseEntity<PhotographerVM> getPhotographer();

}
