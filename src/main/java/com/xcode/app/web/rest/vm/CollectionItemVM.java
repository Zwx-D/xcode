package com.xcode.app.web.rest.vm;

import com.xcode.app.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 小程序模块
 * 微信用户的收藏夹的每一项
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class CollectionItemVM extends BaseEntity implements Serializable {

    private Long id;

    private String name;

    private String desc;

    private String imageUuid;

}
