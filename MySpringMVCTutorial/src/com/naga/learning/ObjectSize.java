package com.naga.learning;

import java.util.concurrent.TimeUnit;

public class ObjectSize {

  public static void main(String[] args) throws InterruptedException {

    MyWrapper five = new MyWrapper(5);

    while (true) {
      TimeUnit.SECONDS.sleep(10);
      System.out.println(five);
    }
  }

  // inner class
  static class MyWrapper {
    int number; // 4 bytes each

    public MyWrapper(int number) {
      this.number = number;
    }
  }
}
