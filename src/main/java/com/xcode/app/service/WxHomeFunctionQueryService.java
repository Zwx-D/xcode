package com.xcode.app.service;

import com.xcode.app.web.rest.filter.WxHomeFunctionCriteria;
import com.xcode.app.web.rest.vm.WxHomeFunctionVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WxHomeFunctionQueryService {

    Page<WxHomeFunctionVM> findByCriteria(WxHomeFunctionCriteria criteria, Pageable pageable);

}
