package com.xcode.app.service.impl;

import com.xcode.app.domain.FolderInfo;
import com.xcode.app.domain.FolderInfo_;
import com.xcode.app.repository.FolderInfoRepository;
import com.xcode.app.service.FolderInfoQueryService;
import com.xcode.app.service.mapper.FolderMapper;
import com.xcode.app.web.rest.filter.FolderCriteria;
import com.xcode.app.web.rest.vm.FolderVM;
import io.github.jhipster.service.QueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FolderInfoQueryServiceImpl extends QueryService<FolderInfo> implements FolderInfoQueryService {

    @Autowired
    private FolderMapper mapper;

    @Autowired
    private FolderInfoRepository repository;

    @Override
    public Page<FolderVM> findByCriteria(FolderCriteria criteria, Pageable pageable) {
        Specification<FolderInfo> spec = createSpecification(criteria);
        Page<FolderInfo> all = repository.findAll(spec, pageable);
        log.info("查询出来的结果:{}", all.getContent());
        Page<FolderVM> map = all.map(mapper::toVM);
        log.info("Mapper后结果：{}",map.getContent());
        return map;
    }

    private Specification<FolderInfo> createSpecification(FolderCriteria criteria) {
        Specification<FolderInfo> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), FolderInfo_.id));
            }
            if (criteria.getUuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUuid(), FolderInfo_.uuid));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), FolderInfo_.name));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), FolderInfo_.createdBy));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), FolderInfo_.lastModifiedBy));
            }
            if (criteria.getCreatedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedTime(), FolderInfo_.createdTime));
            }
            if (criteria.getLastModifiedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedTime(), FolderInfo_.lastModifiedTime));
            }
        }
        return specification;
    }

}
