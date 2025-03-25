package com.xcode.app.repository;

import com.xcode.app.domain.CarouselImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarouselImageRepository extends JpaRepository<CarouselImage, Long> {

    Optional<CarouselImage> findOneByImageUuid(String imgageUuid);

}
