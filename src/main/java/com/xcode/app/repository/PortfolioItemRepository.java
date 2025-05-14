package com.xcode.app.repository;

import com.xcode.app.domain.PortfolioItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Long>, JpaSpecificationExecutor<PortfolioItem> {


}
