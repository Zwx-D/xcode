package com.xcode.app.web.rest.filter;

import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import lombok.Data;

import java.io.Serializable;

@Data
public class PortfolioCriteria implements Serializable {

    private LongFilter id;

    private StringFilter imageUuid;

    private StringFilter name;

    private IntegerFilter sortOrder;

    private StringFilter createdBy;

    private LocalDateTimeFilter createdTime;

    private StringFilter lastModifiedBy;

    private LocalDateTimeFilter lastModifiedTime;

    private StringFilter uuid;

}
