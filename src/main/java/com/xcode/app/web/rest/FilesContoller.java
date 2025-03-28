package com.xcode.app.web.rest;

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RestController
@RequestMapping("/api/files")
public class FilesContoller {

    private static final String UPLOAD_DIR = "uploads";

    @Value("${upload.root.directory}")
    private String uploadRootDir;

    @Autowired
    private FilesSerivce filesSerivce;

    @PostMapping("/upload")
    public ResponseEntity<FilesVM> uploadImage(@RequestParam("file") MultipartFile file) {
        FilesVM filesVM = filesSerivce.uploadImage(file);
        return ResponseEntity.ok(filesVM);
    }


    @GetMapping("/download/{uuid}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String uuid, @RequestParam("isDownload") Boolean isDownload) {
        return filesSerivce.downloadImage(uuid, isDownload);
    }

//    @PostMapping("/img/compress")
//    public ResponseEntity<byte[]> compressImage(@RequestParam("file") MultipartFile file) {
//        try {
//            // 检查是否为jpg或png文件
//            String contentType = file.getContentType();
//            if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
//                return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
//            }
//
//            // 调用Thumbnailator压缩图片
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            Thumbnails.of(file.getInputStream())
//                .size(1024, 1024)  // 调整大小，如果不想改变分辨率，可以保留原始大小
//                .outputFormat("jpg") // 输出为jpg格式
//                .outputQuality(1.0) // 初始质量，稍后进一步调整
//                .toOutputStream(outputStream);
//
//            // 压缩到100KB以下
//            double quality = 1.0;
//            while (outputStream.toByteArray().length > 100 * 1024 && quality > 0) {
//                outputStream.reset();
//                quality -= 0.05;
//                Thumbnails.of(file.getInputStream())
//                    .size(1024, 1024)
//                    .outputFormat("jpg")
//                    .outputQuality(quality)
//                    .toOutputStream(outputStream);
//            }
//
//            return new ResponseEntity<>(outputStream.toByteArray(), HttpStatus.OK);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


}
