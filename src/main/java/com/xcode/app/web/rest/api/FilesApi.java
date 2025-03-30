package com.xcode.app.web.rest.api;

import com.xcode.app.web.rest.filter.FilesCriteria;
import com.xcode.app.web.rest.filter.FolderCriteria;
import com.xcode.app.web.rest.vm.FileInfoVM;
import com.xcode.app.web.rest.vm.FilesVM;
import com.xcode.app.web.rest.vm.FolderVM;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FilesApi {

    @PostMapping("/upload")
    ResponseEntity<FilesVM> uploadImage(@RequestParam("file") MultipartFile file);

    @GetMapping("/download/{uuid}")
    ResponseEntity<Resource> downloadImage(@PathVariable String uuid, @RequestParam("isDownload") Boolean isDownload);

    @GetMapping("/findFileInfoByCriteria")
    ResponseEntity<List<FileInfoVM>> findFileInfoByCriteria(@SpringQueryMap FilesCriteria criteria,
                                            @PageableDefault(page = 0,size = 10) Pageable pageable);

    @GetMapping("/findFolderByCriteria")
    ResponseEntity<List<FolderVM>> findFolderByCriteria(@SpringQueryMap FolderCriteria criteria,
                                        @PageableDefault(page = 0,size = 10) Pageable pageable);

}
