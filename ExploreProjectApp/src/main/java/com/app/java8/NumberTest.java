package com.app.java8;

import java.util.Arrays;

public class NumberTest {
  public static void main(String[] args) {
    Integer[] numbers = { 1, 2, 3, 4, 5, 6 };
    Arrays.asList(numbers) // convert to list
        .stream() // stream
        .filter((e) -> (e % 2 != 0)) // extract only odd numbers 1, 3, 5
        .map((e) -> (e * 2)) // double the odd numbers 2, 6, 10
        .forEach(System.out::println); // print each doubled number.
  }
}
