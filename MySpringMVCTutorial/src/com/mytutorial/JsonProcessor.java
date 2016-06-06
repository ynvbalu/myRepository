package com.mytutorial;

import org.springframework.stereotype.Component;

//Processor implementations “JsonProcessor”

@Component("JsonProcessor")
public class JsonProcessor implements Processor {

  @Override
  public <T> T process() {
    // ......TODO 
    
    System.out.println("jsonProcessor.........");
    return null;
  }
}
