package com.xcode.app.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 小程序模块
 * 微信用户信息
 */
@Data
@Entity
@Table(name = "wechat_user")
@EqualsAndHashCode(callSuper = false)
public class WechatUser extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickName;

    @Column
    private String openId;

    @Column
    private String unionId;

    @Column
    private LocalDateTime lastLoginTime;

    @Column
    private String loginIp;

    @Column
    private String os;

    @OneToMany(mappedBy = "wechatUser", cascade = CascadeType.ALL)
    private List<CollectionFolder> collectionFolders;

}
