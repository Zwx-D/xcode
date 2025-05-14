package com.xcode.app.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 小程序模块
 * 摄影作品集
 */
@Data
@Entity
@Table(name = "portfolio_info")
@EqualsAndHashCode(callSuper = false)
public class Portfolio extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
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

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PortfolioItem> portfolioItemList;
}
