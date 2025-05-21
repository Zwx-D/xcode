package com.xcode.app.web.rest.vm;

import com.xcode.app.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class PortfolioTypeTagVM extends BaseEntity implements Serializable {

    private Long id;

    private String name;

    private String desc;

}
