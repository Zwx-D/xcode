package com.xcode.app.service.impl;

import com.xcode.app.domain.CarouselImage;
import com.xcode.app.domain.CarouselImage_;
import com.xcode.app.repository.CarouselImageRepository;
import com.xcode.app.service.CarouselImageQueryService;
import com.xcode.app.service.CarouselImageService;
import com.xcode.app.service.mapper.CarouselImageMapper;
import com.xcode.app.web.rest.filter.CarouselImageCriteria;
import com.xcode.app.web.rest.vm.CarouselImageVM;
import io.github.jhipster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CarouselImageQueryServiceImpl extends QueryService<CarouselImage> implements CarouselImageQueryService {

    @Autowired
    private CarouselImageMapper mapper;

    @Autowired
    private CarouselImageRepository repository;

    @Autowired
    private CarouselImageService service;

    @Override
    public Page<CarouselImageVM> findByCriteria(CarouselImageCriteria criteria, Pageable pageable) {
        Specification<CarouselImage> spec = createSpecification(criteria);
        return repository.findAll(spec, pageable).map(mapper::toVM);
    }

    private Specification<CarouselImage> createSpecification(CarouselImageCriteria criteria) {
        Specification<CarouselImage> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CarouselImage_.id));
            }
            if (criteria.getImageUuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageUuid(), CarouselImage_.imageUuid));
            }
            if (criteria.getSortOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSortOrder(), CarouselImage_.sortOrder));
            }
            if (criteria.getUuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUuid(), CarouselImage_.uuid));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), CarouselImage_.createdBy));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), CarouselImage_.lastModifiedBy));
            }
            if (criteria.getCreatedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedTime(), CarouselImage_.createdTime));
            }
            if (criteria.getLastModifiedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedTime(), CarouselImage_.lastModifiedTime));
            }
        }
        return specification;
    }

}
