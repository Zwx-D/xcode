package com.xcode.app.service;

import com.xcode.app.web.rest.dto.CreateCollectionFolderDTO;
import com.xcode.app.web.rest.vm.CollectionFolderVM;

public interface CollectionFolderService {

    CollectionFolderVM save(CreateCollectionFolderDTO vm);

    CollectionFolderVM update(CollectionFolderVM vm);

    CollectionFolderVM findOneByUuid(String uuid);

}
