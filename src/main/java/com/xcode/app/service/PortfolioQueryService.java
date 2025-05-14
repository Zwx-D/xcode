package com.xcode.app.service;

import com.xcode.app.web.rest.filter.PortfolioCriteria;
import com.xcode.app.web.rest.vm.PortfolioVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PortfolioQueryService {

    Page<PortfolioVM> findByCriteria(PortfolioCriteria criteria, Pageable pageable);

}
