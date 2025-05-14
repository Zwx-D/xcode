package com.xcode.app.web.rest.vm;

import com.xcode.app.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PortfolioVM extends BaseEntity implements Serializable {

    private Long id;

    private String name;

    private String imageUuid;

    private String desc;

    private Integer sortOrder;

    private Boolean isShow = false;

    private List<PortfolioItemVM> portfolioItemList;
}
