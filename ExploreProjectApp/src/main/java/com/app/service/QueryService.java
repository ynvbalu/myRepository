package com.app.service;

import java.io.IOException;
import java.util.List;

public interface QueryService {

  List<String> getStartUpQueries(String fileName) throws IOException;

  Integer getNumberOfLinesInTheLog(String fileName) throws IOException;

  List<String> getFormattedQueriesWithBindingValues(String fileName, Integer readFromThatLine) throws IOException;

  List<String> getQueryTemplates() throws IOException;
  
  @Deprecated
  String getStartUpMessage(String fileName) throws IOException;

  List<String> getLinesBySearchString(String fileName, String searchStr) throws IOException;

  List<String> getErrorsOrExceptions(String location) throws IOException;

}