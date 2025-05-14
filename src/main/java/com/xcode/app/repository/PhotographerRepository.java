package com.xcode.app.repository;

import com.xcode.app.domain.Photographer;
import com.xcode.app.domain.WechatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotographerRepository extends JpaRepository<Photographer, Long>, JpaSpecificationExecutor<Photographer> {


}
