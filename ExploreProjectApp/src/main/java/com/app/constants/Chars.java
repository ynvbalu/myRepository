package com.app.constants;

import java.util.HashMap;
import java.util.Map;

public enum Chars {
  GREATERTHAN('>'),
  LESSTHAN('<'),
  COMMA(','),
  SEMICOLON(';'),
  R_BRACKT(']'),
  L_BRACKT('['),
  EXCLAMATION_SIGN('!');

  private final int ch;
  private static Map<Integer, Chars> map = new HashMap<>();
  
  static {
    for (Chars c : Chars.values()) {
      map.put(c.ch, c);
    }
  }

  private Chars(int ch) {
    this.ch = ch;
  }

  public int toCodePoint() {
    return ch;
  }

  public static Chars fromCodePoint(int cp) {
    return map.get(cp);
  }

  @Override
  public String toString() {
    return new String(new int[] { ch }, 0, 1);
  }
}
