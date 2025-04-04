package com.xcode.app.repository;

import com.xcode.app.domain.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, Long>, JpaSpecificationExecutor<FileInfo> {

    Optional<FileInfo> findOneByUuid(String uuid);

}
