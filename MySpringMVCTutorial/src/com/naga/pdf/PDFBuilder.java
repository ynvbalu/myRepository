package com.naga.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class PDFBuilder extends AbstractITextPdfView {

  @Override
  protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
      HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    @SuppressWarnings("unchecked")
    List<Book> listOfBooks = (List<Book>) model.get("books");
    
    document.add(new Paragraph("Testing purpose"));

    PdfPTable table = new PdfPTable(5);
    table.setWidthPercentage(100.0f);
    table.setWidths(new float[] {3.0f, 2.0f, 2.0f, 2.0f, 1.0f});
    table.setSpacingBefore(10);
     
    // define font for table header row
    Font font = FontFactory.getFont(FontFactory.HELVETICA);
    font.setColor(BaseColor.WHITE);
     
    // define table header cell
    PdfPCell cell = new PdfPCell();
    cell.setBackgroundColor(BaseColor.BLUE);
    cell.setPadding(5);
     
    // write table header
    cell.setPhrase(new Phrase("Book Title", font));
    table.addCell(cell);
     
    cell.setPhrase(new Phrase("Author", font));
    table.addCell(cell);

    cell.setPhrase(new Phrase("ISBN", font));
    table.addCell(cell);
     
    cell.setPhrase(new Phrase("Published Date", font));
    table.addCell(cell);
     
    cell.setPhrase(new Phrase("Price", font));
    table.addCell(cell);
     
    // write table row data
    for (Book aBook : listOfBooks) {
        table.addCell(aBook.getTitle());
        table.addCell(aBook.getAuthor());
        table.addCell(aBook.getIsbn());
        table.addCell(aBook.getPublishedDate());
        table.addCell(String.valueOf(aBook.getPrice()));
    }
     
    document.add(table);
    
  }
  
  
  
}
