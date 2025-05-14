package com.xcode.app.service.impl;

import com.xcode.app.domain.WechatUser;
import com.xcode.app.domain.WxHomeFunction;
import com.xcode.app.domain.WechatUser_;
import com.xcode.app.repository.WechatUserRepository;
import com.xcode.app.service.WechatUserQueryService;
import com.xcode.app.service.mapper.WechatUserMapper;
import com.xcode.app.web.rest.filter.WechatUserCriteria;
import com.xcode.app.web.rest.vm.WechatUserVM;
import io.github.jhipster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class WechatUserQueryServiceImpl extends QueryService<WechatUser> implements WechatUserQueryService {

    @Autowired
    private WechatUserRepository repository;

    @Autowired
    private WechatUserMapper mapper;

    @Override
    public Page<WechatUserVM> findByCriteria(WechatUserCriteria criteria, Pageable pageable) {
        Specification<WechatUser> spec = createSpecification(criteria);
        return repository.findAll(spec, pageable).map(mapper::toVM);
    }

    private Specification<WechatUser> createSpecification(WechatUserCriteria criteria) {
        Specification<WechatUser> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), WechatUser_.id));
            }
            if (criteria.getNickName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNickName(), WechatUser_.nickName));
            }
            if (criteria.getOpenId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOpenId(), WechatUser_.openId));
            }
            if (criteria.getUnionId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUnionId(), WechatUser_.unionId));
            }
            if (criteria.getLastLoginTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastLoginTime(), WechatUser_.lastLoginTime));
            }
            if (criteria.getUuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUuid(), WechatUser_.uuid));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), WechatUser_.createdBy));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), WechatUser_.lastModifiedBy));
            }
            if (criteria.getCreatedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedTime(), WechatUser_.createdTime));
            }
            if (criteria.getLastModifiedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedTime(), WechatUser_.lastModifiedTime));
            }
        }
        return specification;
    }

}
