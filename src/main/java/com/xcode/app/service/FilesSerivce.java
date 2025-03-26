package com.xcode.app.service;

import com.xcode.app.domain.FileInfo;
import com.xcode.app.web.rest.vm.FilesVM;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FilesSerivce {

    FilesVM uploadImage(MultipartFile file);

    FileInfo saveFilesRecord(String currentDate, String directoryPath, String fileName);

    ResponseEntity<Resource> downloadImage(String uuid, Boolean isDownload);

}
