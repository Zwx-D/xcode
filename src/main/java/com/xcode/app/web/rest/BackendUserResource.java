package com.xcode.app.web.rest;

import com.xcode.app.domain.BackendUser;
import com.xcode.app.service.BackendUserQueryService;
import com.xcode.app.service.BackendUserService;
import com.xcode.app.web.rest.api.BackendUserApi;
import com.xcode.app.web.rest.dto.LoginDTO;
import com.xcode.app.web.rest.filter.BackendUserFilter;
import com.xcode.app.web.rest.vm.JwtVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class BackendUserResource implements BackendUserApi {

    @Autowired
    private BackendUserService backendUserService;

    @Autowired
    private BackendUserQueryService queryService;

    @Override
    public ResponseEntity<String> register(BackendUser user) {
        return null;
    }

    @Override
    public ResponseEntity<JwtVM> login(LoginDTO login) {
        return ResponseEntity.ok(
            JwtVM.builder()
                .token(backendUserService.loginUser(login.getUsername(), login.getPassword()))
                .build());
    }

    @Override
    public ResponseEntity<List<BackendUser>> getUserList(BackendUserFilter filter, Pageable pageable) {
        return null;
    }

}
