package com.naga.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyHelloWorld {

  @RequestMapping("/welcome")
  public ModelAndView helloWorld() {

    String message = "<br><div style='text-align:center;'>" +
        "<h3>********** Hello World, Spring MVC Tutorial</h3>" +
        "This message is coming from MyHelloWorld.java **********</div><br><br>";

    return new ModelAndView("welcome", "message", message);
  }
  
  @RequestMapping("/introduction-to-spring-boot")
  public ModelAndView test3() {
    return new ModelAndView("introduction-to-spring-boot");
  }
}
