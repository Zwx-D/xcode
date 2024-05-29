package com.xcode.app.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public interface PdfService {

    ByteArrayInputStream simpleGeneratePdf();

    ByteArrayInputStream addWatermark(InputStream pdfInputStream, String watermarkText);
}
