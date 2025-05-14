package com.xcode.app.web.rest.dto;

import com.xcode.app.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreateCollectionFolderDTO extends BaseEntity implements Serializable {

    private String name;

    private String desc;

    private Long wechatUserId;

}
