package com.xcode.app.service;

import com.xcode.app.web.rest.dto.CreateCollectionFolderDTO;
import com.xcode.app.web.rest.dto.UpdateCollectionItemDTO;
import com.xcode.app.web.rest.vm.CollectionFolderVM;

import java.util.List;

public interface CollectionFolderService {

    CollectionFolderVM save(CreateCollectionFolderDTO vm);

    CollectionFolderVM update(CollectionFolderVM vm);

    CollectionFolderVM findOneByUuid(String uuid);

    void delete(String uuid);

    List<CollectionFolderVM> findByWechatUserId(Long wechatUserId);

    void addItemInFolder(UpdateCollectionItemDTO dto);

    void delItemInFolder(String uuid);

}
