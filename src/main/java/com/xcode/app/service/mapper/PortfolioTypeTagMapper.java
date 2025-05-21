package com.xcode.app.service.mapper;

import com.xcode.app.domain.PortfolioTypeTag;
import com.xcode.app.web.rest.vm.PortfolioTypeTagVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PortfolioTypeTagMapper extends BaseMapper<PortfolioTypeTag, PortfolioTypeTagVM> {

}
