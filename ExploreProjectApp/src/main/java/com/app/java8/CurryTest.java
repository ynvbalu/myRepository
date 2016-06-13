package com.app.java8;

import java.util.function.Function;

public class CurryTest {
  public static void main(String[] args) {
    Function<Integer, Function<Integer, Integer>> add = (a) -> (b) -> a + b;

    Function<Integer, Integer> addOne = add.apply(1);
    Function<Integer, Integer> addfive = add.apply(5);
    Function<Integer, Integer> addTen = add.apply(10);

    Integer result1 = addOne.apply(2); // returns 3
    Integer result2 = addfive.apply(2); // returns 7
    Integer result3 = addTen.apply(2); // returns 12

    System.out.println("reslut1 = " + result1);
    System.out.println("reslut2 = " + result2);
    System.out.println("reslut3 = " + result3);
  }
}
