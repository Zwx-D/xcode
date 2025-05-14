package com.xcode.app.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 小程序模块
 * 摄影作品集-作品项
 */
@Data
@Entity
@Table(name = "portfolio_item")
@EqualsAndHashCode(callSuper = false)
public class PortfolioItem extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Lob
    @Column(name = "description")
    private String desc;

    @Column
    private Integer sortOrder;

    @Column
    private Boolean isShow = false;

    @Column
    private String imageUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Pportfolio_id", nullable = false)
    private Portfolio portfolio;

}
