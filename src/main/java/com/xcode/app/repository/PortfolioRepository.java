package com.xcode.app.repository;

import com.xcode.app.domain.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long>, JpaSpecificationExecutor<Portfolio> {

    Optional<Portfolio> findOneByUuid(String uuid);

    Optional<Portfolio> findOneByName(String name);

}
