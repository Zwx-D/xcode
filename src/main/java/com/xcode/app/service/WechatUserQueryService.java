package com.xcode.app.service;

import com.xcode.app.web.rest.filter.WechatUserCriteria;
import com.xcode.app.web.rest.vm.WechatUserVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WechatUserQueryService {

    Page<WechatUserVM> findByCriteria(WechatUserCriteria criteria, Pageable pageable);

}
