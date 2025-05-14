package com.xcode.app.service;

import com.xcode.app.web.rest.vm.PortfolioVM;

public interface PortfolioService {

    PortfolioVM save(PortfolioVM vm);

    PortfolioVM update(PortfolioVM vm);

    PortfolioVM findOneByUuid(String uuid);

}
