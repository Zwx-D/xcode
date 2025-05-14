package com.xcode.app.web.rest.vm;

import com.xcode.app.domain.BaseEntity;
import com.xcode.app.domain.Portfolio;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class PortfolioItemVM extends BaseEntity implements Serializable {

    private Long id;

    private String name;

    private String desc;

    private Integer sortOrder;

    private Boolean isShow = false;

    private String imageUuid;

}
