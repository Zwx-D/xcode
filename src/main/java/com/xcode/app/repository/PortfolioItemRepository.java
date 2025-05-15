package com.xcode.app.repository;

import com.xcode.app.domain.PortfolioItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Long>, JpaSpecificationExecutor<PortfolioItem> {

    Optional<PortfolioItem> findOneByUuid(String uuid);

}
