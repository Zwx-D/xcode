package com.xcode.app.service.impl;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import com.xcode.app.service.PdfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;

@Slf4j
@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public ByteArrayInputStream  simpleGeneratePdf() {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("Hello, this is a simple PDF document created with OpenPDF!"));
            document.close();
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public ByteArrayInputStream addWatermark(InputStream pdfInputStream, String watermarkText) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PdfReader pdfReader = new PdfReader(pdfInputStream);
            PdfStamper pdfStamper = new PdfStamper(pdfReader, outputStream);
            int totalPages = pdfReader.getNumberOfPages();

            PdfContentByte content;
            BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED);

            for (int i = 1; i <= totalPages; i++) {
                Rectangle pageSize = pdfReader.getPageSizeWithRotation(i);
                float width = pageSize.getWidth();
                float height = pageSize.getHeight();
                log.info("Processing page {}: width = {}, height = {}", i, width, height);

                content = pdfStamper.getOverContent(i); // 使用 getOverContent 方法
                content.beginText();
                content.setFontAndSize(baseFont, 50); // 设置字体和大小
                content.setColorFill(new GrayColor(0.75f)); // 设置颜色
                content.showTextAligned(
                    PdfContentByte.ALIGN_CENTER, // 对齐方式
                    watermarkText, // 水印文本
                    width / 2, // x 坐标
                    height / 2, // y 坐标
                    45 // 旋转角度
                );
                content.endText();
                log.info("Added watermark to page {}: {}", i, watermarkText);
            }
            pdfStamper.close();
            pdfReader.close();
        } catch (IOException | DocumentException e) {
            log.error("Error while adding watermark to PDF", e);
        }

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

}
