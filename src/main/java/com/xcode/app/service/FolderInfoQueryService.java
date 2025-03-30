package com.xcode.app.service;

import com.xcode.app.web.rest.filter.FolderCriteria;
import com.xcode.app.web.rest.vm.FolderVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FolderInfoQueryService {

    Page<FolderVM> findByCriteria(FolderCriteria criteria, Pageable pageable);

}
