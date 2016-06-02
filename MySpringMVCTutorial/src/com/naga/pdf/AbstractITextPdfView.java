package com.naga.pdf;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.OutputStream;

public abstract class AbstractITextPdfView extends AbstractView {

  public AbstractITextPdfView() {
    setContentType("application/pdf");
  }

  @Override
  protected boolean generatesDownloadContent() {
    return true;
  }

  @Override
  protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    // IE workaround: write into byte array first.
    ByteArrayOutputStream baos = createTemporaryOutputStream();

    // Apply preferences and buils metadata.
    
    Document document = newDocument();
    PdfWriter writer = newWriter(document, baos);
    prepareWriter(model, writer, request);
    buildPdfMetadata(model, document, request);
    
    //Build Pdf document
    document.open();
    buildPdfDocument(model, document, writer, request, response);
    document.close();
    
    // flush to HTTP response
    writeToResponse(response, baos);
  }

  protected Document newDocument() {
    return new Document(PageSize.A4);
  }

  protected PdfWriter newWriter(Document document, OutputStream os) throws DocumentException {
    return PdfWriter.getInstance(document, os);
  }

  protected void prepareWriter(Map<String, Object> model, PdfWriter writer, HttpServletRequest request) {
    writer.setViewerPreferences(getViewerPreferences());
  }

  protected int getViewerPreferences() {
    return PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage;
  }

  protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {

  }

  protected abstract void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
      HttpServletRequest request, HttpServletResponse response) throws Exception;

}
