package com.mytutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MyServiceImplWithSetter {
  private Processor xmlProcessor;
  private Processor jsonProcessor;

  public void processXml() {
    xmlProcessor.process();
  }

  public void processJson() {
    jsonProcessor.process();
  }

  @Autowired
  @Qualifier("XmlProcessor")
  public void setXmlProcessor(Processor xmlProcessor) {
    this.xmlProcessor = xmlProcessor;
  }

  @Autowired
  @Qualifier("JsonProcessor")
  public void setJsonProcessor(Processor jsonProcessor) {
    this.jsonProcessor = jsonProcessor;
  }

}
