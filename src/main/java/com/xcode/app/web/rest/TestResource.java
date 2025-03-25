package com.xcode.app.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestResource {

    @GetMapping("/test")
    public ResponseEntity<String> registerAccount() {
        return ResponseEntity.ok("調用成功");
    }


}
