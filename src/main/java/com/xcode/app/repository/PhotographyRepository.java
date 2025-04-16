package com.xcode.app.repository;

import com.xcode.app.domain.Photography;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotographyRepository extends JpaRepository<Photography, Long>, JpaSpecificationExecutor<Photography> {

    Optional<Photography> findOneByUuid(String uuid);

    List<Photography> findAllByIsShow(Boolean isShow);

}
