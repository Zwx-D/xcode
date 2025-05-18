package com.xcode.app.service.impl;

import com.xcode.app.domain.WechatUser;
import com.xcode.app.repository.WechatUserRepository;
import com.xcode.app.service.WechatUserService;
import com.xcode.app.service.mapper.WechatUserMapper;
import com.xcode.app.web.rest.vm.WechatUserVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class WechatUserServiceImpl implements WechatUserService {

    @Autowired
    private WechatUserRepository repository;

    @Autowired
    private WechatUserMapper mapper;

    @Override
    public WechatUserVM save(WechatUserVM vm) {
        if (Optional.ofNullable(vm.getOpenId()).isEmpty()) {
            throw new RuntimeException("不存在OpenId");
        }
        Optional<WechatUser> oneByOpenId = repository.findOneByOpenId(vm.getOpenId());
        if (oneByOpenId.isPresent()) {
            WechatUser wechatUser = oneByOpenId.get();
            wechatUser.setNickName(vm.getNickName());
            wechatUser.setLastLoginTime(LocalDateTime.now());
            wechatUser.setAvatarUrl(vm.getAvatarUrl());
            wechatUser.setGender(vm.getGender());
            return mapper.toVM(repository.save(wechatUser));
        } else {
            vm.setLastLoginTime(LocalDateTime.now());
            return mapper.toVM(repository.save(mapper.toEntity(vm)));
        }
    }

    @Override
    public WechatUserVM findOneByUnionId(String unionId) {
        return repository.findOneByUnionId(unionId).map(mapper::toVM).orElseThrow(() -> {
            throw new RuntimeException("找不到对应用户！");
        });
    }

}
