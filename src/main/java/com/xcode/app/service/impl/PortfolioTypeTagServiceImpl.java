package com.xcode.app.service.impl;

import com.xcode.app.domain.PortfolioTypeTag;
import com.xcode.app.repository.PortfolioTypeTagRepository;
import com.xcode.app.service.PortfolioTypeTagService;
import com.xcode.app.service.mapper.PortfolioTypeTagMapper;
import com.xcode.app.web.rest.vm.PortfolioTypeTagVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioTypeTagServiceImpl implements PortfolioTypeTagService {

    @Autowired
    private PortfolioTypeTagRepository repository;

    @Autowired
    private PortfolioTypeTagMapper mapper;


    @Override
    public PortfolioTypeTagVM save(PortfolioTypeTagVM vm) {
        if (repository.findOneByName(vm.getName()).isPresent()) {
            throw new RuntimeException("已存在当前标签");
        }
        return mapper.toVM(repository.save(mapper.toEntity(vm)));
    }

    @Override
    public PortfolioTypeTagVM update(PortfolioTypeTagVM vm) {
        PortfolioTypeTag portfolioTypeTag = repository.findOneByUuid(vm.getUuid()).orElseThrow(() -> {
            throw new RuntimeException("找不到对应详情");
        });
        if (!portfolioTypeTag.getName().equals(vm.getName())) {
            if (repository.findOneByName(vm.getName()).isPresent()) {
                throw new RuntimeException("已存在当前标签");
            }
        }
        portfolioTypeTag.setDesc(vm.getDesc());
        portfolioTypeTag.setName(vm.getName());
        return mapper.toVM(repository.save(portfolioTypeTag));
    }

    @Override
    public void delete(String uuid) {
        PortfolioTypeTag portfolioTypeTag = repository.findOneByUuid(uuid).orElseThrow(() -> {
            throw new RuntimeException("找不到对应详情");
        });
        repository.delete(portfolioTypeTag);
    }

    @Override
    public List<PortfolioTypeTagVM> findAll() {
        return mapper.toVMList(repository.findAll());
    }

}
