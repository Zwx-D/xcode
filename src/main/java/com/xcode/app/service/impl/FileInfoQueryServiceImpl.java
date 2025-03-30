package com.xcode.app.service.impl;

import com.xcode.app.domain.FileInfo;
import com.xcode.app.domain.FileInfo_;
import com.xcode.app.domain.FolderInfo;
import com.xcode.app.domain.FolderInfo_;
import com.xcode.app.repository.FileInfoRepository;
import com.xcode.app.service.FileInfoQueryService;
import com.xcode.app.service.mapper.FileInfoMapper;
import com.xcode.app.web.rest.filter.FilesCriteria;
import com.xcode.app.web.rest.vm.FileInfoVM;
import io.github.jhipster.service.QueryService;
import io.github.jhipster.service.filter.LongFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

@Slf4j
@Service
public class FileInfoQueryServiceImpl extends QueryService<FileInfo> implements FileInfoQueryService {

    @Autowired
    private FileInfoMapper mapper;

    @Autowired
    private FileInfoRepository repository;

    @Override
    public Page<FileInfoVM> findByCriteria(FilesCriteria criteria, Pageable pageable) {
        Specification<FileInfo> spec = createSpecification(criteria);
        return repository.findAll(spec, pageable).map(mapper::toVM);
    }

    private Specification<FileInfo> createSpecification(FilesCriteria criteria) {
        Specification<FileInfo> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), FileInfo_.id));
            }
            if (criteria.getUuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUuid(), FileInfo_.uuid));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), FileInfo_.name));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), FileInfo_.createdBy));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), FileInfo_.lastModifiedBy));
            }
            if (criteria.getCreatedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedTime(), FileInfo_.createdTime));
            }
            if (criteria.getLastModifiedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedTime(), FileInfo_.lastModifiedTime));
            }


            // 处理 FolderInfo 相关的过滤条件
            if (criteria.getFolderId() != null) {
                specification = specification.and(buildSpecification(criteria.getFolderId(), root -> getPath(root, "id")));
            }

            if (criteria.getFolderUuid() != null) {
                specification = specification.and(buildSpecification(criteria.getFolderUuid(), root -> getPath(root, "uuid")));
            }

            if (criteria.getFolderName() != null) {
                specification = specification.and(buildSpecification(criteria.getFolderName(), root -> getPath(root, "name")));
            }
        }
        return specification;
    }


    private Path getPath(Root<FileInfo> root, String fieldName) {
        Join join = root.getJoins().stream().filter(j -> JoinType.LEFT.equals(j.getJoinType())).findFirst().orElse(null);
        if (root.getJoins().isEmpty()) {
            Path path = root.join(FileInfo_.folder, JoinType.LEFT).get(fieldName);
            return path;
        }
        return root.get(fieldName);
    }

}
