package com.xcode.app.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 文件夹信息
 */
@Data
@Entity
@Table(name = "folder_info")
@EqualsAndHashCode(callSuper=false)
public class FolderInfo  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private FolderInfo parent;

    @ToString.Exclude
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<FolderInfo> subFolders;

    @ToString.Exclude
    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL)
    private List<FileInfo> files;

}
