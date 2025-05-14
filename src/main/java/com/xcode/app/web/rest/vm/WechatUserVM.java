package com.xcode.app.web.rest.vm;

import com.xcode.app.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class WechatUserVM extends BaseEntity implements Serializable {

    private Long id;

    private String nickName;

    private String openId;

    private String unionId;

    private LocalDateTime lastLoginTime;

    private String loginIp;

    private String os;

    private List<CollectionFolderVM> collectionFolders;

}
