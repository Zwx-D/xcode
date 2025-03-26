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


}
