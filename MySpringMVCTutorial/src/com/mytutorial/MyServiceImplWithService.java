package com.mytutorial;

import org.springframework.beans.factory.annotation.Autowired;

public class MyServiceImplWithService {

  @Autowired
  ProcessorServiceLocatorFactory locatorService;

  public void processXml() {
    Processor processor = locatorService.getProcessor("XmlProcessor");
    processor.process();
    // ......
  }

  public void processJson() {
    Processor processor = locatorService.getProcessor("JsonProcessor");
    processor.process();
  }

}
