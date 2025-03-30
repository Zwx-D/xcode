package com.xcode.app.web.rest.filter;

import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.StringFilter;
import lombok.Data;

import java.io.Serializable;

@Data
public class BackendUserCriteria implements Serializable {

    private StringFilter username;
    private StringFilter realName;
    private StringFilter email;
    private StringFilter phone;
    private BooleanFilter enabled;
    private LocalDateTimeFilter lastLoginTime;
    private StringFilter createdBy;
    private LocalDateTimeFilter createdTime;
    private StringFilter lastModifiedBy;
    private LocalDateTimeFilter lastModifiedTime;

}
