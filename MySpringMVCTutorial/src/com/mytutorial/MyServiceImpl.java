package com.mytutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

// Service class MyServiceImpl

public class MyServiceImpl {

  @Autowired
  @Qualifier("XmlProcessor")
  Processor xmlProcessor; // code to interface - DON'T DO "XmlPrcessor xmlProcessor" that violates DIP

  @Autowired
  @Qualifier("JsonProcessor")
  Processor jsonProcessor; // code to interface - DONT'T DO "JsonProcessor jsonPrcessor" that violates DIP

  public void processXml() {
    xmlProcessor.process();
    // ......
  }

  public void processJson() {
    jsonProcessor.process();
    // ......
  }

}
