package com.xcode.app.service.impl;

import com.xcode.app.domain.Portfolio;
import com.xcode.app.domain.Portfolio_;
import com.xcode.app.domain.Portfolio;
import com.xcode.app.repository.PortfolioRepository;
import com.xcode.app.service.PortfolioQueryService;
import com.xcode.app.service.mapper.PortfolioMapper;
import com.xcode.app.web.rest.filter.PortfolioCriteria;
import com.xcode.app.web.rest.vm.PortfolioVM;
import io.github.jhipster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class PortfolioQueryServiceImpl extends QueryService<Portfolio> implements PortfolioQueryService {

    @Autowired
    private PortfolioRepository repository;

    @Autowired
    private PortfolioMapper mapper;

    @Override
    public Page<PortfolioVM> findByCriteria(PortfolioCriteria criteria, Pageable pageable) {
        Specification<Portfolio> spec = createSpecification(criteria);
        return repository.findAll(spec, pageable).map(mapper::toVM);
    }

    private Specification<Portfolio> createSpecification(PortfolioCriteria criteria) {
        Specification<Portfolio> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Portfolio_.id));
            }
            if (criteria.getImageUuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageUuid(), Portfolio_.imageUuid));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Portfolio_.name));
            }
            if (criteria.getTypeTag() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeTag(), Portfolio_.typeTag));
            }
            if (criteria.getSortOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSortOrder(), Portfolio_.sortOrder));
            }
            if (criteria.getUuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUuid(), Portfolio_.uuid));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), Portfolio_.createdBy));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), Portfolio_.lastModifiedBy));
            }
            if (criteria.getCreatedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedTime(), Portfolio_.createdTime));
            }
            if (criteria.getIsShow() != null) {
                specification = specification.and(buildSpecification(criteria.getIsShow(), Portfolio_.isShow));
            }
            if (criteria.getLastModifiedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedTime(), Portfolio_.lastModifiedTime));
            }
        }
        return specification;
    }

}
