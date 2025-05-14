package com.xcode.app.web.rest;

import com.xcode.app.service.CollectionFolderService;
import com.xcode.app.web.rest.api.CollectionFolderApi;
import com.xcode.app.web.rest.dto.CreateCollectionFolderDTO;
import com.xcode.app.web.rest.vm.CollectionFolderVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api")
public class CollectionFolderResource implements CollectionFolderApi {

    @Autowired
    private CollectionFolderService service;

    @Override
    public ResponseEntity<CollectionFolderVM> save(CreateCollectionFolderDTO vm) {
        return ResponseEntity.ok(service.save(vm));
    }

    @Override
    public ResponseEntity<CollectionFolderVM> update(CollectionFolderVM vm) {
        return ResponseEntity.ok(service.update(vm));
    }

    @Override
    public ResponseEntity<CollectionFolderVM> findOneByUuid(String uuid) {
        return ResponseEntity.ok(service.findOneByUuid(uuid));
    }

}
