package com.xcode.app.web.rest.api;

import com.xcode.app.web.rest.dto.CreateCollectionFolderDTO;
import com.xcode.app.web.rest.vm.CollectionFolderVM;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface CollectionFolderApi {

    @PostMapping("/collectionFolder")
    ResponseEntity<CollectionFolderVM> save(@RequestBody CreateCollectionFolderDTO vm);

    @PutMapping("/collectionFolder")
    ResponseEntity<CollectionFolderVM> update(@RequestBody CollectionFolderVM vm);

    @GetMapping("/collectionFolder/{uuid}")
    ResponseEntity<CollectionFolderVM> findOneByUuid(@PathVariable String uuid);

}
