package com.xcode.app.service.mapper;

import com.xcode.app.domain.CollectionFolder;
import com.xcode.app.web.rest.vm.CollectionFolderVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CollectionFolderMapper extends BaseMapper<CollectionFolder, CollectionFolderVM> {

    @Override
    @Mapping(target = "wechatUser",ignore = true)
    @Mapping(target = "collectionItemList",ignore = true)
    CollectionFolder toEntity(CollectionFolderVM vm);

    @Override
    @Mapping(target = "wechatUser",ignore = true)
    @Mapping(target = "collectionItemList",ignore = true)
    List<CollectionFolder> toEntityList(List<CollectionFolderVM> vmList);
}
