package com.xcode.app.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 小程序模块
 * 摄影者简介
 */
@Data
@Entity
@Table(name = "photographer_info")
@EqualsAndHashCode(callSuper = false)
public class Photographer extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String imageUuid;

    @Lob
    @Column(name = "description")
    private String desc;

}
