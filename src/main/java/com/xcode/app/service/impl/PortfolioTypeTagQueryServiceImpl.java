package com.xcode.app.service.impl;

import com.xcode.app.domain.PortfolioTypeTag;
import com.xcode.app.domain.PortfolioTypeTag_;
import com.xcode.app.repository.PortfolioTypeTagRepository;
import com.xcode.app.service.PortfolioTypeTagQueryService;
import com.xcode.app.service.mapper.PortfolioTypeTagMapper;
import com.xcode.app.web.rest.filter.PortfolioTypeTagCriteria;
import com.xcode.app.web.rest.vm.PortfolioTypeTagVM;
import io.github.jhipster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class PortfolioTypeTagQueryServiceImpl extends QueryService<PortfolioTypeTag> implements PortfolioTypeTagQueryService {

    @Autowired
    private PortfolioTypeTagRepository repository;

    @Autowired
    private PortfolioTypeTagMapper mapper;

    @Override
    public Page<PortfolioTypeTagVM> findByCriteria(PortfolioTypeTagCriteria criteria, Pageable pageable) {
        Specification<PortfolioTypeTag> spec = createSpecification(criteria);
        return repository.findAll(spec, pageable).map(mapper::toVM);
    }

    private Specification<PortfolioTypeTag> createSpecification(PortfolioTypeTagCriteria criteria) {
        Specification<PortfolioTypeTag> specification = Specification.where(null);

        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), PortfolioTypeTag_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PortfolioTypeTag_.name));
            }
            if (criteria.getDesc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDesc(), PortfolioTypeTag_.desc));
            }
            if (criteria.getUuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUuid(), PortfolioTypeTag_.uuid));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), PortfolioTypeTag_.createdBy));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), PortfolioTypeTag_.lastModifiedBy));
            }
            if (criteria.getCreatedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedTime(), PortfolioTypeTag_.createdTime));
            }
            if (criteria.getLastModifiedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedTime(), PortfolioTypeTag_.lastModifiedTime));
            }
        }

        return specification;
    }

}
