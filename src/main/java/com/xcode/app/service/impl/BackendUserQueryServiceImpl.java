package com.xcode.app.service.impl;

import com.xcode.app.domain.BackendUser;
import com.xcode.app.domain.BackendUser_;
import com.xcode.app.repository.BackendUserRepository;
import com.xcode.app.service.BackendUserQueryService;
import com.xcode.app.web.rest.filter.BackendUserFilter;
import io.github.jhipster.service.QueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BackendUserQueryServiceImpl extends QueryService<BackendUser> implements BackendUserQueryService {

    @Autowired
    private BackendUserRepository backendUserRepository;

    public Page<BackendUser> findAllBackendUser(BackendUserFilter filter, Pageable pageable) {
        Specification<BackendUser> spec = createSpecification(filter);
        return backendUserRepository.findAll(spec, pageable);
    }

    private Specification<BackendUser> createSpecification(BackendUserFilter filter) {
        Specification<BackendUser> specification = Specification.where(null);
        if (filter != null) {
            if (filter.getUsername() != null) {
                specification = specification.and(buildStringSpecification(filter.getUsername(), BackendUser_.username));
            }
            if (filter.getRealName() != null) {
                specification = specification.and(buildStringSpecification(filter.getRealName(), BackendUser_.realName));
            }
            if (filter.getEmail() != null) {
                specification = specification.and(buildStringSpecification(filter.getEmail(), BackendUser_.email));
            }
            if (filter.getPhone() != null) {
                specification = specification.and(buildStringSpecification(filter.getPhone(), BackendUser_.phone));
            }
            if (filter.getEnabled() != null) {
                specification = specification.and(buildSpecification(filter.getEnabled(), BackendUser_.enabled));
            }
            if (filter.getLastLoginTime() != null) {
                specification = specification.and(buildRangeSpecification(filter.getLastLoginTime(), BackendUser_.lastLoginTime));
            }
            if (filter.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(filter.getCreatedBy(), BackendUser_.createdBy));
            }
            if (filter.getCreatedTime() != null) {
                specification = specification.and(buildRangeSpecification(filter.getCreatedTime(), BackendUser_.createdTime));
            }
            if (filter.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(filter.getLastModifiedBy(), BackendUser_.lastModifiedBy));
            }
            if (filter.getLastModifiedTime() != null) {
                specification = specification.and(buildRangeSpecification(filter.getLastModifiedTime(), BackendUser_.lastModifiedTime));
            }
        }
        return specification;
    }

}
