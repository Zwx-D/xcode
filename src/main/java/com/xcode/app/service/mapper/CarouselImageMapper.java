package com.xcode.app.service.mapper;

import com.xcode.app.domain.CarouselImage;
import com.xcode.app.web.rest.vm.CarouselImageVM;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarouselImageMapper extends BaseMapper<CarouselImage, CarouselImageVM> {
}
