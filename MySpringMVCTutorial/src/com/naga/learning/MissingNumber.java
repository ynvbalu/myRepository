package com.naga.learning;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class MissingNumber {

  public static void main(String[] args) {
    int numbers[] = { 7, 3, 6, 4, 2, 8, 9, 10, 12, 14, 13, 11, 15, 18 };
    MissingNumber.findMissingNumbers(numbers, 0, 20);
  }

  private static void findMissingNumbers(int[] input, int first, int last) {
    Set<Integer> expectedNumbers = new CopyOnWriteArraySet<Integer>();
    for (int i = first; i <= last; i++) {
      expectedNumbers.add(i);
    }

    //remove numbers from the above set that are in the input.
    for (int i = 0; i < input.length; i++) {
      if(expectedNumbers.contains(input[i])) {
        expectedNumbers.remove(input[i]); //CopyOnWriteArraySet is used that
        //You won't get ConcurrentModificationException
      }
    }

    //left over numbers in the set are the missing numbers
    for (Integer number : expectedNumbers){
      System.out.println(number);
    }
  }

} 