package com.myapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  
  @Bean
  public MyAppBean myBean() {
    return new MyAppBeanImpl();
  }
}
