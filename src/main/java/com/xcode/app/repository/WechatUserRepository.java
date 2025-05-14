package com.xcode.app.repository;

import com.xcode.app.domain.WechatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WechatUserRepository extends JpaRepository<WechatUser, Long>, JpaSpecificationExecutor<WechatUser> {

    Optional<WechatUser> findOneByUnionId(String unionId);

    Optional<WechatUser> findOneById(Long id);

}
