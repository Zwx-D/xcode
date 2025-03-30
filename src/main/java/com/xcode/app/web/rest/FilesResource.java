package com.xcode.app.web.rest;

import com.xcode.app.service.FileInfoQueryService;
import com.xcode.app.service.FilesSerivce;
import com.xcode.app.service.FolderInfoQueryService;
import com.xcode.app.util.HeaderUtils;
import com.xcode.app.web.rest.api.FilesApi;
import com.xcode.app.web.rest.filter.FilesCriteria;
import com.xcode.app.web.rest.filter.FolderCriteria;
import com.xcode.app.web.rest.vm.FileInfoVM;
import com.xcode.app.web.rest.vm.FilesVM;
import com.xcode.app.web.rest.vm.FolderVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/files")
public class FilesResource implements FilesApi {

    @Autowired
    private FilesSerivce filesSerivce;

    @Autowired
    private FileInfoQueryService fileInfoQueryService;

    @Autowired
    private FolderInfoQueryService folderInfoQueryService;

    @Override
    public ResponseEntity<FilesVM> uploadImage(@RequestParam("file") MultipartFile file) {
        FilesVM filesVM = filesSerivce.uploadImage(file);
        return ResponseEntity.ok(filesVM);
    }

    @Override
    public ResponseEntity<Resource> downloadImage(@PathVariable String uuid, @RequestParam("isDownload") Boolean isDownload) {
        return filesSerivce.downloadImage(uuid, isDownload);
    }

    @Override
    public ResponseEntity<List<FileInfoVM>> findFileInfoByCriteria(FilesCriteria criteria, Pageable pageable) {
        Page<FileInfoVM> byCriteria = fileInfoQueryService.findByCriteria(criteria, pageable);
        return ResponseEntity.ok()
            .headers(HeaderUtils.createHeadersWithTotalCount(byCriteria))
            .body(byCriteria.getContent());
    }

    @Override
    public ResponseEntity<List<FolderVM>> findFolderByCriteria(FolderCriteria criteria, Pageable pageable) {
        Page<FolderVM> byCriteria = folderInfoQueryService.findByCriteria(criteria, pageable);
        return ResponseEntity.ok()
            .headers(HeaderUtils.createHeadersWithTotalCount(byCriteria))
            .body(byCriteria.getContent());
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
