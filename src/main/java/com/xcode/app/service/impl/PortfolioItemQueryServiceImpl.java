package com.xcode.app.service.impl;

import com.xcode.app.domain.FileInfo;
import com.xcode.app.domain.FileInfo_;
import com.xcode.app.domain.PortfolioItem;
import com.xcode.app.domain.PortfolioItem_;
import com.xcode.app.repository.PortfolioItemRepository;
import com.xcode.app.service.PortfolioItemQueryService;
import com.xcode.app.service.mapper.PortfolioItemMapper;
import com.xcode.app.web.rest.filter.PortfolioItemCriteria;
import com.xcode.app.web.rest.vm.PortfolioItemVM;
import io.github.jhipster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

@Service
public class PortfolioItemQueryServiceImpl extends QueryService<PortfolioItem> implements PortfolioItemQueryService {

    @Autowired
    private PortfolioItemRepository repository;

    @Autowired
    private PortfolioItemMapper mapper;

    @Override
    public Page<PortfolioItemVM> findByCriteria(PortfolioItemCriteria criteria, Pageable pageable) {
        Specification<PortfolioItem> spec = createSpecification(criteria);
        return repository.findAll(spec, pageable).map(mapper::toVM);
    }

    private Specification<PortfolioItem> createSpecification(PortfolioItemCriteria criteria) {
        Specification<PortfolioItem> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), PortfolioItem_.id));
            }
            if (criteria.getImageUuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageUuid(), PortfolioItem_.imageUuid));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PortfolioItem_.name));
            }
            if (criteria.getSortOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSortOrder(), PortfolioItem_.sortOrder));
            }
            if (criteria.getUuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUuid(), PortfolioItem_.uuid));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), PortfolioItem_.createdBy));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), PortfolioItem_.lastModifiedBy));
            }
            if (criteria.getCreatedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedTime(), PortfolioItem_.createdTime));
            }
            if (criteria.getLastModifiedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedTime(), PortfolioItem_.lastModifiedTime));
            }

            if (criteria.getPortfolioUuid() != null) {
                specification = specification.and(buildSpecification(criteria.getPortfolioUuid(), root -> getPath(root, "uuid")));
            }
        }
        return specification;
    }


    private Path getPath(Root<PortfolioItem> root, String fieldName) {
        Join join = root.getJoins().stream().filter(j -> JoinType.LEFT.equals(j.getJoinType())).findFirst().orElse(null);
        if (root.getJoins().isEmpty()) {
            Path path = root.join(PortfolioItem_.portfolio, JoinType.LEFT).get(fieldName);
            return path;
        }
        return root.get(fieldName);
    }


}
