package com.xcode.app.service.impl;

import com.xcode.app.repository.WechatUserRepository;
import com.xcode.app.service.WechatUserService;
import com.xcode.app.service.mapper.WechatUserMapper;
import com.xcode.app.web.rest.vm.WechatUserVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return mapper.toVM(repository.save(mapper.toEntity(vm)));
    }

    @Override
    public WechatUserVM findOneByUnionId(String unionId) {
        return repository.findOneByUnionId(unionId).map(mapper::toVM).orElseThrow(() -> {
            throw new RuntimeException("找不到对应用户！");
        });
    }

}
