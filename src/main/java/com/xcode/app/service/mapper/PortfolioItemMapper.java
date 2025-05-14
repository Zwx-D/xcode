package com.xcode.app.service.mapper;

import com.xcode.app.domain.PortfolioItem;
import com.xcode.app.web.rest.vm.PortfolioItemVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PortfolioItemMapper extends BaseMapper<PortfolioItem, PortfolioItemVM> {

    @Override
    @Mapping(target = "portfolio",ignore = true)
    PortfolioItem toEntity(PortfolioItemVM vm);

    @Override
    @Mapping(target = "portfolio",ignore = true)
    List<PortfolioItem> toEntityList(List<PortfolioItemVM> vmList);
}
