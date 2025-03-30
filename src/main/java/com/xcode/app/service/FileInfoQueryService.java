package com.xcode.app.service;

import com.xcode.app.web.rest.filter.FilesCriteria;
import com.xcode.app.web.rest.vm.FileInfoVM;
import com.xcode.app.web.rest.vm.FilesVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FileInfoQueryService {

    Page<FileInfoVM> findByCriteria(FilesCriteria criteria, Pageable pageable);

}
