package com.xcode.app.repository;

import com.xcode.app.domain.BackendUser;
import com.xcode.app.domain.FolderInfo;
import com.xcode.app.web.rest.vm.FolderVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface FolderInfoRepository extends JpaRepository<FolderInfo, Long>, JpaSpecificationExecutor<FolderInfo> {

    Optional<FolderInfo> findOneByNameAndParentIsNull(String name);

}
