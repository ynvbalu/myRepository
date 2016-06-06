package com.mytutorial;

public class MyServiceImplWithConstructorInjection {

  private final Processor xmlProcessor;
  private final Processor jsonProcessor;

  public MyServiceImplWithConstructorInjection(Processor xmlProcessor, Processor jsonProcessor) {
    super();
    this.xmlProcessor = xmlProcessor;
    this.jsonProcessor = jsonProcessor;
  }

  public void processXml() {
    xmlProcessor.process();
    // ....
  }

  public void processJson() {
    jsonProcessor.process();
    // ....
  }
}
