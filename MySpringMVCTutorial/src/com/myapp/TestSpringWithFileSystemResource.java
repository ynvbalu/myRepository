package com.myapp;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class TestSpringWithFileSystemResource {
  public static void main(String[] args) {
    Resource res = new FileSystemResource("src/com/myapp/myApp-applicationContext.xml");
    XmlBeanFactory factory = new XmlBeanFactory(res);
    MyAppBean bean = (MyAppBean) factory.getBean("myAppBean");
    System.out.println(bean.sayHello());
  }
}
