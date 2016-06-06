package com.mytutorial;

import org.springframework.stereotype.Component;

// Processor implementations “XmlProcessor”

@Component("XmlProcessor")
public class XmlProcessor implements Processor {
  /**
   * Default Constructor.
   */
  public XmlProcessor() {
  }

  @Override
  public <T> T process() {
    // TODO .......
    System.out.println("XmlProcessor.......");
    return null;
  }
}
