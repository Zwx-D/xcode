package com.xcode.app.service;

import com.xcode.app.web.rest.filter.PortfolioItemCriteria;
import com.xcode.app.web.rest.vm.PortfolioItemVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PortfolioItemQueryService {

    Page<PortfolioItemVM> findByCriteria(PortfolioItemCriteria criteria, Pageable pageable);

}
