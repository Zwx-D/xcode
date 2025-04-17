package com.xcode.app.service.impl;

import com.xcode.app.domain.WxHomeFunction;
import com.xcode.app.repository.WxHomeFunctionRepository;
import com.xcode.app.service.WxHomeFunctionService;
import com.xcode.app.service.mapper.WxHomeFunctionMapper;
import com.xcode.app.web.rest.vm.WxHomeFunctionVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WxHomeFunctionServiceImpl implements WxHomeFunctionService {

    @Autowired
    private WxHomeFunctionRepository repository;

    @Autowired
    private WxHomeFunctionMapper mapper;

    @Override
    public WxHomeFunctionVM save(WxHomeFunctionVM vm) {
        return mapper.toVM(repository.save(mapper.toEntity(vm)));
    }

    @Override
    public void delete(String uuid) {
        Optional<WxHomeFunction> oneByUuid = repository.findOneByUuid(uuid);
        if (oneByUuid.isPresent()) {
            WxHomeFunction wxHomeFunction = oneByUuid.get();
            repository.delete(wxHomeFunction);
        } else {
            throw new RuntimeException("找不到对应首页功能");
        }
    }

    @Override
    public void updateIsShow(String uuid, Boolean isShow) {
        Optional<WxHomeFunction> oneByUuid = repository.findOneByUuid(uuid);
        if (oneByUuid.isPresent()) {
            WxHomeFunction wxHomeFunction = oneByUuid.get();
            wxHomeFunction.setIsShow(isShow);
            repository.save(wxHomeFunction);
        } else {
            throw new RuntimeException("找不到对应首页功能");
        }
    }

    @Override
    public List<WxHomeFunctionVM> findAllByIsShow(Boolean isShow) {
        return mapper.toVMList(repository.findAllByIsShow(isShow));
    }

}
