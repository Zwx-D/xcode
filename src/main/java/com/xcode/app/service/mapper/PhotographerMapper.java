package com.xcode.app.service.mapper;

import com.xcode.app.domain.Photographer;
import com.xcode.app.web.rest.vm.PhotographerVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhotographerMapper extends BaseMapper<Photographer, PhotographerVM> {


}
