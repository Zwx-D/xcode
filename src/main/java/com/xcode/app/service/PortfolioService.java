package com.xcode.app.service;

import com.xcode.app.web.rest.dto.CreatePortfolioItemDTO;
import com.xcode.app.web.rest.vm.PortfolioVM;

import java.util.List;

public interface PortfolioService {

    PortfolioVM save(PortfolioVM vm);

    PortfolioVM update(PortfolioVM vm);

    PortfolioVM findOneByUuid(String uuid);

    void changIsShow(String uuid, Boolean isShow);

    void delete(String uuid);

    void createPortfolioItem(CreatePortfolioItemDTO dto);

    void changItemIsShow(String uuid, Boolean isShow);

    void deletePortfolioItem(String uuid);

    List<String> findDistinctTypeTags();

}
