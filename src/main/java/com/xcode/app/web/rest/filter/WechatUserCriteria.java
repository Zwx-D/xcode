package com.xcode.app.web.rest.filter;

import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import lombok.Data;

import java.io.Serializable;

@Data
public class WechatUserCriteria implements Serializable {

    private LongFilter id;

    private StringFilter nickName;

    private StringFilter openId;

    private StringFilter unionId;

    private StringFilter createdBy;

    private LocalDateTimeFilter lastLoginTime;

    private LocalDateTimeFilter createdTime;

    private StringFilter lastModifiedBy;

    private LocalDateTimeFilter lastModifiedTime;

    private StringFilter uuid;

}
