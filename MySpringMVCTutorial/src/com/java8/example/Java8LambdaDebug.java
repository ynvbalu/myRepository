package com.java8.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Java8LambdaDebug {
  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10, 6);// 6 is repeated
    list.stream()
    .filter(i -> i % 3 == 0) //multiples of 3
    .peek(i -> System.out.println("Debug pt1: " + i))
    .filter(i -> i < 7)
    .peek(i -> System.out.println("Debug pt2: " + i))
    .collect(Collectors.toSet()) // remove duplicates i.e 6
    .forEach(i -> System.out.println("result: " + i));
  }

}
