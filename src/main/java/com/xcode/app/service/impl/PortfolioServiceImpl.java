package com.xcode.app.service.impl;

import com.xcode.app.domain.Portfolio;
import com.xcode.app.repository.PortfolioRepository;
import com.xcode.app.service.PortfolioService;
import com.xcode.app.service.mapper.PortfolioMapper;
import com.xcode.app.web.rest.vm.PortfolioVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private PortfolioRepository repository;

    @Autowired
    private PortfolioMapper mapper;

    @Override
    public PortfolioVM save(PortfolioVM vm) {
        return mapper.toVM(repository.save(mapper.toEntity(vm)));
    }

    @Override
    public PortfolioVM update(PortfolioVM vm) {
        Portfolio data = repository.findOneByUuid(vm.getUuid()).orElseThrow(() -> {
            throw new RuntimeException("找不到对应详情");
        });
        if (!data.getName().equals(vm.getName())) {
            Optional<Portfolio> oneByName = repository.findOneByName(vm.getName());
            if (oneByName.isPresent()) {
                throw new RuntimeException("已存在" + vm.getName() + "的作品集");
            }
        }
        return save(vm);
    }

    @Override
    public PortfolioVM findOneByUuid(String uuid) {
        return repository.findOneByUuid(uuid).map(mapper::toVM).orElseThrow(() -> {
            throw new RuntimeException("找不到对应详情");
        });
    }

}
