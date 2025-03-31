package com.xcode.app.repository;

import com.xcode.app.domain.CarouselImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarouselImageRepository extends JpaRepository<CarouselImage, Long>, JpaSpecificationExecutor<CarouselImage> {

    Optional<CarouselImage> findOneByImageUuid(String imgageUuid);

    Optional<CarouselImage> findOneByUuid(String uuid);

}
