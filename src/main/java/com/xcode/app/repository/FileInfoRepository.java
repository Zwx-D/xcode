package com.xcode.app.repository;

import com.xcode.app.domain.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {

    Optional<FileInfo> findOneByUuid(String uuid);

}
