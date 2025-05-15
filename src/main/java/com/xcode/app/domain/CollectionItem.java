package com.xcode.app.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 小程序模块
 * 微信用户的收藏夹的每一项
 */
@Data
@Entity
@Table(name = "collection_item")
@EqualsAndHashCode(callSuper = false)
public class CollectionItem  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(name = "description")
    private String desc;

    @Column
    private String imageUuid;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_folder_id", nullable = false)
    private CollectionFolder collectionFolder;


}
