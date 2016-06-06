package com.mytutorial;

import org.springframework.context.annotation.*;

// Spring Configuration AppConfig

@Configuration
@ComponentScan("com.mytutorial")
public class AppConfig {
  
  @Bean
  MyServiceImpl myServiceImple() {
    return new MyServiceImpl();
  }
}
