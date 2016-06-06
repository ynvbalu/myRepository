package com.mytutorial;

import org.springframework.stereotype.Service;

@Service
public class ProcessorServiceLocatorFactory {
  
  public Processor getProcessor(String processorName) {
    // lookup dynamically via JNDI or ohter Map based registries to map dependencies
    if("XmlProcessor".equalsIgnoreCase(processorName)) {
      return new XmlProcessor();
    }
    return new JsonProcessor();
  }
  
}
