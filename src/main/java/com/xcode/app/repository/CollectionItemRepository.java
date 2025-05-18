package com.xcode.app.repository;

import com.xcode.app.domain.CollectionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollectionItemRepository extends JpaRepository<CollectionItem, Long>, JpaSpecificationExecutor<CollectionItem> {

    Optional<CollectionItem> findOneByUuid(String uuid);

}
