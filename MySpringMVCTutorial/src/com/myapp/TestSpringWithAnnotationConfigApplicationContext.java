package com.myapp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestSpringWithAnnotationConfigApplicationContext {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    ctx.register(AppConfig.class);
    ctx.refresh();

    MyAppBean myApp = ctx.getBean(MyAppBean.class);
    System.out.println(myApp.sayHello());

    ctx.close();
  }
}
