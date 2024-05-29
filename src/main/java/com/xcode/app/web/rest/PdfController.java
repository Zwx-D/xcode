package com.xcode.app.web.rest;

import com.xcode.app.service.PdfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    private HttpHeaders headers;

    @PostConstruct
    public void init() {
        headers = new HttpHeaders();
    }

    @GetMapping("/simpleGeneratePdf")
    public ResponseEntity<InputStreamResource> simpleGeneratePdf() {
        ByteArrayInputStream bis = pdfService.simpleGeneratePdf();
        headers.add("Content-Disposition", "inline; filename=simple.pdf");
        return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(bis));
    }

    @PostMapping("/add-watermark")
    public ResponseEntity<InputStreamResource> addWatermarkToPdf(
        @RequestParam("file") MultipartFile file,
        @RequestParam("watermark") String watermark) {
        log.info("file入参大小：{}，水印文字：{}", file.getSize(), watermark);
        try {
            ByteArrayInputStream watermarkedPdf = pdfService.addWatermark(file.getInputStream(), watermark);
            headers.add("Content-Disposition", "inline; filename=watermarked.pdf");

            return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(watermarkedPdf));
        } catch (IOException e) {
            log.error("Error reading input file", e);
            return ResponseEntity.status(500).build();
        }
    }

}
