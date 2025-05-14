package com.xcode.app.service.mapper;

import com.xcode.app.domain.WechatUser;
import com.xcode.app.domain.WxHomeFunction;
import com.xcode.app.web.rest.vm.WechatUserVM;
import com.xcode.app.web.rest.vm.WxHomeFunctionVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WechatUserMapper extends BaseMapper<WechatUser, WechatUserVM> {

    @Override
    @Mapping(target = "collectionFolders",ignore = true)
    WechatUser toEntity(WechatUserVM vm);

    @Override
    @Mapping(target = "collectionFolders",ignore = true)
    List<WechatUser> toEntityList(List<WechatUserVM> vmList);
}
