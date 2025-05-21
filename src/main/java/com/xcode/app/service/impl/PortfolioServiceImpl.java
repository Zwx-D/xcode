package com.xcode.app.service.impl;

import com.xcode.app.domain.Portfolio;
import com.xcode.app.domain.PortfolioItem;
import com.xcode.app.repository.PortfolioItemRepository;
import com.xcode.app.repository.PortfolioRepository;
import com.xcode.app.service.PortfolioService;
import com.xcode.app.service.mapper.PortfolioMapper;
import com.xcode.app.web.rest.dto.CreatePortfolioItemDTO;
import com.xcode.app.web.rest.vm.PortfolioVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private PortfolioRepository repository;

    @Autowired
    private PortfolioMapper mapper;

    @Autowired
    private PortfolioItemRepository portfolioItemRepository;

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
        data.setName(vm.getName());
        data.setSortOrder(vm.getSortOrder());
        data.setIsShow(vm.getIsShow());
        data.setImageUuid(vm.getImageUuid());
        data.setDesc(vm.getDesc());
        return mapper.toVM(repository.save(data));
    }

    @Override
    public PortfolioVM findOneByUuid(String uuid) {
        return repository.findOneByUuid(uuid).map(mapper::toVM).orElseThrow(() -> {
            throw new RuntimeException("找不到对应详情");
        });
    }

    @Override
    public void changIsShow(String uuid, Boolean isShow) {
        Portfolio portfolio = repository.findOneByUuid(uuid).orElseThrow(() -> {
            throw new RuntimeException("找不到对应详情");
        });
        portfolio.setIsShow(isShow);
        repository.save(portfolio);
    }

    @Override
    public void delete(String uuid) {
        Portfolio portfolio = repository.findOneByUuid(uuid).orElseThrow(() -> {
            throw new RuntimeException("找不到对应详情");
        });
        repository.delete(portfolio);
    }

    @Override
    public void createPortfolioItem(CreatePortfolioItemDTO dto) {
        Portfolio Portfolio = repository.findOneByUuid(dto.getPortfolioUuid()).orElseThrow(() -> {
            throw new RuntimeException("找不到对应详情");
        });
        log.info("查询到的作品集详情：{}", Portfolio);
        List<PortfolioItem> collect = Portfolio.getPortfolioItemList()
            .stream().filter(item -> item.getImageUuid().equals(dto.getImageUuid())).collect(Collectors.toList());
        if (collect.size() > 0) {
            throw new RuntimeException("当前作品集已存在该照片");
        }
        PortfolioItem portfolioItem = new PortfolioItem();
        portfolioItem.setDesc(dto.getDesc());
        portfolioItem.setImageUuid(dto.getImageUuid());
        portfolioItem.setIsShow(dto.getIsShow());
        portfolioItem.setName(dto.getName());
        portfolioItem.setPortfolio(Portfolio);
        portfolioItem.setSortOrder(dto.getSortOrder());
        portfolioItemRepository.save(portfolioItem);
    }

    @Override
    public void changItemIsShow(String uuid, Boolean isShow) {
        PortfolioItem portfolioItem = portfolioItemRepository.findOneByUuid(uuid).orElseThrow(() -> {
            throw new RuntimeException("找不到对应详情");
        });
        portfolioItem.setIsShow(isShow);
        portfolioItemRepository.save(portfolioItem);
    }

    @Override
    public void deletePortfolioItem(String uuid) {
        PortfolioItem portfolioItem = portfolioItemRepository.findOneByUuid(uuid).orElseThrow(() -> {
            throw new RuntimeException("找不到对应详情");
        });
        portfolioItemRepository.delete(portfolioItem);
    }

    @Override
    public List<String> findDistinctTypeTags() {
        return repository.findDistinctTypeTags();
    }

}
