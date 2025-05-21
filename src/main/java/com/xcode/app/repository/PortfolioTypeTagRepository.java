package com.xcode.app.repository;

import com.xcode.app.domain.PortfolioTypeTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfolioTypeTagRepository extends JpaRepository<PortfolioTypeTag, Long>, JpaSpecificationExecutor<PortfolioTypeTag> {

    Optional<PortfolioTypeTag> findOneByName(String name);

    Optional<PortfolioTypeTag> findOneByUuid(String uuid);
}
