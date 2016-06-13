package com.app.java8;

import java.util.function.Function;

public class ClosureTest {
  public static void main(String[] args) {
    // closure 1 that adds 5 to a given integer
    Function<Integer, Integer> plus5 = (i) -> (i + 5);
    // closure 2 that times by 2 a given number
    Function<Integer, Integer> times2 = (i) -> (i * 2);
    // closure 3 that adds 5 and then multiply the result by 2
    Function<Integer, Integer> plus5AndThenTimes2 = plus5.andThen(times2);
    // closure 4 that times by 2 and then adds 5
    Function<Integer, Integer> times2AndThenplus5 = times2.andThen(plus5);

    // callback or execute closure
    // fucntions plus5, times2. etc can be passed as arguments
    System.out.println("9+5=" + execute(plus5, 9));
    System.out.println("9*2=" + execute(times2, 9));
    System.out.println("(9+5)*2=" + execute(plus5AndThenTimes2, 9));
    System.out.println("9*2+5=" + execute(times2AndThenplus5, 9));
  }

  // functions can be used as method parameters
  private static Integer execute(Function<Integer, Integer> function, Integer number) {
    return function.apply(number); // execute the function
  }
}
