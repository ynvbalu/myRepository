package com.app.java8;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import com.app.java8.ComparatorUsingJava8;

public class ComparatorUsingJava8Test {

  private ComparatorUsingJava8 test = new ComparatorUsingJava8();
  private List<Integer> myList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

  @Test(expected = AssertionError.class)
  public void testSortTheGivenCollectionWithNullInput() {
    test.sortTheGivenCollection(null);
    fail("List should not be null");
  }

  @Test(expected = AssertionError.class)
  public void testSortTheGivenCollectionWithEmptyInput() {
    test.sortTheGivenCollection(new ArrayList<>());
    fail("List should not be empty");
  }

  @Test
  public void testSortTheGivenCollection() {
    ArrayList<String> list = new ArrayList<>();
    list.add("z");
    list.add("b");
    list.add("a");

    ArrayList<String> listOutput = new ArrayList<>();
    listOutput.add("a");
    listOutput.add("b");
    listOutput.add("z");
    assertTrue(test.sortTheGivenCollection(list).equals(listOutput));
  }

  @Test(expected = AssertionError.class)
  public void testIterateListWithNullInput() {
    test.iterateList(null);
    fail("List should not be null");
  }

  @Test(expected = AssertionError.class)
  public void testIterateListWithEmptyInput() {
    test.iterateList(new ArrayList<>());
    fail("List should not be empty");
  }

  @Test
  public void testIterateList() {
    ArrayList<String> list = new ArrayList<>();
    list.add("z");
    list.add("b");
    list.add("a");
    assertTrue(test.iterateList(list).equals(list));
  }

  @Test(expected = AssertionError.class)
  public void testEvaluateWithNullInput() {
    test.evaluate(null, n -> true);
    fail("List should not be null");
  }

  @Test(expected = AssertionError.class)
  public void testEvaluateWithEmptyInput() {
    test.evaluate(new ArrayList<>(), n -> true);
    fail("List should not be empty");
  }

  @Test
  public void testEvalList() {
    test.evaluate(myList, n -> true);
  }
}
