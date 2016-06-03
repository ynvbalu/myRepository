package com.app.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import static com.app.constants.Constants.*;
import static org.apache.commons.lang3.StringUtils.*;

public class PDFBuilder extends AbstractITextPdfView {

  @Override
  protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
      HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    @SuppressWarnings("unchecked")
    List<String> listOfQueries = (List<String>) model.get("listOfQueries");
    
    document.add(new Paragraph("Queries in the log"));

    PdfPTable table = new PdfPTable(2);
    table.setWidthPercentage(100.0f);
    table.setWidths(new float[] {1.0f, 9.0f});
    table.setSpacingBefore(10);
     
    // define font for table header row
    Font font = FontFactory.getFont(FontFactory.HELVETICA);
    font.setColor(BaseColor.WHITE);
     
    // define table header cell
    PdfPCell cell = new PdfPCell();
    cell.setBackgroundColor(BaseColor.BLUE);
    cell.setPadding(2);
     
    // write table header
    cell.setPhrase(new Phrase("S.No", font));
    table.addCell(cell);
     
    cell.setPhrase(new Phrase("Query", font));
    table.addCell(cell);
     
    // write table row data
    for (int i = 0; i < listOfQueries.size(); i++) {
      String query = listOfQueries.get(i);
      query = replace(query, RED_FONT, "");
      query = replace(query, BLUE_FONT, "");
      query = replace(query, BLACK_FONT, "");
      query = replace(query, T_END, "");
        table.addCell(String.valueOf(i));
        table.addCell(query);
    }
     
    document.add(table);
  }
}
