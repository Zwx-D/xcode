package com.xcode.app.service;

import com.xcode.app.domain.BackendUser;
import com.xcode.app.web.rest.filter.BackendUserFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BackendUserQueryService {

    Page<BackendUser> findAllBackendUser(BackendUserFilter filter, Pageable pageable);

}
