package com.xcode.app.service.mapper;

import com.xcode.app.domain.FolderInfo;
import com.xcode.app.web.rest.vm.FolderVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FolderMapper extends BaseMapper<FolderInfo, FolderVM> {


    @Override
    @Mappings({
        @Mapping(target = "parent", ignore = true),
        @Mapping(target = "subFolders", ignore = true),
        @Mapping(target = "files", ignore = true)
    })
    FolderInfo toEntity(FolderVM vm);

    @Override
    @Mappings({
        @Mapping(target = "parent", ignore = true),
        @Mapping(target = "subFolders", ignore = true),
        @Mapping(target = "files", ignore = true)
    })
    List<FolderInfo> toEntityList(List<FolderVM> vmList);
}
