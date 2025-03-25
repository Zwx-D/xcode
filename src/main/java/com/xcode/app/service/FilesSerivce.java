package com.xcode.app.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FilesSerivce {

    void uploadImage(MultipartFile file);

    void saveFilesRecord(String currentDate, String directoryPath, String fileName);

    ResponseEntity<Resource> downloadImage(String uuid);

}
