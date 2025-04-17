package com.xcode.app.service.impl;

import com.xcode.app.domain.WxHomeFunction;
import com.xcode.app.domain.WxHomeFunction_;
import com.xcode.app.repository.WxHomeFunctionRepository;
import com.xcode.app.service.WxHomeFunctionQueryService;
import com.xcode.app.service.mapper.WxHomeFunctionMapper;
import com.xcode.app.web.rest.filter.WxHomeFunctionCriteria;
import com.xcode.app.web.rest.vm.WxHomeFunctionVM;
import io.github.jhipster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class WxHomeFunctionQueryServiceImpl extends QueryService<WxHomeFunction> implements WxHomeFunctionQueryService {

    @Autowired
    private WxHomeFunctionRepository repository;

    @Autowired
    private WxHomeFunctionMapper mapper;

    @Override
    public Page<WxHomeFunctionVM> findByCriteria(WxHomeFunctionCriteria criteria, Pageable pageable) {
        Specification<WxHomeFunction> spec = createSpecification(criteria);
        return repository.findAll(spec, pageable).map(mapper::toVM);
    }

    private Specification<WxHomeFunction> createSpecification(WxHomeFunctionCriteria criteria) {
        Specification<WxHomeFunction> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), WxHomeFunction_.id));
            }

            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), WxHomeFunction_.name));
            }
            if (criteria.getRoute() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRoute(), WxHomeFunction_.route));
            }
            if (criteria.getImageUuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageUuid(), WxHomeFunction_.imageUuid));
            }
            if (criteria.getSortOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSortOrder(), WxHomeFunction_.sortOrder));
            }
            if (criteria.getDesc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDesc(), WxHomeFunction_.desc));
            }
            if (criteria.getUuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUuid(), WxHomeFunction_.uuid));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), WxHomeFunction_.createdBy));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), WxHomeFunction_.lastModifiedBy));
            }
            if (criteria.getCreatedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedTime(), WxHomeFunction_.createdTime));
            }
            if (criteria.getLastModifiedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedTime(), WxHomeFunction_.lastModifiedTime));
            }
        }
        return specification;
    }

}
