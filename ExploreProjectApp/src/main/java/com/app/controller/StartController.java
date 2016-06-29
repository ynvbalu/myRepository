package com.app.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartController {
  @Value("${application.message:Hello World}")
  private String message = "Hello World";

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
}
