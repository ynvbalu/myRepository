package com.practice;

public class Extension extends Base {
  
  public Extension() {
    add(2);
  }
  
  @Override
  public void add(int j) {
    i += j*2;
  }
  
}
