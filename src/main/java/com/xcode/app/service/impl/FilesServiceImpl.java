package com.xcode.app.service.impl;

import com.xcode.app.domain.FileInfo;
import com.xcode.app.domain.FolderInfo;
import com.xcode.app.repository.FileInfoRepository;
import com.xcode.app.repository.FolderInfoRepository;
import com.xcode.app.service.FilesSerivce;
import com.xcode.app.web.rest.vm.FilesVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class FilesServiceImpl implements FilesSerivce {

    @Value("${upload.root.directory}")
    private String uploadRootDir;

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private FolderInfoRepository folderInfoRepository;

    @Override
    public FilesVM uploadImage(MultipartFile file) {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        String directoryPath = uploadRootDir + File.separator + currentDate;
        FileInfo fileInfo = saveFilesRecord(currentDate, directoryPath, fileName);
        String downloadUrl = saveFiles(file, currentDate, directoryPath, fileName);
        return FilesVM.builder()
            .downloadUrl(downloadUrl)
            .filePath(fileInfo.getFilePath())
            .folder(fileInfo.getFolder())
            .name(fileInfo.getName())
            .id(fileInfo.getId())
            .uuid(fileInfo.getUuid())
            .build();
    }

    @Override
    @Transactional
    public FileInfo saveFilesRecord(String currentDate, String directoryPath, String fileName) {
        Optional<FolderInfo> folderInfoOptional = folderInfoRepository.findOneByNameAndParentIsNull(currentDate);
        FolderInfo folderInfo;
        if (folderInfoOptional.isPresent()) {
            folderInfo = folderInfoOptional.get();
        } else {
            folderInfo = new FolderInfo();
            folderInfo.setName(currentDate);
            folderInfo = folderInfoRepository.save(folderInfo);
        }

        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(fileName);
        fileInfo.setFolder(folderInfo);
        fileInfo.setFilePath(Paths.get(directoryPath, fileName).toString());
        log.info("文件:{}", fileInfo);
        FileInfo save = fileInfoRepository.save(fileInfo);
        return save;
    }

    @Override
    public ResponseEntity<Resource> downloadImage(String uuid, Boolean isDownload) {
        FileInfo fileInfo = Optional.ofNullable(fileInfoRepository.findOneByUuid(uuid))
            .map(Optional::get)
            .orElseThrow(() -> {
                throw new RuntimeException("找不到对应文件");
            });
        try {
            // Load file as Resource
            Path filePath = Paths.get("").resolve(fileInfo.getFilePath()).normalize();
            log.info("实际文件路径:{}", filePath.toUri());
            Resource resource = new UrlResource(filePath.toUri());
            // Check if file exists
            if (resource.exists()) {
                // Set content type and attachment disposition
                String contentType = Files.probeContentType(filePath);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType(contentType));
                if (isDownload) {
                    headers.setContentDispositionFormData("attachment", fileInfo.getName());
                }

                return new ResponseEntity<>(resource, headers, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private String saveFiles(MultipartFile file, String currentDate, String directoryPath, String fileName) {
        log.info("保存文件：currentDate:{},directoryPath:{},fileName:{}", currentDate, directoryPath, fileName);
        if (file.isEmpty()) {
            throw new RuntimeException("请上传文件");
        }
        try {
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            Files.copy(file.getInputStream(), Paths.get(directoryPath).resolve(fileName));
            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/download/")
                .path(currentDate + "/")
                .path(fileName)
                .toUriString();
            return downloadUrl;
        } catch (IOException e) {
            throw new RuntimeException("上传文件异常：" + e.getMessage());
        }
    }

}
