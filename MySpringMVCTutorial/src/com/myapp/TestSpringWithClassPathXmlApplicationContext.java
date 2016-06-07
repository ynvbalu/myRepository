package com.myapp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringWithClassPathXmlApplicationContext {
  public static void main(String[] args) {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
        new String[] {"com/myapp/myApp-applicationContext.xml"});
    MyAppBean bean = (MyAppBean) applicationContext.getBean("myAppBean");
    System.out.println(bean.sayHello());
    applicationContext.destroy();
    applicationContext.close();
  }
}
