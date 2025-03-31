package com.xcode.app.service.impl;

import com.xcode.app.domain.Photography;
import com.xcode.app.domain.Photography_;
import com.xcode.app.repository.PhotographyRepository;
import com.xcode.app.service.PhotographyQueryService;
import com.xcode.app.service.mapper.PhotographyMapper;
import com.xcode.app.web.rest.filter.PhotographyCriteria;
import com.xcode.app.web.rest.vm.PhotographyVM;
import io.github.jhipster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class PhotographyQueryServiceImpl extends QueryService<Photography> implements PhotographyQueryService {

    @Autowired
    private PhotographyRepository repository;

    @Autowired
    private PhotographyMapper mapper;

    @Override
    public Page<PhotographyVM> findByCriteria(PhotographyCriteria criteria, Pageable pageable) {
        Specification<Photography> spec = createSpecification(criteria);
        return repository.findAll(spec, pageable).map(mapper::toVM);
    }

    private Specification<Photography> createSpecification(PhotographyCriteria criteria) {
        Specification<Photography> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Photography_.id));
            }
            if (criteria.getImageUuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageUuid(), Photography_.imageUuid));
            }
            if (criteria.getSortOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSortOrder(), Photography_.sortOrder));
            }
            if (criteria.getDesc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDesc(), Photography_.desc));
            }
            if (criteria.getUuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUuid(), Photography_.uuid));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), Photography_.createdBy));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), Photography_.lastModifiedBy));
            }
            if (criteria.getCreatedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedTime(), Photography_.createdTime));
            }
            if (criteria.getLastModifiedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedTime(), Photography_.lastModifiedTime));
            }
        }
        return specification;
    }
}
