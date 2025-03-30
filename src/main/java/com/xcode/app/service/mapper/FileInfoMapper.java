package com.xcode.app.service.mapper;

import com.xcode.app.domain.FileInfo;
import com.xcode.app.web.rest.vm.FileInfoVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileInfoMapper extends BaseMapper<FileInfo, FileInfoVM> {

    @Override
    @Mappings({
        @Mapping(target = "folder", ignore = true)
    })
    List<FileInfo> toEntityList(List<FileInfoVM> vmList);

    @Override
    @Mappings({
        @Mapping(target = "folder", ignore = true)
    })
    FileInfo toEntity(FileInfoVM vm);
}
