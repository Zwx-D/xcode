package com.xcode.app.service;

import com.xcode.app.web.rest.filter.PortfolioTypeTagCriteria;
import com.xcode.app.web.rest.vm.PortfolioTypeTagVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PortfolioTypeTagQueryService {

    Page<PortfolioTypeTagVM> findByCriteria(PortfolioTypeTagCriteria criteria, Pageable pageable);

}
