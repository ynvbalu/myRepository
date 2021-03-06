package com.app.controller;

import static com.app.constants.Constants.LOG;
import static com.app.constants.Constants.LOG_LOC_1;
import static org.apache.commons.lang3.StringUtils.contains;
import static org.apache.commons.lang3.StringUtils.join;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.inject.Inject;
import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.app.constants.UuidGenerator;
import com.app.excel.ExcelBuilder;
import com.app.pdf.PDFBuilder;
import com.app.service.QueryService;

@Controller
public class QueryController {

  @SuppressWarnings("unused")
  private DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

  @Inject
  private QueryService queryService;

  @RequestMapping("/queries")
  public String queries() {
    return "queries";
  }

  @RequestMapping("/log")
  public String readQueriesAndBindingValuesFromLogFile(@RequestParam String name, Map<String, Object> model,
      HttpServletRequest request) throws Exception {
    model.put("name", name);
    List<String> finalOutput = getQueriesFromLogFile(name, request);
    model.put("finalOutput", finalOutput);
    // finalOutput.forEach(line -> System.out.println(line));
    return "log_temp1";
  }

  /**
   * @param name log filename
   * @param request request
   * @return list of queries
   * @throws IOException IOException
   */
  private List<String> getQueriesFromLogFile(String name, HttpServletRequest request) throws IOException {
    String location = null;
    Integer readFromThatLine = 0;
    List<String> finalOutput = null;

    HttpSession httpSession = request.getSession();
    String BASE_LOCATION = LOG_LOC_1;

    if (contains(name, LOG)) {
      location = join(BASE_LOCATION, name);
    } else {
      location = join(BASE_LOCATION, name, LOG);
    }

    Integer numberOfLines = (Integer) httpSession.getAttribute("numberOfLines");
    if (numberOfLines == null) {
      readFromThatLine = 0;
      httpSession.setAttribute("numberOfLines", queryService.getNumberOfLinesInTheLog(location));
    } else {
      readFromThatLine = numberOfLines;
      httpSession.setAttribute("numberOfLines", queryService.getNumberOfLinesInTheLog(location));
    }

    finalOutput = queryService.getFormattedQueriesWithBindingValues(location, readFromThatLine);
    httpSession.setAttribute("finalOutput", finalOutput);

    return finalOutput;
  }

  @SuppressWarnings("unchecked")
  @RequestMapping("/startup/{name}")
  public String startUp(@PathVariable String name, Map<String, Object> model, HttpServletRequest request)
      throws Exception {
    HttpSession httpSession = request.getSession();
    String location = null;
    String BASE_LOCATION = LOG_LOC_1;

    if (contains(name, LOG)) {
      location = join(BASE_LOCATION, name);
    } else {
      location = join(BASE_LOCATION, name, LOG);
    }

    List<String> finalOutput = null;
    Object attribute = httpSession.getAttribute("finalOutput");
    if (attribute == null) {
      finalOutput = queryService.getStartUpQueries(location);
      httpSession.setAttribute("finalOutput", finalOutput);
      // finalOutput.forEach(line -> System.out.println(line));
    } else {
      finalOutput = (List<String>) attribute;
    }
    model.put("finalOutput", finalOutput);

    return "startup";
  }

  @SuppressWarnings("unchecked")
  @RequestMapping("/qt")
  public String queryTemplates(Map<String, Object> model, HttpServletRequest request) throws Exception {
    HttpSession httpSession = request.getSession();

    List<String> qt = null;
    Object attribute = httpSession.getAttribute("qt");
    if (attribute == null) {
      qt = queryService.getQueryTemplates();
      httpSession.setAttribute("qt", qt);
      qt.forEach(line -> System.out.println(line));
    } else {
      qt = (List<String>) attribute;
    }

    model.put("qt", qt);

    return "qt";
  }

  @RequestMapping("/find/{name}/{searchStr}")
  public String find(@PathVariable String name, @PathVariable String searchStr, Map<String, Object> model)
      throws Exception {
    String location = null;
    String BASE_LOCATION = LOG_LOC_1;

    if (contains(name, LOG)) {
      location = join(BASE_LOCATION, name);
    } else {
      location = join(BASE_LOCATION, name, LOG);
    }

    List<String> finalOutput = null;
    finalOutput = queryService.getLinesBySearchString(location, searchStr);

    model.put("searchStr", searchStr);
    model.put("finalOutput", finalOutput);

    return "search";
  }

  @RequestMapping("/error/{name}")
  public String findErrors(@PathVariable String name, Map<String, Object> model) throws Exception {
    String location = null;
    String BASE_LOCATION = LOG_LOC_1;

    if (contains(name, LOG)) {
      location = join(BASE_LOCATION, name);
    } else {
      location = join(BASE_LOCATION, name, LOG);
    }

    List<String> finalOutput = null;
    finalOutput = queryService.getErrorsOrExceptions(location);

    model.put("finalOutput", finalOutput);

    return "error";
  }

  @RequestMapping(value = "/log/ajax/{name}", method = RequestMethod.GET)
  public @ResponseBody List<String> readQueriesAndBindingValuesFromLogFileWithAjax(@PathVariable String name)
      throws Exception {
    String location = null;
    Integer readFromThatLine = 0;
    List<String> finalOutput = null;

    String BASE_LOCATION = LOG_LOC_1;

    if (contains(name, LOG)) {
      location = join(BASE_LOCATION, name);
    } else {
      location = join(BASE_LOCATION, name, LOG);
    }
    finalOutput = queryService.getFormattedQueriesWithBindingValues(location, readFromThatLine);

    return finalOutput;
  }

  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/downloadExcel/{name}", method = RequestMethod.GET)
  public ModelAndView downloadExcel(@PathVariable String name, HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    List<String> existing = new ArrayList<>();
    Object attribute = request.getSession().getAttribute("finalOutput");

    if (attribute != null) {
      existing = (List<String>) attribute;
    }

    List<String> listOfQueries = getQueriesFromLogFile(name, request);

    if (listOfQueries != null) {
      listOfQueries.addAll(existing);
    }
    // return a view which will be resolved by an excel view resolver
    response.setContentType("application/ms-excel");
    //String fileName = "queries_" + df.format(new Date()) + ".xls";
    String fileName = UuidGenerator.fromHostAndCurrentTime().toString()+ ".xls";
    response.setHeader("Content-disposition", "attachment; filename=" + fileName);
    return new ModelAndView(new ExcelBuilder(), "listOfQueries", listOfQueries);
  }

  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/downloadPDF/{name}", method = RequestMethod.GET)
  public ModelAndView downloadPDF(@PathVariable String name, HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    List<String> existing = new ArrayList<>();
    Object attribute = request.getSession().getAttribute("finalOutput");

    if (attribute != null) {
      existing = (List<String>) attribute;
    }

    List<String> listOfQueries = getQueriesFromLogFile(name, request);

    if (listOfQueries != null) {
      listOfQueries.addAll(existing);
    }
    // return a view which will be resolved by an excel view resolver
    response.setContentType("application/pdf");
    //String fileName = "queries_" + df.format(new Date()) + ".pdf";
    String fileName = UuidGenerator.fromHostAndCurrentTime().toString()+ ".pdf";

    response.setHeader("Content-disposition", "attachment; filename=" + fileName);
    return new ModelAndView(new PDFBuilder(), "listOfQueries", listOfQueries);
  }

}
