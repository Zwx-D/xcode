package com.xcode.app.web.rest.filter;

import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import lombok.Data;

import java.io.Serializable;

@Data
public class FolderCriteria implements Serializable {

    private LongFilter id;
    private StringFilter uuid;
    private StringFilter name;
    private StringFilter createdBy;
    private LocalDateTimeFilter createdTime;
    private StringFilter lastModifiedBy;
    private LocalDateTimeFilter lastModifiedTime;

}
