package com.xcode.app.service.mapper;

import com.xcode.app.domain.CarouselImage;
import com.xcode.app.web.rest.vm.CarouselImageVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarouselImageMapper extends BaseMapper<CarouselImage, CarouselImageVM> {

    @Override
    @Mappings({
        @Mapping(target = "linkUrl", ignore = true)
    })
    CarouselImageVM toVM(CarouselImage entity);

    @Override
    @Mappings({
        @Mapping(target = "linkUrl", ignore = true)
    })
    List<CarouselImageVM> toVMList(List<CarouselImage> entityList);

}
