package com.app.controller;

import static com.app.constants.Constants.*;
import static org.apache.commons.lang3.StringUtils.contains;
import static org.apache.commons.lang3.StringUtils.join;

import java.util.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.app.service.QueryService;

@Controller
public class QueryController {

  @Value("${application.message:Hello World}")
  private String message = "Hello World";

  @Inject
  private QueryService queryService;

  @RequestMapping("/")
  public String welcome(Map<String, Object> model) {
    model.put("time", new Date());
    model.put("message", this.message);
    return "welcome";
  }

  @RequestMapping("/foo")
  public String foo() {
    throw new RuntimeException("Foo");
  }

  @RequestMapping("/test")
  public String test(Map<String, Object> model) {
    model.put("time", new Date());
    model.put("message", this.message);
    return "test";
  }

  @RequestMapping("/queries")
  public String queries() {
    return "queries";
  }

  @RequestMapping("/log/{name}")
  public String readQueriesAndBindingValuesFromLogFile(@PathVariable String name, Map<String, Object> model,
      HttpServletRequest request) throws Exception {
    String location = null;
    Integer readFromThatLine = 0;
    List<String> finalOutput = null;

    HttpSession httpSession = request.getSession();
    String BASE_LOCATION = LOG_LOC_1;

    if (contains(name, "log4j")) {
      BASE_LOCATION = LOG_LOC_2;
      readFromThatLine = 0;
    }

    if (contains(name, LOG)) {
      location = join(BASE_LOCATION, name);
    } else {
      location = join(BASE_LOCATION, name, LOG);
    }

    if (!contains(name, "log4j")) {
      Integer numberOfLines = (Integer) httpSession.getAttribute("numberOfLines");
      if (numberOfLines == null) {
        readFromThatLine = 0;
        httpSession.setAttribute("numberOfLines", queryService.getNumberOfLinesInTheLog(location));
      } else {
        readFromThatLine = numberOfLines;
        httpSession.setAttribute("numberOfLines", queryService.getNumberOfLinesInTheLog(location));
      }
    }

    finalOutput = queryService.getFormattedQueriesWithBindingValues(location, readFromThatLine);
    model.put("finalOutput", finalOutput);
    // finalOutput.forEach(line -> System.out.println(line));
    return "log_temp1";
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

  @Value("${property.one}")
  public String propertyOne;

  @Value("${property.two}")
  public String propertyTwo;

  @Value("${property.three}")
  private String propertyThree;

  @PostConstruct
  public void postConstruct() {
    System.out.println("Property One: " + propertyOne);
    System.out.println("Property Two: " + propertyTwo);
    System.out.println("Property Three: " + propertyThree);
    // System.out.println("location: " + loc);
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

  @RequestMapping("/ajax")
  public ModelAndView sayHello() {
    return new ModelAndView("ajax", "message", "Spring MVC with Ajax and JQuery Demo..");
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
}
