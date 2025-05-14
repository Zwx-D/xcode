package com.xcode.app.service.mapper;

import com.xcode.app.domain.Portfolio;
import com.xcode.app.web.rest.vm.PortfolioVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PortfolioMapper extends BaseMapper<Portfolio, PortfolioVM> {

    @Override
    @Mapping(target = "portfolioItemList", ignore = true)
    Portfolio toEntity(PortfolioVM vm);

    @Override
    @Mapping(target = "portfolioItemList", ignore = true)
    List<Portfolio> toEntityList(List<PortfolioVM> vmList);
}
