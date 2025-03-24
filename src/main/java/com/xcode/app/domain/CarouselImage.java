package com.xcode.app.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "carousel_image")
@EqualsAndHashCode(callSuper=false)
public class CarouselImage extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 对应图片的uuid
     */
    @Column(nullable = false)
    private String imageUuid;

    /**
     * 图片排序
     */
    @Column(nullable = false)
    private Integer sortOrder;

}
