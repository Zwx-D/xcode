package com.xcode.app.repository;

import com.xcode.app.domain.CarouselImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarouselImageRepository  extends JpaRepository<CarouselImage, Long> {
}
