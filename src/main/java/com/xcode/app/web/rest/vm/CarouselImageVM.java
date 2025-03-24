package com.xcode.app.web.rest.vm;

import lombok.Data;

import java.io.Serializable;

@Data
public class CarouselImageVM implements Serializable {

    private Long id;
    private String imageUuid;
    private String linkUrl;
    private Integer sortOrder;

}
