package com.app;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.*;

public class Test {
  static String temp1 = "{'naga':venkata,'bala':ffffff}";
  public static final String BRACKET = "] - ";
  public static void main(String[] args) {
    System.out.println(BRACKET.length());
    for (int cp = 32; cp < 48; ++cp) {
      //System.out.printf("%c : %s%n", cp, Character.getName(cp));
    }
    
    String test = "abc from def";
    
    for (String string : test.split("from")) {
      //System.out.println(string);
    }

    for (String string2 : StringUtils.splitByWholeSeparator(test, "from")) {
      //System.out.println(string2);
    }
    System.out.println(toPrettyFormat(temp1));
  }
  
  public static String toPrettyFormat(String jsonString) 
  {
    
      JsonParser parser = new JsonParser();
      JsonObject json = parser.parse(jsonString).getAsJsonObject();

      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String prettyJson = gson.toJson(json);

      return prettyJson;
  }
}
