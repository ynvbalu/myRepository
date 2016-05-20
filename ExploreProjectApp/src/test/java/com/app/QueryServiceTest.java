package com.app;

import static org.junit.Assert.*;

import java.nio.file.*;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.app.service.QueryService;
import com.app.service.QueryServiceImpl;

@Ignore
public class QueryServiceTest {

  private static String arrayIndexOutOfBoundException = "C://applications//dev//arrayIndexOutOfBoundException.log";
  private static String LOG_FILE_PATH = "C://applications//application_logs//test_2016-04-18_09_44.log";
  private static String LOG_FILE_PATH1 = "C://applications//application_logs//test_2016-04-27_09_15.log";
  private static String LOG_FILE_PATH_STARTUP = "C://applications//application_logs//test_2016-04-19_11_40.log";
  private static String LOG_FILE_EMPTY = "C://applications//dev//empty.log";
  private static String ERROR_FILE_PATH = "C://applications//application_logs//test_2016-05-19_09_49.log";
  private static String CHECK_OUT = "C://applications//myprojects_debug//search_logs.txt";
  private QueryService log = new QueryServiceImpl();

  @Test
  public void testNumberOfLines() throws Exception {
    assertNotNull(log.getNumberOfLinesInTheLog(LOG_FILE_PATH));
  }

  @Test
  public void testNumberOfLinesEmptyFile() throws Exception {
    assertEquals(log.getNumberOfLinesInTheLog(LOG_FILE_EMPTY), Long.valueOf(0));
  }

  @Test
  public void readFromLogFileWithListNull() throws Exception {
    assertNotNull(log.getFormattedQueriesWithBindingValues(LOG_FILE_PATH, Integer.valueOf(0)));
  }
  
  @Test
  public void readFromLogFileWithList() throws Exception {
    assertNotNull(log.getFormattedQueriesWithBindingValues(LOG_FILE_PATH1, Integer.valueOf(0)));
  }

  @Test
  public void readFromLogFileWithListNullStartUpQueries() throws Exception {
    assertNotNull(log.getFormattedQueriesWithBindingValues(LOG_FILE_PATH_STARTUP, Integer.valueOf(0)));
    
  }

  @Test
  public void readFromLogFileWithAllFiles() throws Exception {
    assertNotNull(log.getFormattedQueriesWithBindingValues(LOG_FILE_EMPTY, Integer.valueOf(0)));
  }
 
  @Test
  public void readFromLogFileWithArrayIndexOutOfBoundException() throws Exception {
    assertNotNull(log.getFormattedQueriesWithBindingValues(arrayIndexOutOfBoundException , Integer.valueOf(
        0)));
  }

  @Test 
  public void testCheckOutput() throws Exception {
    List<String> lines = Files.readAllLines(Paths.get(CHECK_OUT));
    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);
      int count = 0;
      if (line.indexOf("?") != -1) {
        count = StringUtils.countMatches(line, "?");
      }
      i += count;
      assertNotNull(line);
      assertTrue((line.startsWith("select") || line.startsWith("SELECT") || line.startsWith("update") ||
          line.startsWith("insert")));
    }
  }
  
  @Test
  public void readErrors() throws Exception {
   List<String> errors = log.getErrorsOrExceptions(ERROR_FILE_PATH);
   errors.forEach(System.out::println);
   assertNotNull(errors);
  }
}
