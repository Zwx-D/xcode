package com.xcode.app.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 文件信息
 */
@Data
@Entity
@Table(name = "file_info")
@EqualsAndHashCode(callSuper=false)
public class FileInfo extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String filePath;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "folder_id")
    private FolderInfo folder;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public FolderInfo getFolder() {
        return folder;
    }

    public void setFolder(FolderInfo folder) {
        this.folder = folder;
    }
}
