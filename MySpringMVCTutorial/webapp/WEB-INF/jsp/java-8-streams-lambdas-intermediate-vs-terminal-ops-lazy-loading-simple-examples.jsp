<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Java 8 Streams, lambdas, intermediate vs terminal ops, and lazy loading with simple examples</title>
</head>
<body>
  <p>
    A <strong>stream </strong>is an infinite sequence of consumable elements (i.e a data structure) for the consumption
    of an operation or iteration. Any <strong>Collection&lt;T&gt;</strong> can be exposed as a stream. It looks complex,
    but once you get it, it is very simple. The operations you perform on a stream can either be
  </p>
  <p>
    <b>1. Intermediate</b> operations like map, filter, sorted, limit, skip, concat, substream, distinct, peek, etc <u>producing
      another java.util.stream.Stream&lt;T&gt;</u> or a
  </p>
  <p>
    <b>2. Terminal</b> operations like forEach, reduce, collect, sum, max, count, matchAny, findFirst, findAny, etc <u>producing
      an object that is not a stream</u>.
  </p>
  <div style="width: 605px">
    <img src="./images/Streams_intermediate_terminal.png" width="595" height="169"
      sizes="(max-width: 595px) 100vw, 595px">
    <p align="center">Streams: intermediate(blue) &#038; terminal (red)</p>
  </div>
  <p>Basically, you are building a pipeline as in Unix. In Unix we &#8220;pipe&#8221; operations, and in Java 8, we
    stream them.</p>
  <p></p>
  <textarea rows="1" cols="110" readonly="readonly">
  ls -l | grep "Dec" | Sort +4n | more 
  </textarea>

  <p></p>
  <p>
    The <strong>stream() </strong>is a default method added to the <strong>Collection&lt;T&gt;</strong>interface in
    Java 8. The stream() returns a java.util.stream.<strong>Stream&lt;T&gt; </strong> interface with multiple abstract
    methods like filter, map, sorted, collect, etc. The <strong>DelegatingStream&lt;T&gt;</strong> is the implementing
    class.
  </p>
  <p>
    Intermediate operations are <b>lazy operations</b>, which will be executed only after a terminal operation was
    executed. So when you call .filter(i -> i % 3 == 0) the lambda body isn&#8217;t being executed at the moment. It
    will only be executed after a terminal operation was called (<strong>collect</strong>, in the example shown below).
    This is essential to understand from the viewpoint of adding break points in your IDE for debugging purpose.
  </p>
  <p>Go through these examples to get a good handle on the stream concepts.</p>
  <p>
    11 numbers 1 to 10 and an extra 6 are <strong>a) </strong>filtered first for multiples of 3 <strong>b) </strong>filtered
    for values less than 7 <strong>c) </strong>remove duplicates by adding to a Set&lt;T&gt; <strong>d) </strong>print
    the result.
  </p>
  <p>
    i -> i % 3 == 0 is a <strong>lambda expression</strong> used as a predicate to filter only multiples of 3. So,
  </p>
  <p>
    <strong>Q.</strong> what is this &#8220;lambda expression&#8221;?<br /> <strong>A.</strong> In OOP or imperative
    programming, x = x+ 5 makes sense, but in mathematics or <strong>functional programming</strong>, you can't say x =
    x + 5 because if x were to be 2, you can't say that 2 = 2 + 5. In functional programming you need to say f(x) -> x +
    5.
  </p>
  <div style="width: 879px">
    <img src="./images/java8_stream.png" width="869" height="357"
      sizes="(max-width: 869px) 100vw, 869px">
    <p align="center">Java 8 Stream</p>
  </div>
</body>
</html>