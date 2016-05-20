package com.app;

import static org.apache.commons.lang3.StringUtils.contains;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import com.app.service.QueryService;

public class QueryServiceEachCaseTest {

  @Inject
  private QueryService log;

  public String drawDigitsFromString(String strValue) {
    String str = strValue.trim();
    String digits = "";
    for (int i = 0; i < str.length(); i++) {
      char chrs = str.charAt(i);
      if (Character.isDigit(chrs))
        digits = digits + chrs;
    }
    return digits;
  }

  @Test
  public void testCheckFileExists() throws IOException {
    assertTrue(fileExists("./src/test/resources/log4j.log"));
  }

  public boolean fileExists(String fileName) throws IOException {
    Path path = Paths.get(fileName);
    List<String> lines = Files.readAllLines(path);
    boolean flag = false;
    if(!lines.isEmpty())  {
      for (int i = 0; i < lines.size(); i++) {
        String line = lines.get(i);
        if(contains(line, "RuleResult JSON: {")) {
          flag = true;
        } 
        if(!contains(line, "}") && flag) {
          System.out.println(line);
        } 
        if(contains(line, "}") && flag) {
          System.out.println(line);
          flag = false;
        } 
      }
    }
    
    boolean pathExists = Files.exists(path, new LinkOption[] { LinkOption.NOFOLLOW_LINKS });
    return pathExists;
  }
  
  
}
