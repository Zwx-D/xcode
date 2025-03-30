package com.xcode.app.web.rest.api;

import com.xcode.app.domain.BackendUser;
import com.xcode.app.web.rest.dto.LoginDTO;
import com.xcode.app.web.rest.filter.BackendUserCriteria;
import com.xcode.app.web.rest.vm.JwtVM;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BackendUserApi {

    @PostMapping("/backendUser/register")
    ResponseEntity<String> register(@RequestBody BackendUser user);

    @PostMapping("/backendUser/login")
    ResponseEntity<JwtVM> login(@RequestBody LoginDTO login);

    @GetMapping("/backendUser/getUserList")
    ResponseEntity<List<BackendUser>> getUserList(@SpringQueryMap BackendUserCriteria filter,
                                                  @PageableDefault(page = 0,size = 10) Pageable pageable);

}
