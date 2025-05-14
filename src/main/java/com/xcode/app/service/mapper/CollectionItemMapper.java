package com.xcode.app.service.mapper;

import com.xcode.app.domain.CollectionItem;
import com.xcode.app.web.rest.vm.CollectionItemVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CollectionItemMapper extends BaseMapper<CollectionItem, CollectionItemVM> {

    @Override
    @Mapping(target = "collectionFolder",ignore = true)
    CollectionItem toEntity(CollectionItemVM vm);

    @Override
    @Mapping(target = "collectionFolder",ignore = true)
    List<CollectionItem> toEntityList(List<CollectionItemVM> vmList);
}
