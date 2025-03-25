package com.xcode.app.repository;

import com.xcode.app.domain.FolderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FolderInfoRepository extends JpaRepository<FolderInfo, Long> {

    Optional<FolderInfo> findOneByNameAndParentIsNull(String name);

}
