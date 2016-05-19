package java8.examples;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class ComparatorUsingJava8 {

  public List<String> sortTheGivenCollection(List<String> names) {
    checkForNullAndEmpty(names);
    Collections.sort(names, (name1, name2) -> name1.compareTo(name2));

    return names;
  }

  public List<String> iterateList(List<String> names) {
    checkForNullAndEmpty(names);
    names.forEach(System.out::println);

    return names;
  }

  /**
   * @param names names
   * @throws AssertionError AssertionError
   */
  private static void checkForNullAndEmpty(List<?> names) throws AssertionError {
    if (names == null) {
      throw new AssertionError("List should not be null");
    } else if (names.isEmpty()) {
      throw new AssertionError("List should not be empty");
    }
  }

  public void evaluate(List<Integer> myList, Predicate<Integer> predicate) {
    checkForNullAndEmpty(myList);

    for (Integer n : myList) {

      if (predicate.test(n)) {
        System.out.println(n);
      }
    }
  }

}
