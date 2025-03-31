package com.xcode.app.repository;

import com.xcode.app.domain.FolderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FolderInfoRepository extends JpaRepository<FolderInfo, Long>, JpaSpecificationExecutor<FolderInfo> {

    Optional<FolderInfo> findOneByNameAndParentIsNull(String name);

}
