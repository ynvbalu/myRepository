<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Java 8 transforming your thinking from OOP to FP</title>
</head>
<body>
  <p>
    One needs to get used to the transformation from <strong>imperative programming </strong>to <strong>functional
      &nbsp;programming</strong>. You like it or not, you will be using functional programming in Java, and interviewers are
    going to quiz you on functional programming. Fortunately, Java is not a fully functional programming language, and
    hence one does not require the full leap to functional programming. Java 8 supports both imperative and functional
    programming approaches.
  </p>
  <p>
    <span style="font-size: large; color: brown;">Q1. </span> What is the difference between imperative and declarative
    programming paradigms?<br /> <span style="font-size: large; color: brown;">A1. </span> <strong>Imperative
    </strong>(or procedural) programming: is about defining the computation how to do something in terms of statements and state
    changes, and as a result what you want to happen will happen.
  </p>

  <p>
    <strong>Declarative programming</strong>: is about declaratively telling what you would like to happen, and let the
    library or functions figure out how to do it. SQL, XSLT and regular expressions are declarative languages.
  </p>
  <p>
    <span style="font-size: large; color: brown;">Q2. </span> Does functional programming use imperative or declarative
    approach?<br /> <span style="font-size: large; color: brown;">A2. </span> Functional programming is a form of
    declarative programming, where <strong>functions are composed of other functions</strong> &#8212; g(f(x)) where g
    and f are functions. An output of one function becomes the input for the composing function. A typical example of
    functional programming, which you may have used is transforming an XML document using XSLT to other forms. The
    composable and isolated XSL style sheets are used for transformation.
  </p>
  <p>
    <span style="font-size: large; color: brown;">Q3. </span> What are the differences between OOP and FP?<br /> <span
      style="font-size: large; color: brown;">A3. </span><br> <br> <img src="./images/OOP_vs_FOP.jpg"
      alt="OOP_vs_FOP" width="826" height="306" sizes="(max-width: 826px) 100vw, 826px" /> <br> <br> In OOP,
    x = x+ 5 makes sense, but in mathematics or functional programming, you can&#8217;t say &nbsp;x = x&nbsp;+ 5 because
    if x were to be 2, you can&#8217;t say that 2 = 2&nbsp;+ 5. In functional programming you need to say f(x) ->
    x&nbsp;+ 5.
  </p>
  <h4>#1. Focus:</h4>
  <p>
    <strong>OOP</strong> focuses on solving business problems by designing classes, interfaces, and contracts. The
    behavior and state are very important to OOP with OO concepts scubas polymorphism, inheritance, and encapsulation.
  </p>
  <p>
    <strong>FP</strong> focuses on computational problems by evaluating functions to transform a collection of data. FP
    avoids state and mutable data, and instead focuses on the composition and application of functions.
  </p>
  <div style="width: 974px">
    <img src="./images/ex.png" alt="Java functional programming focusing on transforming data" width="964" height="121"
      sizes="(max-width: 964px) 100vw, 964px">
    <p align="center">Java functional programming focusing on transforming data</p>
  </div>
</body>
</html>