package com.naga.controller;

import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MySpringAjaxQuery {

  @RequestMapping("/ajax")
  public ModelAndView sayHello() {
    return new ModelAndView("ajax", "message", "Spring MVC with Ajax and JQuery Demo..");
  }

  @RequestMapping(value = "/ajaxtest", method = RequestMethod.GET)
  public @ResponseBody String getTime() {

    Random random = new Random();
    float r = random.nextFloat() * 100;
    String result = "<br>Next Random # is <b>" + r + "</b>. Generated on <b>" + new Date().toString() + "</b>";
    System.out.println("Debug Message from " + this + " Controller.." + new Date().toString());
    
    return result;
  }
  
  @RequestMapping("/show")
  public ModelAndView showHideJquery() {
    return new ModelAndView("show_hide_jquery");
  }
  
  @RequestMapping("/spring")
  public ModelAndView showInterview() {
    return new ModelAndView("spring-1");
  }

}
