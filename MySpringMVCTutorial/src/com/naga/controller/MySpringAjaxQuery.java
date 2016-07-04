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
  
  @RequestMapping("/cj")
  public ModelAndView showCoreJava() {
    return new ModelAndView("corejava-1");
  }
  
  @RequestMapping("/j8")
  public ModelAndView java_8_transforming_thinking_oop_fop_java_8_examples() {
    return new ModelAndView("java-8-transforming-thinking-oop-fop-java-8-examples");
  }
  
  @RequestMapping("/jee1")
  public ModelAndView how_to_go_about_designing_a_medium_size_jee_application() {
    return new ModelAndView("how-to-go-about-designing-a-medium-size-jee-application");
  }
  
  @RequestMapping("/tool")
  public ModelAndView java_tools_remember() {
    return new ModelAndView("java-tools-remember");
  }
  
  @RequestMapping("/sqa")
  public ModelAndView testing_qa_spring() {
    return new ModelAndView("testing_qa_spring");
  }
  
  @RequestMapping("/lamda")
  public ModelAndView java_8_streams_lambdas_intermediate_vs_terminal_ops_lazy_loading_simple_examples() {
    return new ModelAndView("java-8-streams-lambdas-intermediate-vs-terminal-ops-lazy-loading-simple-examples");
  }
  
  @RequestMapping("/hashmap-and-how-it-works")
  public ModelAndView test1() {
    return new ModelAndView("hashmap-and-how-it-works");
  }
  
  @RequestMapping("/finding-the-missing-numbers-java-example")
  public ModelAndView test2() {
    return new ModelAndView("finding-the-missing-numbers-java-example");
  }
  
  @RequestMapping("/jee-overview-interview-questions-and-answers")
  public ModelAndView test3() {
    return new ModelAndView("jee-overview-interview-questions-and-answers");
  }
}
