package com.java8.example;

import java.util.function.Function;
import java.util.function.IntToDoubleFunction;

public class RecursionTest {

  static class Recursive<I> {
    public I func;
  }

  static Function<Integer, Double> factorial = x -> {
    Recursive<IntToDoubleFunction> recursive = new Recursive<IntToDoubleFunction>();
    recursive.func = n -> (n == 0) ? 1 : n * recursive.func.applyAsDouble(n - 1);

    return recursive.func.applyAsDouble(x);
  };

  public static void main(String[] args) {
    Double result = factorial.apply(3);
    System.out.println("factorial of 3 = " + result);
  }
}
