package com.myapp;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

@SuppressWarnings("deprecation")
public class TestSpringWithClassPathResource {
  public static void main(String[] args) {
    ClassPathResource resource = new ClassPathResource("com/myapp/myApp-applicationContext.xml");
    XmlBeanFactory beanFactory = new XmlBeanFactory(resource);
    MyAppBean bean = (MyAppBean) beanFactory.getBean("myAppBean");
    System.out.println(bean.sayHello());
  }
}
