package com.xcode.app.service.mapper;

import com.xcode.app.domain.Photography;
import com.xcode.app.web.rest.vm.PhotographyVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhotographyMapper extends BaseMapper<Photography, PhotographyVM> {
}
