package com.xcode.app.service;

import com.xcode.app.domain.BackendUser;
import com.xcode.app.web.rest.filter.BackendUserCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BackendUserQueryService {

    Page<BackendUser> findByCriteria(BackendUserCriteria filter, Pageable pageable);

}
