<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring</title>
</head>
<body>
  <span style="font-size: large; color: brown;">Q1.</span> What do you understand by the terms Dependency Inversion
  Principle (DIP), Dependency Injection (DI) and Inversion of Control (IoC) container?
  <br>
  <span style="font-size: large; color: brown;">A1</span> The differences are very subtle and can be hard to understand.
  Hence, explained via code samples.
  <br>
  <div style="width: 924px">
    <img src="./images/DIP.png" alt="DIP, DI &amp; IoC" width="914" height="501" sizes="(max-width: 914px) 100vw, 914px">
    <p>DIP, DI &amp; IoC</p>
  </div>
  <br>
  <p>
    <span style="font-size: 20px; color: green"><strong>1)</strong> Dependency Inversion Principle (<strong>DIP):</strong>
    </span> is one of the 6 OO design principles
    </a>
    abbreviated as SOLI<u>D</u>, and D stands for DIP meaning that we should always only <u>rely on interfaces and
      not on their implementations</u>. The idea of DIP is that higher layers of your application should not directly depend
    on lower layers. DIP is the principle that guides us towards DI pattern. You will see in the example below that the
    higher layer module MyServiceImpl depends on the lower layer module interface <strong>Processor</strong> and <u>NOT
      on the implementations</u> XmlProcessor &amp; JsonProcessor. This is commented on the code as // code to interface for
    the understanding.
  </p>
  <p>
    <span style="font-size: 20px; color: green"><strong>2)</strong> Dependency Injection (<strong>DI</strong>): </span>
    is a <u>design pattern</u> where instead of having your objects create a dependency or asking a factory object to
    make one for you, you pass the needed dependencies into the constructor or via setters <u>from outside the class</u>.
    This is achieved by defining the dependencies as interfaces, and then injecting in a concrete class implementing
    that interface via a constructor (i.e. constructor injection) or a setter method (i.e. setter injection). Dependency
    Injection is a design pattern that allows us to write loosely coupled code for better maintainability,
  </p>

  <p>
    <span style="font-size: 20px; color: green"><strong>3) </strong> Inversion of Control (<strong>IoC</strong>):
    </span> is a software <u>design principle</u> where the framework controls the program flow. Spring framework, Guice, etc
    are IoC containers that implement the IoC principle.
  </p>
  <p>
    <span style="font-size: large; color: brown;">Q2.</span> What are you Inverting in IoC?<br> <span
      style="font-size: large; color: brown;">A2.</span> <strong>Flow of control</strong> is inverted by dependency
    injection because you are effectively delegating dependencies to some external system (e.g. IoC container or Service
    Locator) .
  </p>
  <p>
    <span style="font-size: large; color: brown;">Q3.</span> What are the different implementation patterns of IoC
    principle?<br> <span style="font-size: large; color: brown;">A3.</span> The two <strong>implementation
      patterns</strong> of the IoC design principles are
  </p>
  <ol>
    <li>Dependency Injection (<strong>DI</strong>) pattern: A class is given its dependencies from outside. It
      neither knows, nor cares where the dependencies are coming from.
    </li>
    <li>Service Locator (<strong>SL</strong>) pattern: A class is still responsible for creating its dependencies.
      It just uses the service locator to do it.
    </li>
  </ol>
</body>
</html>