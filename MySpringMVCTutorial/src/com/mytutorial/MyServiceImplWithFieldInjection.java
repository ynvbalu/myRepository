package com.mytutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MyServiceImplWithFieldInjection {

  @Autowired
  @Qualifier("XmlProcessor")
  Processor xmlProcessor;

  public void processXml() {
    xmlProcessor.process();
  }

}
