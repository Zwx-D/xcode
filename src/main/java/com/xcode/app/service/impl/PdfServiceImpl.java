package com.xcode.app.service.impl;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.itextpdf.text.BaseColor;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import com.xcode.app.service.PdfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;

@Slf4j
@Service
public class PdfServiceImpl implements PdfService {

    private final static String FONTSPATH = "src/main/resources/fonts/NotoSansSC-VariableFont_wght.ttf";

    @Override
    public ByteArrayInputStream simpleGeneratePdf() {
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

            // 使用支持中文的字体
            BaseFont baseFont = BaseFont.createFont(FONTSPATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            for (int i = 1; i <= totalPages; i++) {
                addWatermarksToPage(pdfStamper, baseFont, watermarkText, i);
            }
            pdfStamper.close();
            pdfReader.close();
        } catch (IOException | DocumentException e) {
            log.error("Error while adding watermark to PDF", e);
        }

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    @Override
    public ByteArrayInputStream GeneratePinYinPdf(String text, String title) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();
            PdfContentByte content = writer.getDirectContent();


            BaseFont baseFont = BaseFont.createFont(FONTSPATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            String[] texts = text.split(",");
            float startX = 50;
            float startY = 750;
            float gridSize = 100;
            float margin = 10;
            float currentX = startX;
            float currentY = startY;

            // 写标题
            content.beginText();
            content.setFontAndSize(baseFont, 30);
            content.setColorFill(new Color(0, 0, 0));
            content.setTextRenderingMode(PdfContentByte.TEXT_RENDER_MODE_FILL_STROKE);
            content.showTextAligned(PdfContentByte.ALIGN_CENTER, title, document.getPageSize().getWidth() / 2, 790, 0);
            content.setLineWidth(5f);
            content.endText();

            for (String txt : texts) {
                String pinyin = PinyinHelper.convertToPinyinString(txt, " ", PinyinFormat.WITH_TONE_MARK);
                log.info("拼音组：{}", pinyin);
                String[] pinyinGroups = pinyin.split(" ");
                for (String group : pinyinGroups) {
                    addTianzige(content, baseFont, group, currentX, currentY, gridSize);
                    currentX += (gridSize / 2 + 1);
                }
                currentX += margin;
                if (currentX + gridSize > document.getPageSize().getWidth() - margin) {
                    currentX = startX;
                    currentY -= gridSize + margin;
                }
            }

            document.close();
        } catch (IOException | DocumentException | PinyinException e) {
            log.error("Error while generating pinyin grid", e);
        }

        return new ByteArrayInputStream(outputStream.toByteArray());
    }


    private void addTianzige(PdfContentByte content, BaseFont baseFont, String text, float x, float y, float size) {
//        log.info("当前文字：{}，x：{}，y：{}，size：{}", text, x, y, size);
        float halfSize = size / 2;
        // 设置田字格颜色为 #00ff80
        Color color = new Color(0, 0, 0);
        // 绘制田字格
        content.setLineWidth(1.5f);
        content.setColorStroke(color);

        // 外框
        content.rectangle(x, y - halfSize, halfSize, halfSize);
        content.stroke();

        // 设置虚线样式
        content.setLineDash(3f, 3f);

        // 横线
//        content.moveTo(x, y - halfSize);
//        content.lineTo(x + halfSize, y - halfSize);
//        content.stroke();

        // 竖线
//        content.moveTo(x + halfSize, y);
//        content.lineTo(x + halfSize, y - halfSize);
//        content.stroke();

        // 上部横线
        content.moveTo(x, y - halfSize / 2);
        content.lineTo(x + halfSize, y - halfSize / 2);
        content.stroke();

        // 左部竖线
        content.moveTo(x + halfSize / 2, y);
        content.lineTo(x + halfSize / 2, y - halfSize);
        content.stroke();

        // 重置为实线
        content.setLineDash(0);

        // 在田字格上方写入拼音
        content.beginText();
        content.setFontAndSize(baseFont, 12);
        content.setColorFill(color);
        content.showTextAligned(PdfContentByte.ALIGN_CENTER, text, x + halfSize / 2, y + 8, 0);
        content.endText();
    }

    private void addWatermarksToPage(PdfStamper pdfStamper, BaseFont baseFont, String watermarkText, int pageNumber) {
        PdfContentByte content = pdfStamper.getOverContent(pageNumber);
        Rectangle pageSize = pdfStamper.getReader().getPageSizeWithRotation(pageNumber);
        float width = pageSize.getWidth();
        float height = pageSize.getHeight();

        log.info("Processing page {}: width = {}, height = {}", pageNumber, width, height);

        float[][] positions = {
            {width / 2, height / 2},
            {width / 4, height * 3 / 4},
            {3 * width / 4, height / 4}
        };

        for (float[] position : positions) {
            addWatermark(content, baseFont, watermarkText, position[0], position[1], 45);
        }

        log.info("Added three watermarks to page {}: {}", pageNumber, watermarkText);
    }

    private void addWatermark(PdfContentByte content, BaseFont baseFont, String text, float x, float y, float angle) {
        content.beginText();
        content.setFontAndSize(baseFont, 50);
        content.setColorFill(new GrayColor(0.75f));
        content.showTextAligned(PdfContentByte.ALIGN_CENTER, text, x, y, angle);
        content.endText();
    }
}
