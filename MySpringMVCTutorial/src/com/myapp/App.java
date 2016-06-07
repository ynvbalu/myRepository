package com.myapp;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class App {

  public static void main(String[] args) {
    final XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("com/myapp/myApp-applicationContext.xml"));

    MyAppBean myAppBean = (MyAppBean) beanFactory.getBean("myAppBean");
    System.out.println(myAppBean.sayHello());

  }
}
