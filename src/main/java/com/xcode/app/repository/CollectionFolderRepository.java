package com.xcode.app.repository;

import com.xcode.app.domain.CollectionFolder;
import com.xcode.app.domain.WechatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollectionFolderRepository extends JpaRepository<CollectionFolder, Long>, JpaSpecificationExecutor<CollectionFolder> {

    Optional<CollectionFolder> findOneByNameAndWechatUserId(String name, Long wechatUserId);

    Optional<CollectionFolder> findOneByUuid(String uuid);

    List<CollectionFolder> findByWechatUserId(Long wechatUserId);

}
