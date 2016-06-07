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
  <p>
    <br /> The variable <b>s1</b> refers to the String instance created by &#8220;Hello&#8221;. The object referred to
    by <b>s2</b> is created with s1 as an initializer, thus the contents of the two String objects are identical, but
    they are 2 distinct objects having 2 distinct <u>references</u> s1 and s2. This means that s1 and s2 do not refer to
    the same object and are, therefore, not ==, but <i>equals( )</i> as they have the same value &#8220;Hello&#8221;.
    The s1 == s3 is true, as they both point to the same object due to internal caching. The references s1 and s3 are <u>interned</u>
    and points to the same object in the string pool.
  </p>
  <div style="width: 605px">
    <img src="./images/cj1.png"
      alt="Create a String object as a literal without the  &quot;new&quot; keyword for caching" width="595"
      height="248" sizes="(max-width: 595px) 100vw, 595px">
    <p align="center">Create a String object as a literal without the &quot;new&quot; keyword for caching</p>
  </div>
  <p>
    <span style="font-size: large; color: brown;">Q2. </span> Can you explain how Strings are interned in Java? <br />
    <span style="font-size: large; color: brown;">A2. </span> String class is designed with the <b>Flyweight design
      pattern</b> in mind. Flyweight is all about re-usability without having to create too many objects in memory. A pool
    of Strings is maintained by the String class. When the <i>intern( ) </i>method is invoked, <i>equals(..) </i>method
    is invoked to determine if the String already exist in the pool. If it does then the String from the pool is
    returned instead of creating a new object. If not already in the string pool, a new String object is added to the
    pool and a reference to this object is returned. For any two given strings s1 &amp; s2, <i>s1.intern( )</i> == <i>
      s2.intern() </i>only if <i>s1.equals(s2) </i>is true.
  </p>
  <p>Two String objects are created by the code shown below. Hence s1 == s2 returns false.</p>
  <p></p>
  <textarea rows="2" cols="110">
   //Two new objects are created. Not interned and not recommended.
      String s1 = new String("A");
      String s2 = new String("A");
  </textarea>
  <p>
    s1.intern() == s2.intern() returns <strong>true</strong>, but you have to remember to make sure that you actually do
    intern() all of the strings that you&#8217;re going to compare. It&#8217;s easy to forget to intern() all strings
    and then you can get confusingly incorrect results. Also, why unnecessarily create more objects?
  </p>
  <p>Instead use string literals as shown below to intern automatically:</p>
  <p></p>
  <textarea rows="2" cols="110">
   String s1 = "A";
    String s2 = "A";
  </textarea>
  <p>
    <br /> s1 and s2 point to the same String object in the pool. Hence s1 == s2 returns true.
  </p>
  <p>
    Since interning is automatic for String literals String s1 = "A", the <i>intern( ) </i>method is to be used on
    Strings constructed with new String("A").
  </p>
  <p>
    <span style="font-size: large; color: brown;">Q3. </span> Why String class has been made immutable in Java? <br />
    <span style="font-size: large; color: brown;">A3. </span> For security, performance, and thread-safety.
  </p>
  <p>
    <b>1. Performance</b>: Immutable classes are ideal for representing values of abstract data (i.e. value objects)
    types like numbers, enumerated types, etc. If you need a different value, create a different object. In Java, <i>Integer</i>,
    <i>Long</i>, <i>Float</i>, <i>Character</i>, <i>BigInteger</i> and <i>BigDecimal</i> are all immutable objects.
    Optimization strategies like caching of hashcode, caching of objects, object pooling, etc can be easily applied to
    improve performance. If String were made mutable, string pooling would not be possible as changing the string with
    one reference will lead to the wrong value for the other references.
  </p>
  <div style="width: 605px">
    <img src="./images/cj2.png" alt="String Pool" width="644" height="380" sizes="(max-width: 644px) 100vw, 644px">
    <p align="center">String Pool</p>
  </div>
</body>
</html>