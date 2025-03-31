package com.xcode.app.web.rest.filter;

import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import lombok.Data;

import java.io.Serializable;

@Data
public class PhotographyCriteria implements Serializable {

    private LongFilter id;

    private StringFilter imageUuid;

    private IntegerFilter sortOrder;

    private StringFilter desc;

    private StringFilter createdBy;

    private LocalDateTimeFilter createdTime;

    private StringFilter lastModifiedBy;

    private LocalDateTimeFilter lastModifiedTime;

    private StringFilter uuid;

}
