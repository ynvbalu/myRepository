package com.myapp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

public class MyAppBeanImpl implements MyAppBean, BeanNameAware, BeanFactoryAware {

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
      System.out.println("received the beanFactory " + beanFactory);
  }

  @Override
  public void setBeanName(String name) {
    System.out.println("the name of the bean is " + name);
  }

  @Override
  public String sayHello() {
    return "Hello, I am initialized";
  }
}
