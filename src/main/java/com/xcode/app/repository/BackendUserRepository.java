package com.xcode.app.repository;

import com.xcode.app.domain.BackendUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BackendUserRepository extends JpaRepository<BackendUser, Long> {
    Optional<BackendUser> findByUsername(String username);

    Page<BackendUser> findAll(Specification<BackendUser> spec, Pageable pageable);

}
