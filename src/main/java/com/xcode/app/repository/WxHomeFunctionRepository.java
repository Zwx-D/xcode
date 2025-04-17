package com.xcode.app.repository;

import com.xcode.app.domain.WxHomeFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WxHomeFunctionRepository extends JpaRepository<WxHomeFunction, Long>, JpaSpecificationExecutor<WxHomeFunction> {

    Optional<WxHomeFunction> findOneByUuid(String uuid);

    List<WxHomeFunction> findAllByIsShow(Boolean isShow);

}
