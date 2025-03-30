package com.xcode.app.web.rest.vm;

import com.xcode.app.domain.FolderInfo;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class FilesVM implements Serializable {

    private String downloadUrl;

    private Long id;

    private String name;

    private String filePath;

    private FolderInfo folder;

    private String uuid;

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
