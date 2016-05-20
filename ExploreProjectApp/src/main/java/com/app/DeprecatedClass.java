package com.app;

import static com.app.constants.Chars.GREATERTHAN;
import static com.app.constants.Chars.LESSTHAN;
import static com.app.constants.Constants.*;
import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;
import static org.apache.commons.lang3.StringUtils.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Deprecated
public class DeprecatedClass {
 
  /**
   * @param fileName file name
   * @return list
   * @throws IOException IOException
   */
  @SuppressWarnings("unused")
  @Deprecated
  private static List<String> filteredLines(String fileName) throws IOException {
    List<String> lines = filteredStream(fileName).collect(Collectors.toList());

    return lines;
  }

  /**
   * @param fileName fileName
   * @return stream
   * @throws IOException io exception
   */
  @Deprecated
  private static Stream<String> filteredStream(String fileName) throws IOException {
    return readAllLines(get(fileName)).stream().filter(line -> contains(line, EXECUTING_SQL_QUERY) || contains(line,
        EXECUTING_PREPARED_SQL_STATEMENT) || contains(line, DEBUG_SQL) || contains(line, BINDING_PARAMETER) || contains(
            line, CALLING_STORED_PROCEDURE) || contains(line, SETTING_SQL_STATEMENT_PARAMETER_VALUE));
  }

  @Deprecated
  public String getStartUpMessage(String fileName) throws IOException {
    String queryStr = null;

    if (isNotBlank(fileName)) {
      Path path = get(fileName);
      String firstLine = readAllLines(path).stream().filter(line -> contains(line,
          THE_SERVER_STARTED_IN_RUNNING_MODE)).findFirst().get();

      System.out.println(join(THE_SERVER_STARTED_IN_RUNNING_MODE, " firstLine :", firstLine));

      int startIndex = indexOf(firstLine, LESSTHAN.toString()) + length(LESSTHAN.toString());
      int lastIndexOf = indexOf(firstLine, GREATERTHAN.toString());
      queryStr = substring(firstLine, startIndex, lastIndexOf);
    }

    return queryStr;
  }

}
