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

  <h4>#2. Flow Control:</h4>
  <p>
    <strong>OOP</strong> uses loops, conditions, and method calls. Order of execution is very important.
  </p>
  <p>
    <strong>FP</strong> uses function calls and recursion. Order of execution is less important.
  </p>
  <p>
    <span style="font-size: large; color: brown;">Q4.</span> Where does functional programming shine?<br /> <span
      style="font-size: large; color: brown;">A4. </span><strong>Example:</strong> Here is an example written functional
    programming to extract odd numbers from a given list of numbers and then double each odd number and print each of
    them.
  </p>
  <p>
    <strong>Functional programming</strong> using the <u>Java 8 lambda expressions</u>.
  </p>
  <p>Functional programming with good set of libraries can cut down lot of fluff and focus on just transformations.
    In other words, just tell what you would like to do.</p>
  <p></p>
  <textarea rows="12" cols="110">
      import java.util.Arrays;
      public class NumberTest {
       public static void main(String[] args) {
        Integer[] numbers = {1,2,3,4,5,6};
        Arrays.asList(numbers)   //convert to least
             .stream()          //stream
             .filter((e) -> (e % 2 != 0))   // extract only odd numbers 1, 3, 5
             .map((e) -> (e * 2))           // double the odd numbers 2, 6, 10
             .forEach(System.out::println); // print each doubled number.
       }
      }
  </textarea>

  <p>
    <strong>Shining moment 1:</strong> The FP has much improved readability and maintainability because each function is
    designed to accomplish a specific task for given arguments. OOP or procedural programming to accomplish the same
    will require for loops and will be more verbose.
  </p>
  <p>
    <strong>Shining moment 2:</strong> A functional program can be easily made to run in parallel using the &#8220;fork
    and join&#8221; feature added in Java 7. To improve performance of the above code. All you have to do is use
    .parallelStream( ) instead of .stream( ).
  </p>
  <p></p>

  <textarea rows="20" cols="110">
      import java.util.Arrays;
      public class NumberTest {
         public static void main(String[] args) {
           Integer[] numbers = {1,2,3,4,5,6};
           Arrays.asList(numbers)
              .parallelStream( )      //use fork and join thread pool introduced in Java 7
              .filter(NumberTestUsingParallelStream::isOddNumber)
              .map(NumberTestUsingParallelStream::doubleIt)
              .peek(x -> System.out.println(Thread.currentThread().getName() + " processed " + x))
              .count(); // a terminal operation is required as the intermediate operations are lazily evaluated.
                        // if count() is omitted, nothing gets processed
         }
         private static boolean isOddNumber(int input) {
             return input % 2 != 0;
         }
         private static int doubleIt(int input) {
             return input * 2;
         }
      }
  </textarea>
  <p>
    <strong>Note</strong>: Also, the Java 8 <strong>CompletableFuture</strong> is enabled for functional programming to
    write more elegant asynchronous code in Java.
  </p>
  <p>
    <strong>Shining moment 3:</strong> The code is also easier to refactor as shown below. If you want to switch the
    logic to double it first and then extract the odd numbers, all you have to do is swap the <strong>.filter</strong>
    call with <strong>.map</strong> call.
  </p>
  <p></p>
  <textarea rows="6" cols="110">
      Arrays.asList(numbers)
       .parallelStream()
       .map(NumberTestUsingParallelStream::doubleIt)
       .filter(NumberTestUsingParallelStream::isOddNumber)
       .forEach(System.out::println);
  </textarea>
  <p>
    <strong>Shining moment 4:</strong> Easier testing and debugging. Because pure functions can more easily be tested in
    isolation, you can write test code that calls the pure function with typical values, valid edge cases, and invalid
    edge cases. In the above example, I was using the Java 8 library that was well tested with confidence. I will only
    have to provide unit tests for the static functions &#8220;boolean isOddNumber(int number)&#8221; and &#8220;int
    doubleIt(int number)&#8221; that provide the logic.
  </p>
  <p>Having said this, OO programming and functional programming can co-exist. Both have their strengths and
    weaknesses. So, both compliment each other.</p>
  <p>
    <span style="font-size: large; color: brown;">Q5. </span>What are the characteristics of functional programming you
    need to be familiar with?<br /> <span style="font-size: large; color: brown;">A5. </span>
  </p>
  <ol>
    <li>A focus on what is to be computed rather than how to compute it.&nbsp;</li>
    <li>Function Closure Support</li>
    <li>Higher-order functions</li>
    <li>Use of recursion as a mechanism for flow control</li>
    <li>Referential transparency</li>
    <li>No side-effects</li>
  </ol>

  <div>
    Let&#8217;s look at these one by one:
    <h3>1. A focus on what is to be computed rather then how to compute it</h3>
  </div>

  <textarea rows="9" cols="110">
          Integer[] numbers = {1,2,3,4,5,6};
          //focusses on what to do as opposed to how to do it
          //no fluff like for loops, mutations, etc
          Arrays.asList(numbers)
                 .stream()
                 .filter((e) -> (e % 2 != 0))
                 .map((e) -> (e * 2))
                 .forEach(System.out::println);
  </textarea>
  <p>
    <br /> Extract odd numbers, and multiply each by 2, and the print the result.<br />
  </p>

  <h3>2. Function closure support</h3>
  <p>In order to create closures, you need a language where the function type is a 1st class citizen, where a
    function can be assigned to a variable, and then passed around like any other variables like a string, int or
    boolean.&nbsp;closure is basically a snapshot of the stack at the point that the lambda function is created. Then
    when the function is re-executed or &nbsp;called back the stack is restored to that state before executing the
    function.</p>
  <p>
    The <b>java.util.function</b> package provides a number of functional interfaces like Consumer
    <T>, Function<T , U>, etc to define closures or you can define your own functional interfaces. 
  </p>
  <p></p>
  <textarea rows="25" cols="110">
      import java.util.function.Function;
      public class ClosureTest {
       public static void main(String[] args) {
        //closure 1 that adds 5 to a given number
        Function<Integer , Integer> plus5 = (i) -> (i+5);
        //closure 2 that times by 2 a given number
        Function<Integer , Integer> times2 = (i) -> (i*2);
        //closure 3 that adds 5 and then multiply the result by 2
        Function<Integer , Integer> plus5AndThenTimes2 = plus5.andThen(times2);
        //closure 4 that times by 2 and then adds 5
        Function<Integer , Integer> times2AndThenplus5 = times2.andThen(plus5);
              //callback or execute closure
        //functions plus5, times2, etc can be passed as arguments
        System.out.println("9+5=" + execute(plus5, 9));
        System.out.println("9*2=" + execute(times2, 9));
        System.out.println("(9+5)*2=" + execute(plus5AndThenTimes2, 9));
        System.out.println("9*2+5=" + execute(times2AndThenplus5, 9));
       }
       //functions can be used as method parameters
       private static Integer execute(Function<Integer , Integer> function, Integer number){
        return function.apply(number); //execute the function
       }
      }  
  </textarea>
  <p>
    In pre Java 8, you can use <b>anonymous inner classes </b>to define closures. In Java 8, lambda operators like (i)
    -> (i+5) are used to denote anonymous functions.
  </p>
  <p>
    <span style="font-size: large;"><b>Is currying possible in Java 8?</b></span>
  </p>
  <p>Currying (named after Haskell Curry) is the fact of evaluating function arguments one by one, producing a new
    function with one argument less on each step.</p>
  <p>Java 8 still does not have first class functions, but currying is "practically" possible with verbose type
    signatures, which is less than ideal. Here is a very simple example:</p>
  <p></p>
  <textarea rows="15" cols="110">
      import java.util.function.Function;
      public class CurryTest {
       public static void main(String[] args) {
        Function<Integer ,Function
      <Integer,Integer>> add = (a) -> (b) -> a + b;
        Function<Integer,Integer> addOne = add.apply(1);
        Function<Integer,Integer> addFive = add.apply(5);
        Function<Integer,Integer> addTen = add.apply(10);
        Integer result1 = addOne.apply(2); // returns 3
        Integer result2 = addFive.apply(2); // returns 7
        Integer result3 = addTen.apply(2); // returns 12
        System.out.println("result1 = "  + result1);
        System.out.println("result2 = "  + result2);
        System.out.println("result3 = "  + result3);
       }
      }
  </textarea>
  <p></p><h3>3. Higher order functions</h3>
  <p> In mathematics and computer science, a <b>higher-order function</b> (aka functional form) is a function that does
   at least one of the following:</p>
   <p></p><ol><li>takes one or more functions as an input &#8212; for example g(f(x)), where f and g are functions, 
   and function g composes the function f.&nbsp;</li><li><b>outputs a function</b> &#8212; for example, in the code 
   above <b><i>plus5 </i></b>and <i>plus2 </i>outputs a function</li></ol><p></p>
   
   <textarea rows="7" cols="110">
      Function<Integer, Integer> plus5 = (i) -> (i+5);
      //closure 2 that times by 2 a given number
      Function<Integer, Integer> times2 = (i) -> (i*2);
      
      also
      
      Function<integer integer> plus5AndThenTimes2 = plus5.andThen(times2);
  </textarea>
  
  <p>outputs another function, where the <b>plus5 </b>function takes <b>plus2 </b>function as an input.</p><p></p>
  <h3>4. Use of recursion as a mechanism for flow control</h3><p> Java is a stack based language that supports 
  <b>reentrant (</b>a method can call itself)<b>&nbsp;</b>methods. This means recursion is possible in Java.&nbsp;
  Using recursion you don&#8217;t need a mutable state, hence the problems can be solved in a much simpler fashion 
  compared to using an iterative approach with a loop.
  </p>
  <p></p>
  <textarea rows="20" cols="110">
      import java.util.function.Function;
      import java.util.function.IntToDoubleFunction;
      public class RecursionTest {
          static class Recursive<I> {
              public I func;
          }
          static Function<Integer, Double> factorial = x -> {
              Recursive<IntToDoubleFunction> recursive = new Recursive<IntToDoubleFunction>();
              recursive.func = n -> (n == 0) ? 1 : n
                      * recursive.func.applyAsDouble(n - 1);
              return recursive.func.applyAsDouble(x);
          };
          public static void main(String[] args) {
              Double result = factorial.apply(3);
              System.out.println("factorial of 3 = " + result);
          }
      }
  </textarea>
  <p>Recursion is used in the Lambda expression to calculate the factorial. This is not very straight-forward, 
  and here are the key points to understand the above code.</p><p><strong>1)</strong> In java 8, you have 
  Consumer&lt;T&gt; and Function&lt;T, R&gt; interfaces where a <strong>Consumer</strong> takes an object input 
  and returns nothing and a <strong>Function</strong> takes an Object input and returns a result of type object.</p>
  <p>&#8220;factorial&#8221; is a &#8220;<strong>Function</strong>&#8221; that takes an <strong>input</strong> of 
  type &#8220;Integer&#8221; and result of type &#8220;Double&#8221;. The output needs to be a double because the 
  factorials can get to very large numbers and int and long are not appropriate as this will lead to data overflow.</p>
  <p><strong>2)</strong> In lambda, you can&#8217;t specify a recursion as shown below as you will get a compile error 
  of &#8220;<strong>The method factorial(int) is undefined</strong>&#8221;</p>
  <p></p>
  <textarea rows="4" cols="110">
      static Function<Integer, Double> factorial = x -> {
          return (x == 0) ? 1.0 : x * factorial(x-1);
      };
  </textarea>
  
  <p>The lambda expression and anonymous classes capture local variables by value when they are created. Therefore, 
  it is impossible for them to refer to themselves by capturing a local variable as the value for pointing to itself 
  does not exist yet at the time it is being created.</p>
  
  <p><strong>3)</strong> So, to overcome the above problem, let&#8217;s create a generic helper class that wraps 
  the variable of the functional interface type. The functional interface type is &#8220;<strong>IntToDoubleFunction
  </strong>&#8220;, which converts an int to double.</p>
  
  <p><strong>4)</strong> The &#8220;applyAsDouble&#8221; method applies <strong>this</strong> function to the 
  given argument.</p>
  
  <h3>5. Referential Transparency</h3><p> Referential transparency is a term commonly used in <b>functional programming</b>, 
  which means that given a function and an input value, you will always receive the same output. There is no external 
  state used in the function. In other words, a referentially transparent function is one which only depends on its 
  input. The <b><i>plus5 </i></b>and <b><i>times2 </i></b>functions shown above are referentially transparent.</p>
  <p>A function that reads from a text file and prints the output is not referentially transparent. The external text 
  file could change at any time so the function would be referentially <b>opaque</b></p>
  
  <h3>6. No side-effects</h3><p></p><div> One way to do programming without side effects is to use only immutable 
  classes. In real world, you try to minimize the mutations, but can&#8217;t eliminate them.&nbsp;Lambdas can 
  refer to local variables that are not declared final but are never modified. This is known as &#8220;
  <b>effectively final</b>&#8220;.&nbsp;</div><div></div><div> When you are using methods like <b>reduce </b>on a 
  stream, you need to be aware of the side effects due to parallelism. For example, the following code will have no 
  side effects when run in serial mode.</div>
  
  <textarea rows="6" cols="110">
     public static void main(String[] args) {
       Integer[] numbers = {10, 6};
       Integer result = Arrays.asList(numbers).stream()
                            .reduce(20, (a,b) -> (a - b));
       System.out.println(result);
      }
  </textarea>
  <p>It starts with 20, and then subtracts 10 and 6. <b>20 &#8211; 10 &#8211; 6 = 4</b>; But if you run it again in 
  parallel mode as shown below</p><p></p>
  
  <textarea rows="6" cols="110">
     public static void main(String[] args) {
         Integer[] numbers = {10, 6};
         Integer result = Arrays.asList(numbers).parallelStream()
                                 .reduce(20, (a,b) -> (a - b));
         System.out.println(result);
        }
  </textarea>
  
  <p></p><p>The <b>output </b>will be:</p><p>-4</p><p><b>What happened here? </b>10 &#8211; (20 &#8211; 6) = &nbsp;-4;</p>
  <p>This means the <span style="font-size: large;"><b>reduce </b></span>method on a stream produce side effects when 
  run in parallel for <b><span style="font-size: large;">non-associative</span></b> operations.</p>
  
  <p><b>Q</b>. What is an associative property?<br> <b>A</b>. Associative operations are operations where the order 
  in which the operations were performed does not matter. For example.</p><p><b>Associative</b>:</p>
  <p>3 + (2 + 1) = (3+ 2) + 1<br> 3 * (2 * 1) = (3 * 2) * 1</p>
  <p><b>Non-associative</b>:</p><p>3 &#8211; (2 &#8211; 1) != (3 &#8211; 2) &#8211; 1<br> (4/2)/2 != 4/(2/2)</p>
  <p> Pure functions are functions with no side effects. In computers, <b>idempotence </b>means that applying an 
  operation once or applying it multiple times has the same effect. For example</p><p><b>Idempotent operations</b></p>
  <ul><li>Multiplying a number by zero. No matter how many times you do it, the result is still zero.</li>
  <li>Setting a boolean flag. No matter how many times you do it, the flag stays set.</li>
  <li>Deleting a row from a database with a given ID. If you try it again, the row is still gone.</li>
  <li>Removing an element from a collection.</li></ul>
  <div> In real life where you have concurrent operations going on, you may find that operations you thought were 
  idempotent cease to be so. For example, another thread could unset the value of the boolean flag.</div>
</body>
</html>