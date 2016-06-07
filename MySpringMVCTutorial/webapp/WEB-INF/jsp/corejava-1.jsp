<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Core Java</title>
</head>
<body>
  <p>Must know core Java interview questions answered with diagrams &#038; code for freshers to experienced.</p>
  <p>
    <span style="font-size: large; color: brown;">Q1. </span>What is the difference between "==" and
    &#8220;equals(&#8230;)&#8221; in comparing Java String objects? <br /> <span
      style="font-size: large; color: brown;">A1. </span>Comparing two <b><i>String</i></b> objects using "==" (i.e.
    shallow) instead of "<i>equals( )</i>" (i.e deep). When you use "==", you are actually comparing two object
    references, to see if they <u>point to the same object</u>. For example:
  </p>
  <p></p>
  <textarea rows="10" cols="110">
   public class StringEquals {
     public static void main(String[ ] args) {
       String s1 = "Hello";
       String s2 = new String(s1);
       String s3 = "Hello";
       System.out.println(s1 + " equals " + s2 + "--> " +  s1.equals(s2)); //true
       System.out.println(s1 + " == " + s2 + " --> " + (s1 == s2)); //false
       System.out.println(s1 + " == " + s3+ " --> " + (s1 == s3)); //true
     }
    }
  </textarea>

</body>
</html>