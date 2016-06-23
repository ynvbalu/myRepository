package com.naga.learning;

public class MissingNumberExOne {

  public static void main(String[] args) {
    int numbers[] = { 2, 3, 4, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 18 };
    findMissingNumbers(numbers, 0, 20);
  }
  
  private static void findMissingNumbers(int[] input, int first, int last) {
    printMissingNumbersFront(input, first);
    printMissingNumbersInTheMiddle(input, last);
    printMissingNumbersBack(input, last);
  }
  
  /**
   * before 2
   * @param input
   * @param first
   */
  private static void printMissingNumbersFront(int[] input, int first) {
    //0 and 1 are less than 2 as per the above input
    for (int i = first; i < input[0]; i++) {
      System.out.println(i);
    }
  }
  
  /**
   * after 18
   * @param input
   * @param last
   */
  private static void printMissingNumbersBack(int[] input, int last) {
    // 1 + the last element value is 24. 24 to 30 are less than or equal to the last value of 30
    for (int i = 1 + input[input.length - 1]; i <= last; i++) {
      System.out.println(i);
    }
  }
  
  /**
   * between 2 and 18
   * @param input
   * @param last
   */
  private static void printMissingNumbersInTheMiddle(int[] input, int last) {
    //for a given index i, index [i-1]+1 should be equal if present.
    for (int i = 1; i < input.length; i++) {
      //e.g. for input[1] = 3, j=1+2
      //input[2] = 4, j=1+3
      //input[3] = 6, j=4+1, Oops 5 is less than 6 so print
      //input[4] = 7, j=6+1
      //if index[i] < index[i-1] + 1, then number index[i-1] + 1 is missing
      for (int j = 1+input[i-1]; j < input[i]; j++) {
        System.out.println(j);
      }
    }
  }
  
}
