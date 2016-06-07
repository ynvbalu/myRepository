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
  <p>
    The core of the Spring Framework is its Inversion of Control (Ioc) container. The Spring IoC container manages Java
    objects from their instantiation to destruction via its BeanFactory. Java components that are instantiated by the
    IoC container are called beans, and the IoC container manages a beans scope (e.g. prototype vs singleton), lifecycle
    events (e.g. initialization, method callbacks & shutdown), and any AOP (Aspect Oriented Programming) features if
    configured. <br> <br> The key focus of both types of IoC is to loosely couple dependencies among
    components like MyApp, MyService, and Procesor as per the examples in the package (com.mytutorial)
  </p>
  <p>
    <span style="font-size: large; color: brown;">Q4.</span> What are the different types of dependency injections?<br />
    <span style="font-size: large; color: brown;">A4.</span> There are 4 types of dependency injection. Spring supports
    3 types. 1, 2 &#038; 4 shown below.
  </p>
  <p>
    <strong>1)</strong> <strong>Constructor Injection</strong> (e.g. Spring): Dependencies are provided as <strong>constructor
      parameters</strong>.
  </p>
  <p></p>
  <p>
    <strong>2) </strong><strong>Setter Injection</strong> (e.g. Spring): Dependencies are assigned through <strong>setter
      methods</strong>.
  </p>
  <p></p>
  <p></p>
  <p>
    <strong>3)</strong> <strong>Interface Injection</strong> (e.g. Avalon): Injection is done through an interface.
  </p>
  <p>
    <strong>4)</strong> <strong>Field injection</strong>: Using annotations on fields and parameters.
  </p>
  <p></p>
  <p></p>
  <p>
    Spring supports <strong>1)</strong> Constructor Injection, <strong>2)</strong> Setter Injection &#038; <strong>4)
    </strong>Field injection with annotations.
  </p>
  <p>
    <span style="font-size: large; color: brown;">Q5.</span> Which ones are the most commonly used DIs?<br /> <span
      style="font-size: large; color: brown;">A5.</span> <strong>1)</strong> Constructor Injection, <strong>2)</strong>
    Setter Injection &#038; <strong>4) </strong>Field injection with annotations.
  </p>
  <p>
    <span style="font-size: large; color: brown;">Q6.</span> When will you favor DI type &#8220;Constructor
    Injection&#8221; over &#8220;Setter Injection&#8221;?<br /> <span style="font-size: large; color: brown;">A6.</span>
    Using constructor injection allows you to hide immutable fields from users of your class. Immutable classes
    don&#8217;t declare setter methods. This also enforces that you have the valid objects at the construction time. It
    also prompts you to rethink about your design when you have too many constructor parameters.
  </p>
  <p>
    <span style="font-size: large; color: brown;">Q7.</span> When will you favor DI type &#8220;Setter Injection&#8221;
    over &#8220;Constructor Injection&#8221;?<br /> <span style="font-size: large; color: brown;">A7.</span> In some
    scenarios, the constructors may get a lot of parameters, which force you to create a lot of overloaded constructors
    for every way the object might be created. In these scenarios setter injection can be favored over constructor
    injection, but having too many constructor parameters may be an indication of a bad design.
  </p>
  <p>
    <span style="font-size: large; color: brown;">Q8.</span> Can you describe the high level Spring architecture?<br />
    <span style="font-size: large; color: brown;">A8.</span> A Spring Bean represents a POJO (Plain Old Java Object)
    performing useful operation(s). All Spring Beans reside within a Spring IoC Container. The Spring Framework hides
    most of the complex infrastructure and the communication that happens between the Spring Container and the Spring
    Beans. The Core Container is shown below.
  </p>
  <br>
  <div style="width: 924px">
    <img src="./images/Spring-architecture.png" alt="Spring Architecture" width="874" height="757"
      sizes="(max-width: 874px) 100vw, 874px">
    <p align="center">Spring Architecture</p>
  </div>
  <p>Spring framework architecture is modular with layers like core, data access &#038; integration, web/remoting,
    and other miscellaneous support.</p>
  <p>
    <span style="font-size: large; color: brown;">Q9.</span> What are the packages (i.e. jar files) required in your
    project to get started with a Spring application?<br /> <span style="font-size: large; color: brown;">A9.</span>
    In order to get started with Spring, your maven pom.xml file should at least have the following <strong>core
      Spring packages</strong>:
  </p>
  <p></p>
</body>
</html>