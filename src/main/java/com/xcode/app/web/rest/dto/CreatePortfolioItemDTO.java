package com.xcode.app.web.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreatePortfolioItemDTO implements Serializable {

    private String name;

    private String desc;

    private Integer sortOrder;

    private Boolean isShow = false;

    private String imageUuid;

    private String portfolioUuid;

}
