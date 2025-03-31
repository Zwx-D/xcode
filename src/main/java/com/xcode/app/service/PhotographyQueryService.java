package com.xcode.app.service;

import com.xcode.app.web.rest.filter.PhotographyCriteria;
import com.xcode.app.web.rest.vm.PhotographyVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhotographyQueryService {

    Page<PhotographyVM> findByCriteria(PhotographyCriteria criteria, Pageable pageable);

}
