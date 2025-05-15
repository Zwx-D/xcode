package com.xcode.app.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 小程序模块
 * 微信用户的收藏夹
 */
@Data
@Entity
@Table(name = "collection_folders")
@EqualsAndHashCode(callSuper = false)
public class CollectionFolder extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(name = "description")
    private String desc;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wechat_user_id", nullable = false)
    private WechatUser wechatUser;

    @OneToMany(mappedBy = "collectionFolder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CollectionItem> collectionItemList = new ArrayList<>();

}
