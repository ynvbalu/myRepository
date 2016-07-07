package com.app.service;

import static com.app.constants.Chars.L_BRACKT;
import static com.app.constants.Chars.R_BRACKT;
import static com.app.constants.Constants.*;
import static java.nio.file.Files.readAllLines;
import static java.nio.file.Files.write;
import static java.nio.file.Paths.get;
import static org.apache.commons.lang3.StringUtils.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.gson.*;

@Service
public class QueryServiceImpl implements QueryService {

  @Bean(name = "props")
  public static PropertiesFactoryBean mapper() {
    PropertiesFactoryBean bean = new PropertiesFactoryBean();
    bean.setLocation(new FileSystemResource(CODE_DESC));

    return bean;
  }

  @Resource(name = "props")
  private Map<String, String> props;

  /**
   * @param fileName name
   * @return list
   * @throws IOException exception
   */
  @Override
  public List<String> getStartUpQueries(String fileName) throws IOException {
    List<String> list = new ArrayList<>();
    List<String> lines = readAllLines(get(fileName)).stream().filter(line -> contains(line,
        SEARCH_START_1) && !contains(line, SEARCH_START_2)).collect(Collectors.toList());

    lines.forEach(line -> list.add(getQuery(line, V_SELECT, S_SELECT, INSERT_Q, UPDATE_Q, DELETE_Q)));

    return list;
  }

  /**
   * @param line line
   * @param crude crude
   * @return query
   */
  private static String getQuery(String line, String... crude) {
    String query = EMPTY;
    for (String sqlType : crude) {

      if (indexOf(line, sqlType) != INDEX_NOT_FOUND) {
        query = substring(line, lastIndexOf(line, sqlType) + length(sqlType));
      }
    }

    return query;
  }

  @Override
  public Integer getNumberOfLinesInTheLog(String fileName) throws IOException {
    Integer numberOfLines = 0;
    List<String> lines = readAllLines(get(fileName));

    if (!CollectionUtils.isEmpty(lines)) {
      numberOfLines = lines.size();
    }

    return numberOfLines;
  }

  /**
   * @param fileName file name
   * @param readFromThatLine readFromThatLine
   * @return finalOutput finalOutput
   * @throws IOException exception
   */
  @Override
  public List<String> getFormattedQueriesWithBindingValues(String fileName, Integer readFromThatLine)
      throws IOException {
    List<String> lines = Collections.emptyList();
    lines = readAllLines(get(fileName));

    List<String> sqls = addQueriesAndBindingValuesToTheCollection(lines, readFromThatLine);

    List<String> finalOutput = matchQueriesWithCorrespondingBindingValues(sqls);

    return finalOutput;
  }

  @Override
  public List<String> getQueryTemplates() throws IOException {
    List<String> finalOutput = readAllLines(get(QUERIES_FILE));

    return finalOutput;
  }

  /**
   * @param queryAndValues list of queries and values
   * @return list
   * @throws IOException IOException
   */
  private List<String> matchQueriesWithCorrespondingBindingValues(List<String> queryAndValues) throws IOException {
    List<String> output = new ArrayList<>();
    int size = queryAndValues.size();
    Set<String> queriesOnlyList = new HashSet<>();
    String descAndCode = props.get(DESC_CODE_LIST);
    String[] descList = split(descAndCode, ",");
    JsonParser parser = new JsonParser();
    GsonBuilder gsonBuilder = new GsonBuilder();

    for (int lineNo = 0; lineNo < size; lineNo++) {
      String query = queryAndValues.get(lineNo);
      boolean startsWith = startsWith(query, QUERY);

      addingOutputToList(output, parser, gsonBuilder, query);

      if (startsWith) {
        queriesOnlyList.add(strip(substring(query, length(QUERY), length(query))));
      }

      try {
        while (indexOf(query, Q_MARK) != INDEX_NOT_FOUND && startsWith) {

          if (lineNo >= size - 1) {
            break;
          }
          String value = queryAndValues.get(lineNo + 1);

          if (startsWith(value, VALUE)) {
            value = formattingTheValue(parser, gsonBuilder, value);
            query = query.replaceFirst(join(SLASH_, Q_MARK), value);
            lineNo++;
          } else {
            query = query.replaceFirst(join(SLASH_, Q_MARK), NO_VAL);
          }
        }

        query = replacingTheQuery(queryAndValues, lineNo, query, startsWith);

      } catch (Exception e) {
        System.out.println(query);
        System.out.println(e);
        e.printStackTrace();
      }

      if (startsWith) {
        String writeText = strip(substring(query, length(QUERY), length(query)));
        output.add(provideMoreInfo(writeText, descList));
      }
    }

    writeToQueryFile(queriesOnlyList);

    return output;
  }

  /**
   * @param parser parser
   * @param gsonBuilder gsonBuilder
   * @param value value
   * @return value
   */
  private String formattingTheValue(JsonParser parser, GsonBuilder gsonBuilder, String value) {
    value = substring(value, length(VALUE), length(value));

    if (!isNumeric(value) && !startsWith(value, S_QUOTE)) {
      value = join(S_QUOTE, value, S_QUOTE);
    }

    if (!CollectionUtils.isEmpty(props) && props.containsKey(value)) {
      value = join(value, BLACK_FONT, C_START, props.get(value), C_END, T_END);
    }

    value = formatingTheJson(parser, gsonBuilder, value);

    return value;
  }

  /**
   * @param queriesOnlyList queriesOnlyList
   * @throws IOException exception
   */
  private static void writeToQueryFile(Set<String> queriesOnlyList) throws IOException {
    Path sortedFile = get(QUERIES_FILE);
    write(sortedFile, queriesOnlyList, Charset.forName(UTF_8));
  }

  /**
   * @param queryAndValues queryAndValues
   * @param lineNo lineNo
   * @param query query
   * @param startsWith startsWith
   * @return string
   */
  private static String replacingTheQuery(List<String> queryAndValues, int lineNo, String query, boolean startsWith) {
    if (indexOf(query, DYN_VAL) != INDEX_NOT_FOUND && startsWith) {
      String replaceValue = queryAndValues.get(lineNo + 5);
      replaceValue = join(RED_FONT, substring(replaceValue, length(VALUE), replaceValue.length()), T_END);
      query = replace(query, DYN_VAL, replaceValue);
    }
    return query;
  }

  /**
   * @param parser parser
   * @param gsonBuilder gsonBuilder
   * @param value value
   * @return value
   */
  private static String formatingTheJson(JsonParser parser, GsonBuilder gsonBuilder, String value) {
    if (startsWith(value, join(S_QUOTE, L_BRACKET))) {
      value = substringAfter(value, S_QUOTE);
      value = removeEnd(value, S_QUOTE);
      value = replace(value, join(D_QUOTE, L_BRACKET), L_BRACKET);
      value = replace(value, join(R_BRACKET, D_QUOTE), R_BRACKET);
      value = replace(value, join(SLASH_, D_QUOTE), S_QUOTE);
      value = replace(value, D_QUOTE, S_QUOTE);
      value = toPrettyFormat(parser, gsonBuilder, value);
      value = join(BLUE_FONT, value, T_END);
    } else {
      value = join(RED_FONT, value, T_END);
    }

    return value;
  }

  /**
   * @param output output
   * @param parser parser
   * @param builder gsonBuilder
   * @param query query
   */
  private static void addingOutputToList(List<String> output, JsonParser parser, GsonBuilder builder, String query) {
    if (startsWith(query, OUT)) {
      String outStr = substringAfter(query, OUT);
      outStr = replace(outStr, join(SLASH_, D_QUOTE), S_QUOTE);
      outStr = replace(outStr, D_QUOTE, S_QUOTE);
      outStr = replace(outStr, join(L_BRACKT.toString(), S_QUOTE), L_BRACKT.toString());
      outStr = replace(outStr, join(S_QUOTE, R_BRACKT.toString()), R_BRACKT.toString());
      outStr = toPrettyFormat(parser, builder, outStr);
      outStr = join(BLUE_FONT, outStr, T_END);
      output.add(outStr);
    }
  }

  /**
   * @param lines bufferred reader
   * @param readFromThatLine readFromThatLine
   * @return list of sqls
   * @throws IOException IO exception when you read the file
   */
  private static List<String> addQueriesAndBindingValuesToTheCollection(List<String> lines, Integer readFromThatLine)
      throws IOException {
    List<String> sqls = new ArrayList<>();
    System.out.println(join("Reading from this line now ", String.valueOf(readFromThatLine)));
    System.out.println(join("Total number of lines are ", String.valueOf(lines.size())));
    boolean flag = false;
    StringBuilder sb = new StringBuilder();
    for (Integer lineNo = readFromThatLine; lineNo < lines.size(); lineNo++) {
      String line = lines.get(lineNo);
      String element = queryStr(line);

      if (isBlank(element)) {
        element = valueStr(line);
      }

      if (isNotBlank(element)) {
        sqls.add(element);
      }

      if (contains(line, "RuleResult JSON: {")) {
        line = substringAfter(line, "RuleResult JSON:");
        flag = true;
      }
      if (!startsWith(line, R_BRACKET) && flag) {
        sb.append(line);
      }
      if (startsWith(line, R_BRACKET) && flag) {
        // System.out.println(line);
        sb.append(line);
        sqls.add(join(OUT, sb.toString()));
        sb.delete(0, sb.length());
        flag = false;
      }

    }

    return sqls;
  }

  /**
   * @param line line
   * @return queryStr
   */
  private static String queryStr(String line) {
    String query = null;

    query = getSpringQuery(line, query);
    query = getQueryFromDebugSqlLine(line, query);

    if (isNotBlank(query)) {
      query = join(QUERY, removeWhiteSpaces(query));
    }

    return query;
  }

  /**
   * @param line line
   * @param query query
   * @return query
   */
  private static String getSpringQuery(String line, String query) {
    if (contains(line, EXECUTING_SQL_QUERY) || contains(line, CALLING_STORED_PROCEDURE) || contains(line,
        EXECUTING_PREPARED_SQL_STATEMENT)) {
      query = extractQuery(line);
      query = replace(query, D_QUOTE, EMPTY);
    } else if (contains(line, DEBUG_SQL) && contains(line, SELECT_FROM_LOWER)) {
      query = substring(line, indexOf(line, HYPEN) + length(HYPEN));
      query = specialCaseTwo(query);
    }

    return query;
  }

  /**
   * @param line line
   * @param queryStr queryStr
   * @return queryStr
   */
  private static String getQueryFromDebugSqlLine(String line, String queryStr) {
    if (contains(line, DEBUG_SQL)) {
      queryStr = substring(line, indexOf(line, HYPEN) + length(HYPEN));
      queryStr = specialCases(queryStr);
      queryStr = changeAliasName(queryStr);
      queryStr = twoSelects(queryStr);
      queryStr = specialCaseThree(queryStr);
    }
    return queryStr;
  }

  /**
   * @param queryStr queryStr
   * @return queryStr
   */
  private static String specialCases(String queryStr) {
    if (indexOf(queryStr, FROM) != INDEX_NOT_FOUND && !contains(queryStr, DUAL) && !contains(queryStr,
        LEFT_OUTER_JOIN)) {
      String[] temp = splitByWholeSeparator(queryStr, FROM);
      queryStr = join(SELECT_FROM, temp[1]);
      if (temp.length == 3) {
        queryStr = join(queryStr, FROM, temp[2]);
      }
    } else if (contains(queryStr, LEFT_OUTER_JOIN)) {
      if (contains(queryStr, CODE_8)) {
        if (contains(queryStr, CODE_7)) {
          queryStr = specialCaseOne(queryStr);
        }

      }

    }
    return queryStr;
  }

  /**
   * @param line line
   * @return query
   */
  private static String extractQuery(String line) {
    return substring(line, indexOf(line, L_BRACKT.toString()) + length(L_BRACKT.toString()), lastIndexOf(line,
        R_BRACKT.toString()));
  }

  /**
   * @param queryStr queryStr
   * @return queryStr
   */
  private static String specialCaseThree(String queryStr) {
    if (contains(queryStr, CODE_12)) {
      queryStr = replace(queryStr, CODE_12, CODE_11);
    }

    if (contains(queryStr, CODE_10)) {
      queryStr = replace(queryStr, CODE_10, CODE_9);
    }

    return queryStr;
  }

  /**
   * @param queryStr queryStr
   * @return queryStr
   */
  private static String specialCaseTwo(String queryStr) {
    queryStr = replace(queryStr, join(CODE_6, Q_MARK), join(CODE_6, RED_FONT, CODE_2, T_END));
    queryStr = replace(queryStr, join(CODE_5, Q_MARK), join(CODE_5, DYN_VAL));
    queryStr = replace(queryStr, join(CODE_4, Q_MARK), join(CODE_4, RED_FONT, CODE_1, T_END));

    return queryStr;
  }

  /**
   * @param queryStr queryStr
   * @return queryStr
   */
  private static String specialCaseOne(String queryStr) {
    queryStr = replace(queryStr, CODE_8, CPSG);
    queryStr = replace(queryStr, CODE_7, CS);
    queryStr = join(SELECT_FROM_LOWER, splitByWholeSeparator(queryStr, FROM)[1]);

    return queryStr;
  }

  /**
   * @param tableName tableName to alias name conversion
   * @return newAlias newAlias
   */
  private static String tableToNewAliasName(String tableName) {
    String newAlias = EMPTY;

    for (int index = 1; index < splitByWholeSeparator(tableName, UNDER_SCORE).length; index++) {
      String input = splitByWholeSeparator(tableName, UNDER_SCORE)[index].toLowerCase();
      newAlias += input.substring(0, 1);
    }

    return newAlias;
  }

  private static String twoSelects(String queryStr) {
    if (startsWith(queryStr, SELECT) && contains(queryStr, swapCase(SELECT))) {
      String[] temp = splitByWholeSeparator(queryStr, swapCase(SELECT));
      queryStr = join(temp[0], changeAliasName(join(SELECT, temp[1])));
    }

    return queryStr;
  }

  /**
   * @param line line
   * @return valueStr
   */
  private static String valueStr(String line) {
    String valueStr = null;

    if (contains(line, BINDING_PARAMETER)) {
      valueStr = strip(substring(line, lastIndexOf(line, BRACKET) + length(BRACKET) - 1));
      if (contains(line, LONGVARCHAR) || contains(line, VARCHAR)) {
        valueStr = join(S_QUOTE, valueStr, S_QUOTE);
      }
    } else if (contains(line, SETTING_SQL_STATEMENT_PARAMETER_VALUE)) {
      valueStr = replace(substring(line, indexOf(line, L_BRACKT.toString()) + length(L_BRACKT.toString()),
          indexOf(line, R_BRACKT.toString())), D_QUOTE, EMPTY);
    }

    if (isNotBlank(valueStr)) {
      valueStr = join(VALUE, valueStr);
    }

    return valueStr;
  }

  private static String changeAliasName(String queryStr) {
    if (startsWith(queryStr, SELECT) && contains(queryStr, lowerCase(WHERE))) {
      String[] out = splitByWholeSeparator(queryStr, lowerCase(WHERE));
      String s1 = out[0];
      int length = s1.split(SPACE).length;
      String oldAlias = s1.split(SPACE)[length - 1];
      String newAlias = s1.split(SPACE)[length - 2];
      queryStr = replace(queryStr, oldAlias, tableToNewAliasName(newAlias));
    }

    return queryStr;
  }

  public static String toPrettyFormat(JsonParser parser, GsonBuilder gsonBuilder, String json) {
    JsonObject jsonObject = parser.parse(json).getAsJsonObject();
    Gson gson = gsonBuilder.setPrettyPrinting().create();
    String prettyJson = gson.toJson(jsonObject);

    return prettyJson;
  }

  private static String removeWhiteSpaces(String query) {
    query = replace(query, "       ", " ");
    query = replace(query, "      ", " ");
    query = replace(query, "     ", " ");
    query = replace(query, "    ", " ");
    query = replace(query, "   ", " ");
    query = replace(query, "  ", " ");
    query = replace(query, NO_SPACE, WITH_SPACE);
    return join(query, SEMI_COLON);
  }

  private String provideMoreInfo(String query, String[] descList) {
    if (descList != null && descList.length != 0)
      query = codeToDescription(query, descList);
    return query;
  }

  /**
   * @param query query
   * @param codes codes
   * @return description
   */
  private String codeToDescription(String query, String... codes) {
    for (String code : codes) {
      if (contains(query, code)) {
        query = replace(query, code, join(code, BLUE_FONT, C_START, props.get(strip(substringAfter(
            code, EQUALS))), C_END, T_END));
      }
    }

    if (contains(query, CODE_3)) {
      query = replace(query, CODE_3, join(CODE_3, BLUE_FONT, C_START, props.get("6518"), ",",
          props.get("7658"), C_END, T_END));
    }

    return query;
  }

  @Override
  public List<String> getLinesBySearchString(String fileName, String searchStr) throws IOException {
    return readAllLines(get(fileName)).stream().filter(line -> contains(line, searchStr)).collect(Collectors.toList());
  }

  @Override
  public List<String> getErrorsOrExceptions(String fileName) throws IOException {
    List<String> lines = readAllLines(get(fileName));
    List<String> sqls = new ArrayList<>();
    boolean flag = false;

    for (String line : lines) {
      if (contains(line, " ERROR ")) {
        flag = true;
      }

      if (flag) {
        sqls.add(line);
      }

      if (!contains(line, " ERROR ") && startsWith(line, "2016-05-19") && flag) {
        flag = false;
      }
    }

    return sqls;
  }
}
