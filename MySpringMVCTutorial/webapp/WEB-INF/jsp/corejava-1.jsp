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
  <p>
    <strong>In Java 6</strong> &#8212; all interned strings were stored in the <strong>PermGen</strong> - the fixed size
    part of heap mainly used for storing loaded classes and string pool.
  </p>
  <p>
    <strong>In Java 7</strong> - the string pool was relocated to the <strong>heap</strong>. So, you are not restricted
    by the limited size.
  </p>

  <p>
    <b>2. Thread safety </b> as immutable classes are inherently thread safe as they cannot be modified once created.
    They can only be used as a read only objects. They can easily be shared among multiple threads for better
    scalability.
  </p>
  <p>
    <b>3. Errors &#038; Security Vulnerabilities</b>: In Java you pass sensitive information like file names, host
    names, login names, passwords, customer account numbers, etc as a string object. If String were not immutable, a
    password or account number can be accidentally &#038; easily changed, which can cause errors and security
    vulnerabilities.<br />
  </p>
  <p>
    <span style="font-size: large; color: brown;">Q4. </span> In Java, what purpose does the key words <b>final</b>, <b>finally</b>,
    and <b>finalize</b> fulfill?<br /> <span style="font-size: large; color: brown;">A4. </span> &#8216;<b>final</b>&#8216;
    makes a variable reference not changeable, makes a method not overridable, and makes a class not inheritable.
  </p>

  <p>
    &#8216;<b>finally</b>&#8216; is used in a try/catch statement to almost always execute the code. Even when an
    exception is thrown, the finally block is executed. This is used to close non-memory resources like file handles,
    sockets, database connections, etc till Java 7. This is is no longer true in Java 7.
  </p>
  <p>
    Java 7 has introduced the <i>AutoCloseable</i> interface to avoid the unsightly try/catch/finally(within finally
    try/catch) blocks to close a resource. It also prevents potential resource leaks due to not properly closing a
    resource.
  </p>
  <p>
    <b>// pre Java 7<br />
    </b>
  </p>
  <p></p>
  <textarea rows="25" cols="110">
     BufferedReader br = null;
     try {
       File f = new File("c://temp/simple.txt");
       InputStream is = new FileInputStream(f);
       InputStreamReader isr = new InputStreamReader(is);
       br = new BufferedReader(isr);
       String read;
       while ((read = br.readLine()) != null) {
          System.out.println(read);
       }
     } catch (IOException ioe) {
       ioe.printStackTrace();
     } finally {
       //Hmmm another try catch. unsightly
       try {
         if (br != null) {
            br.close();
       }
       } catch (IOException ex) {
         ex.printStackTrace();
      }
    }
  </textarea>
  <p>
    <b>Java 7</b> - try can have <b><i>AutoCloseble</i></b> types. <i>InputStream</i> and <i>OutputStream</i> classes
    now implements the Autocloseable interface.
  </p>
  <p></p>
  <textarea rows="12" cols="110">
     try (InputStream is = new FileInputStream(new File("c://temp/simple.txt"));
     InputStreamReader isr = new InputStreamReader(is);
     BufferedReader br2 = new BufferedReader(isr);) {
       String read;
       while ((read = br2.readLine()) != null) {
          System.out.println(read);
       }
     }
     catch (IOException ioe) {
       ioe.printStackTrace();
     }
  </textarea>
  <p></p>
  <p>
    try can now have multiple statements in the parenthesis and each statement should create an object which implements
    the new <i>java.lang.AutoClosable</i> interface. The <i><b>AutoClosable</b></i> interface consists of just one
    method. void close() throws <i>Exception</i> {}. Each <i>AutoClosable</i> resource created in the try statement will
    be automatically closed without requiring a finally block. If an exception is thrown in the try block and another
    Exception is thrown while closing the resource, the first Exception is the one eventually thrown to the caller.
    Think of the <i>close( ) </i>method as implicitly being called as the last line in the try block.
  </p>
  <p>
    &#8216;<b>finalize</b>&#8216; is called when an object is garbage collected. You rarely need to override it. It
    should not be used to release non-memory resources like file handles, sockets, database connections, etc because
    Java has only a finite number of these resources and you do not know when the garbage collection is going to kick in
    to release these non-memory resources through the <i>finalize( ) </i>method.
  </p>
  <p>So, final and finally are used very frequently in your Java code, but the key word finalize is hardly or never
    used.</p>
  <p>
    <span style="font-size: large; color: brown;">Q5. </span> What value will the following method return?
  </p>
  <p></p>
  <textarea rows="8" cols="110">
     public static int getSomeNumber( ){
       try{
         return 2;
       } finally {
         return 1;
       }
      }
  </textarea>
  <p>
    <span style="font-size: large; color: brown;">A5. </span> 1 is returned because &#8216;finally&#8217; has the right
    to override any exception/returned value by the try..catch block. It is a bad practice to return from a finally
    block as it can suppress any exceptions thrown from a try..catch block. For example, the following code will not
    throw an exception.
  </p>
  <p></p>
  <textarea rows="8" cols="110">
     public static int getSomeNumber( ){
        try{
           throw new RuntimeException( );
        } finally {
           return 12;
        }
       }
  </textarea>
  <p></p>
  <p>
    <span style="font-size: large; color: brown;">Q6. </span> What can prevent execution of a code in a finally block? <br />
    <span style="font-size: large; color: brown;">A6. </span> <b> a)</b> An end-less loop.
  </p>
  <p></p>
  <textarea rows="13" cols="110">
     public static void main(String[ ] args) {
         try {
           System.out.println("This line is printed .....");
           //endless loop
           while(true){
             //...
           }
         }
         finally{
           System.out.println("Finally block is reached."); // won't reach
         }
        }
  </textarea>
  <p></p>
  <p>
    <b>b)</b> <i>System.exit(1) </i>statement.
  </p>
  <p></p>
  <textarea rows="13" cols="110">
      public class Temp {
       public static void main(String[ ] args) {
         try {
           System.out.println("This line is printed .....");
           System.exit(1);
         }
         finally{
           System.out.println("Finally block is reached.");// won't reach
         }
       }
      }
  </textarea>
  <p>
    <br /> <b>c)</b> Thread death or turning off the power to CPU.<br /> <b>d)</b> An exception arising in a finally
    block itself.<br /> <b>e)</b> <i>Process p = Runtime.getRuntime( ).exec(&#8220;</i>
    <o command="" kill="" s=""> <i>&#8220;)</i>; 
  </p>
  <p>
    If using Java 7 or later editions, use <i>AutoCloseable</i> statements within the try block.
    </o>
  </p>

  <p>
    It is important to understand <strong>compile-time</strong> vs <strong>runtime</strong> from core and enterprise
    Java interview questions &#038; answers perspective. <br /> <br /> <span style="font-size: large; color: brown;">Q7.
    </span> Can you describe "method overloading" versus "method overriding"? Does it happen at <strong>compile time</strong>
    or <strong>runtime</strong>?<br /> <span style="font-size: large; color: brown;">A7. </span> <b>Method
      overloading</b>: Overloading deals with multiple methods in the same class with the same name but different method
    signatures. Both the below methods have the same method names but different method signatures, which mean the
    methods are overloaded.
  </p>
  <p></p>
  <textarea rows="4" cols="110">
    public class {
      public static void evaluate(String param1); // method #1
      public static void evaluate(int param1); // method #2
    }
  </textarea>
  <p></p>
  <p>This happens at compile-time. This is also called compile-time polymorphism because the compiler must decide
    how to select which method to run based on the data types of the arguments. If the compiler were to compile the
    statement:</p>
  <p></p>
  <textarea rows="2" cols="110">
    evaluate("My Test Argument passed to param1");
  </textarea>
  <p></p>
  <p>it could see that the argument was a string literal, and generate byte code that called method #1.</p>
  <p>
    Overloading lets you define the <u><b>same operation in different ways for different data</b></u>.
  </p>
  <p>
    <b>Method overriding:</b> Overriding deals with two methods, one in the parent class and the other one in the child
    class and has the same name and signatures. Both the below methods have the same method names and the signatures but
    the method in the subclass MyClass overrides the method in the superclass BaseClass.
  </p>
  <p></p>
  <textarea rows="4" cols="110">
    public class A {
     public int compute(int input) { //method #3
       return 3 * input;
     }
    }
  </textarea>
  <textarea rows="4" cols="110">
    public class B extends A {
       @Override
       public int compute(int input) { //method #4
         return 4 * input;
       }
      }
  </textarea>
  <p></p>
  <p>This happens at runtime. This is also called runtime polymorphism because the compiler does not and cannot know
    which method to call. Instead, the JVM must make the determination while the code is running.</p>
  <p>The method compute(..) in subclass "B" overrides the method compute(..) in super class "A". If the compiler has
    to compile the following method,</p>
  <p></p>
  <textarea rows="3" cols="110">
      public int evaluate(A reference, int arg2) {
        int result = reference.compute(arg2);
      }
  </textarea>
  <p></p>
  <p>The compiler would not know whether the input argument &#8216;reference&#8217; is of type "A" or type "B". This
    must be determined during runtime whether to call method #3 or method #4 depending on what type of object (i.e.
    instance of Class A or instance of Class B) is assigned to input variable "reference".</p>
  <p></p>
  <textarea rows="3" cols="110">
      A obj1 = new B( );
      A obj2 = new A( );
      evaluate(obj1, 5); // 4 * 5 = 20. method #4 is invoked as stored object is of type B
      evaluate(obj2, 5); // 3 * 5 = 15. method #3 is invoked as stored object is of type A
  </textarea>
  <p>
    Overriding lets you define <u><b>the same operation in different ways for different object types</b></u>.
  </p>
  <p>
    <span style="font-size: large; color: brown;">Q8. </span> What do you know about class loading? Explain Java class
    loaders? If you have a class in a package, what do you need to do to run it? Explain dynamic class loading? <br />
    <span style="font-size: large; color: brown;">A8. </span> Class loaders are hierarchical. Classes are introduced
    into the JVM as they are referenced by name in a class that is already running in the JVM. So, how is the very first
    class loaded? The very first class is specially loaded with the help of static <i>main( ) </i>method declared in
    your class. All the subsequently loaded classes are loaded by the classes, which are already loaded and running. A
    class loader creates a namespace. All JVMs include at least one class loader that is embedded within the JVM called
    the primordial (or bootstrap) class loader. The JVM has hooks in it to allow user defined class loaders to be used
    in place of primordial class loader. Let us look at the class loaders created by the JVM.
  </p>
  <div style="width: 986px">
    <img src="./images/cl.png" alt="Class Loading" width="976" height="535" sizes="(max-width: 976px) 100vw, 976px">
    <p align="center">Java Class Loader Basics</p>
  </div>
  <p>Class loaders are hierarchical and use a delegation model when loading a class. Class loaders request their
    parent to load the class first before attempting to load it themselves. When a class loader loads a class, the child
    class loaders in the hierarchy will never reload the class again. Hence uniqueness is maintained. Classes loaded by
    a child class loader have visibility into classes loaded by its parents up the hierarchy but the reverse is not true
    as explained in the above diagram.</p>
  <p>
    <span style="font-size: large; color: brown;">Q9. </span> Explain static vs. dynamic class loading? <br /> <span
      style="font-size: large; color: brown;">A9. </span> Classes are <b>statically loaded</b> with Java's "new"
    operator.
  </p>
  <p></p>
  <textarea rows="6" cols="110">
      class MyClass {
         public static void main(String args[]) {
           Car c = new Car( );
         }
        }
  </textarea>
  <p>
    <b>Dynamic loading</b> is a technique for programmatically invoking the functions of a class loader at run time. Let
    us look at how to load classes dynamically.
  </p>
  <p></p>
  <textarea rows="3" cols="110">
      //static method which returns a Class
      Class.forName (String className);
  </textarea>
  <p></p>
  <p>The above static method returns the class object associated with the class name. The string className can be
    supplied dynamically at run time. Unlike the static loading, the dynamic loading will decide whether to load the
    class Car or the class Jeep at runtime based on a properties file and/or other runtime conditions. Once the class is
    dynamically loaded the following method returns an instance of the loaded class. It's just like creating a class
    object with no arguments.</p>

  <textarea rows="3" cols="110">
        // A non-static method, which creates an instance of a
        // class (i.e. creates an object).
        class.newInstance ( );
    </textarea>

  <p></p>
  <p>
    Static class loading throws "<b><i>NoClassDefFoundError</i></b>" if the class is not found and the dynamic class
    loading throws "<b><i>ClassNotFoundException</i></b>" if the class is not found.
  </p>
  <p>
    <span style="font-size: large; color: brown;">Q10. </span> What tips would you give to someone who is experiencing a
    class loading or "Class Not Found" exception? <br /> <span style="font-size: large; color: brown;">A10. </span> "<i>
      <b>ClassNotFoundException</b>
    </i>" could be quite tricky to troubleshoot. When you get a <i>ClassNotFoundException</i>, it means the JVM has
    traversed the entire classpath and not found the class you&#8217;ve attempted to reference.
  </p>
  <p>
    <b>1)</b> Stand alone Java applications use -cp or -classpath to define all the folders and jar files to look for.
    In windows separated by &#8220;;&#8221; and in Unix separated by &#8220;:&#8221;.
  </p>
  <p></p>
  <textarea rows="2" cols="110">
  java -classpath "C:/myproject/classes;C:/myproject/lib/my-utility.jar;C:/myproject/lib/my-dep.jar" MyApp
  </textarea>
  <p>
    <b>2)</b> Determine the jar file that should contain the class file within the classpath &#8212; war/ear archives
    and application server lib directories. Search recursively for the class.
  </p>
  <p></p>
  <textarea rows="2" cols="110">
    $ find . -name "*.jar" -print -exec jar -tf '{}' \; | grep -E "jar$|String\.class"
  </textarea>
  <p></p>
  <p>
    <b>3)</b> Check the version of the jar in the manifest file MANIFEST.MF, access rights (e.g. read-only) of the jar
    file, presence of multiple versions of the same jar file and any jar corruption by trying to unjar it with
    &#8220;jar -xvf &#8230;&#8221;. If the class is dynamically loaded with <span style="font-size: small;"><span
      style="font-family: inherit;"><code class="plain">Class.forName("com.myapp.Util")</code></span></span>, check if you have
    spelled the class name correctly.
  </p>
  <p>
    <b>4) </b>Check if the application is running under the right JDK? Check the JAVA_HOME environment property
  </p>
  <p></p>
</body>
</html>