package com.app.service;

import static com.app.constants.Chars.LEFT_BRACKET;
import static com.app.constants.Chars.RIGHT_BRACKET;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.gson.*;

@Service
public class QueryServiceImpl implements QueryService {

  @Bean(name = "myProperties")
  public static PropertiesFactoryBean mapper() {
    PropertiesFactoryBean bean = new PropertiesFactoryBean();
    bean.setLocation(new ClassPathResource(CODE_DESC_PROPERTIES));

    return bean;
  }

  @Resource(name = "myProperties")
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
        DEBUG_ABSTRACT_ENTITY_PERSISTER) && !contains(line, STATIC_SQL_FOR_ENTITY)).collect(Collectors.toList());

    lines.forEach(line -> list.add(getQuery(line, VERSION_SELECT, SNAPSHOT_SELECT, INSERT_0, UPDATE_0, DELETE_0)));

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
    List<String> lines = readAllLines(get(fileName));

    Path linesFound = get(LINES_FOUND_FILE);
    write(linesFound, lines, Charset.forName(UTF_8));

    List<String> sqls = addQueriesAndBindingValuesToTheCollection(lines, readFromThatLine);

    Path file = get(QUERY_VALUES_TXT);
    write(file, sqls, Charset.forName(UTF_8));

    List<String> finalOutput = matchQueriesWithCorrespondingBindingValues(sqls);

    return finalOutput;
  }

  @Override
  public List<String> getQueryTemplates() throws IOException {
    List<String> finalOutput = readAllLines(get(QUERIESFILE));

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
    String descAndCode = props.get("DESC_CODE_LIST");
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
        while (indexOf(query, QUESTION_MARK_) != INDEX_NOT_FOUND && startsWith) {

          if (lineNo >= size - 1) {
            break;
          }
          String value = queryAndValues.get(lineNo + 1);

          if (startsWith(value, VALUE)) {
            value = formattingTheValue(parser, gsonBuilder, value);
            query = query.replaceFirst(join(DOUBLE_LEFT_QUOTE, QUESTION_MARK_), value);
            lineNo++;
          } else {
            query = query.replaceFirst(join(DOUBLE_LEFT_QUOTE, QUESTION_MARK_), NOVALUE);
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

    if (!isNumeric(value) && !startsWith(value, SINGLE_QUOTE)) {
      value = join(SINGLE_QUOTE, value, SINGLE_QUOTE);
    }

    if (!CollectionUtils.isEmpty(props) && props.containsKey(value)) {
      value = join(value, FONT_COLOR_BLACK_FONT, COMMENT_START, props.get(value), COMMENT_END, FONT_END);
    }

    value = formatingTheJson(parser, gsonBuilder, value);

    return value;
  }

  /**
   * @param queriesOnlyList queriesOnlyList
   * @throws IOException exception
   */
  private static void writeToQueryFile(Set<String> queriesOnlyList) throws IOException {

    Path sortedFile = get(QUERIESFILE);
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
    if (indexOf(query, DYNAMIC_VALUE) != INDEX_NOT_FOUND && startsWith) {
      String replaceValue = queryAndValues.get(lineNo + 5);
      replaceValue = join(FONT_COLOR_RED, substring(replaceValue, length(VALUE), replaceValue.length()), FONT_END);
      query = replace(query, DYNAMIC_VALUE, replaceValue);
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
    if (startsWith(value, join(SINGLE_QUOTE, LEFT_BRACKET_1))) {
      value = substringAfter(value, SINGLE_QUOTE);
      value = removeEnd(value, SINGLE_QUOTE);
      value = replace(value, join(DOUBLE_QUOTE, LEFT_BRACKET_1), LEFT_BRACKET_1);
      value = replace(value, join(RIGHT_BRACKET_1, DOUBLE_QUOTE), RIGHT_BRACKET_1);
      value = replace(value, join(DOUBLE_LEFT_QUOTE, DOUBLE_QUOTE), SINGLE_QUOTE);
      value = replace(value, DOUBLE_QUOTE, SINGLE_QUOTE);
      value = toPrettyFormat(parser, gsonBuilder, value);
      value = join(FONT_COLOR_BLUE_FONT, value, FONT_END);
    } else {
      value = join(FONT_COLOR_RED, value, FONT_END);
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
    if (startsWith(query, OUTPUT)) {
      String outStr = substringAfter(query, OUTPUT);
      outStr = replace(outStr, join(DOUBLE_LEFT_QUOTE, DOUBLE_QUOTE), SINGLE_QUOTE);
      outStr = replace(outStr, DOUBLE_QUOTE, SINGLE_QUOTE);
      outStr = replace(outStr, join(LEFT_BRACKET.toString(), SINGLE_QUOTE), LEFT_BRACKET.toString());
      outStr = replace(outStr, join(SINGLE_QUOTE, RIGHT_BRACKET.toString()), RIGHT_BRACKET.toString());
      outStr = toPrettyFormat(parser, builder, outStr);
      outStr = join(FONT_COLOR_BLUE_FONT, outStr, FONT_END);
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
      if (!startsWith(line, RIGHT_BRACKET_1) && flag) {
        sb.append(line);
      }
      if (startsWith(line, RIGHT_BRACKET_1) && flag) {
        // System.out.println(line);
        sb.append(line);
        sqls.add(join(OUTPUT, sb.toString()));
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
      query = replace(query, DOUBLE_QUOTE, EMPTY);
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
    return substring(line, indexOf(line, LEFT_BRACKET.toString()) + length(LEFT_BRACKET.toString()), lastIndexOf(line,
        RIGHT_BRACKET.toString()));
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
    queryStr = replace(queryStr, join(CODE_6, QUESTION_MARK_), join(CODE_6, FONT_COLOR_RED, CODE_2, FONT_END));
    queryStr = replace(queryStr, join(CODE_5, QUESTION_MARK_), join(CODE_5, DYNAMIC_VALUE));
    queryStr = replace(queryStr, join(CODE_4, QUESTION_MARK_), join(CODE_4, FONT_COLOR_RED, CODE_1, FONT_END));

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
        valueStr = join(SINGLE_QUOTE, valueStr, SINGLE_QUOTE);
      }
    } else if (contains(line, SETTING_SQL_STATEMENT_PARAMETER_VALUE)) {
      valueStr = replace(substring(line, indexOf(line, LEFT_BRACKET.toString()) + length(LEFT_BRACKET.toString()),
          indexOf(line, RIGHT_BRACKET.toString())), DOUBLE_QUOTE, EMPTY);
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
    query = replace(query, TABLE_SUFFIX, SPACE_TABLE_SUFFIX);
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
        query = replace(query, code, join(code, FONT_COLOR_BLUE_FONT, COMMENT_START, props.get(strip(substringAfter(
            code, EQUALS))), COMMENT_END, FONT_END));
      }
    }

    if (contains(query, CODE_3)) {
      query = replace(query, CODE_3, join(CODE_3, FONT_COLOR_BLUE_FONT, COMMENT_START, props.get("6518"), ",",
          props.get("7658"), COMMENT_END, FONT_END));
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
