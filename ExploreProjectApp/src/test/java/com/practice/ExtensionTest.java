package com.practice;

public class ExtensionTest {
 
 public static void main(String[] args) {
   exec(new Extension());
 }
 
 private static void exec(Base base) {
   base.add(8);
   System.out.println(base.i);
 }
 
}