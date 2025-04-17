package com.xcode.app.service.mapper;

import com.xcode.app.domain.WxHomeFunction;
import com.xcode.app.web.rest.vm.WxHomeFunctionVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WxHomeFunctionMapper extends BaseMapper<WxHomeFunction, WxHomeFunctionVM> {


}
