package com.xcode.app.repository;

import com.xcode.app.domain.Authority;
import com.xcode.app.domain.BackendUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface BackendUserRepository extends JpaRepository<BackendUser, Long>, JpaSpecificationExecutor<BackendUser> {
    Optional<BackendUser> findByUsername(String username);

}
