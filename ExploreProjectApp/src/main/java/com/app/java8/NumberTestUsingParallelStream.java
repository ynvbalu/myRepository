package com.app.java8;

import java.util.Arrays;

public class NumberTestUsingParallelStream {
  public static void main(String[] args) {
    Integer[] numbers = {1,2,3,4,5,6};

    Arrays.asList(numbers)
       .parallelStream( )      //use fork and join thread pool introduced in Java 7
       .filter(NumberTestUsingParallelStream::isOddNumber)
       .map(NumberTestUsingParallelStream::doubleIt)
       .peek(x -> System.out.println(Thread.currentThread().getName() + " processed " + x))
       .count(); // a terminal operation is required as the intermediate operations are lazily evaluated.
                 // if count() is omitted, nothing gets processed
  } 
  
  private static boolean isOddNumber(int input) {
      return input % 2 != 0;
  }
  
  private static int doubleIt(int input) {
      return input * 2;
  }
}
