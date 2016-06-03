package com.app.excel;

import static com.app.constants.Constants.*;
import static org.apache.commons.lang3.StringUtils.replace;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractJExcelView;

import jxl.write.*;

public class ExcelBuilder extends AbstractJExcelView {

  @Override
  protected void buildExcelDocument(Map<String, Object> model, WritableWorkbook workbook, HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    // get data model which is passed by the Spring container
    @SuppressWarnings("unchecked")
    List<String> listOfQueries = (List<String>) model.get("listOfQueries");

    // create a new Excel sheet
    WritableSheet sheet = workbook.createSheet("Log Queries", 0);

    // create header row
    sheet.addCell(new Label(0, 0, "S No"));
    sheet.addCell(new Label(1, 0, "Query"));

    // create data rows
    int rowCount = 1;

    for (int i = 0; i < listOfQueries.size(); i++) {
      String query = listOfQueries.get(i);
      query = replace(query, RED_FONT, "");
      query = replace(query, BLUE_FONT, "");
      query = replace(query, BLACK_FONT, "");
      query = replace(query, T_END, "");
      sheet.addCell(new Label(0, rowCount, String.valueOf(i)));
      sheet.addCell(new Label(1, rowCount, query));

      rowCount++;
    }
  }
}