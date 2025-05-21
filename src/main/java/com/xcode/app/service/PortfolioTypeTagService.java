package com.xcode.app.service;

import com.xcode.app.web.rest.vm.PortfolioTypeTagVM;

import java.util.List;

public interface PortfolioTypeTagService {

    PortfolioTypeTagVM save(PortfolioTypeTagVM vm);

    PortfolioTypeTagVM update(PortfolioTypeTagVM vm);

    void delete(String uuid);

    List<PortfolioTypeTagVM> findAll();

}
