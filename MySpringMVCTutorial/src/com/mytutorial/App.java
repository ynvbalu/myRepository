package com.mytutorial;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// Standalone App to execute

public class App {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    ctx.register(AppConfig.class);
    ctx.refresh();
    MyServiceImpl bean = ctx.getBean(MyServiceImpl.class);
    bean.processXml();
    bean.processJson();
    ctx.close();
  }
}
