<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Introduction to Spring Boot</title>
</head>
<body>
  <p>
    <strong>Spring Boot</strong> is a completely new project from Pivotal Team(The Spring Team). It is a Framework
    developed on top of existing Spring Framework.<br /> It uses completely new development model to make Java
    Development very easy by avoiding some tedious development steps and boilerplate code and configuration.
  </p>
  <h3>What is Spring Boot?</h3>
  <p>
    Spring Boot is a Framework from &#8220;The Spring Team&#8221; to ease the bootstrapping and development of new
    Spring Applications.<br /> It provides defaults for code and annotation configuration to quick start new Spring
    projects within no time. It follows &#8220;Opinionated Defaults Configuration&#8221; Approach to avoid lot of
    boilerplate code and configuration to improve Development, Unit Test and Integration Test Process.
  </p>
  <h3>What is NOT Spring Boot?</h3>
  <p>
    Spring Boot Framework is not implemented from the scratch by The Spring Team, rather than implemented on top of
    existing Spring Framework (Spring IO Platform).<br /> It is not used for solving any new problems. It is used to
    solve same problems like Spring Framework.
  </p>
  <h3>Why Spring Boot?</h3>
  <ul>
    <li>To ease the Java-based applications Development, Unit Test and Integration Test Process.</li>
    <li>To reduce Development, Unit Test and Integration Test time by providing some defaults.</li>
    <li>To increase Productivity.</li>
  </ul>
  <h3>Advantages of Spring Boot:</h3>
  <ul>
    <li>It is very easy to develop Spring Based applications with Java or Groovy.</li>
    <li>It reduces lots of development time and increases productivity.</li>
    <li>It avoids writing lots of boilerplate Code, Annotations and XML Configuration.</li>
    <li>It is very easy to integrate Spring Boot Application with its Spring Ecosystem like Spring JDBC, Spring
      ORM, Spring Data, Spring Security etc.</li>
    <li>It follows &#8220;Opinionated Defaults Configuration&#8221; Approach to reduce Developer effort</li>
    <li>It provides Embedded HTTP servers like Tomcat, Jetty etc. to develop and test our web applications very
      easily.</li>
    <li>It provides CLI (Command Line Interface) tool to develop and test Spring Boot(Java or Groovy) Applications
      from command prompt very easily and quickly.</li>
    <li>It provides lots of plugins to develop and test Spring Boot Applications very easily using Build Tools like
      Maven and Gradle</li>
    <li>It provides lots of plugins to work with embedded and in-memory Databases very easily.</li>
  </ul>
  <p>In Simple Terminology, What Spring Boot means</p>

  <div style="width: 1498px">
    <img src="./images/WhatIsSpringBoot1.png" alt="What Is Spring Boot" width="1498" height="186"
      sizes="(max-width: 1498px) 100vw, 1498px">
    <p align="center">What Is Spring Boot</p>
  </div>
  <p>
    That means Spring Boot is nothing but existing Spring Framework + Some Embedded HTTP Servers (Tomcat/Jetty etc.)
    &#8211; XML or Annotations Configurations.<br /> Here minus means we don&#8217;t need to write any XML
    Configuration and few Annotations only.
  </p>
  <h3>Main Goal of Spring Boot:</h3>
  <p>The main goal of Spring Boot Framework is to reduce Development, Unit Test and Integration Test time and to
    ease the development of Production ready web applications very easily compared to existing Spring Framework, which
    really takes more time.</p>
  <ul>
    <li>To avoid XML Configuration completely</li>
    <li>To avoid defining more Annotation Configuration(It combined some existing Spring Framework Annotations to a
      simple and single Annotation)</li>
    <li>To avoid writing lots of import statements</li>
    <li>To provide some defaults to quick start new projects within no time.</li>
    <li>To provide Opinionated Development approach.</li>
  </ul>
  <p>By providing or avoiding these things, Spring Boot Framework reduces Development time, Developer Effort and
    increases productivity.</p>
  <h3>Limitation/Drawback of Spring Boot:</h3>
  <p>
    Spring Boot Framework has one limitation.<br /> It is very tough and time consuming process to convert existing or
    legacy Spring Framework projects into Spring Boot Applications. It is applicable only for brand new/Greenfield
    Spring Projects.
  </p>
  <p>To Start Opinionated Approach to create Spring Boot Applications, The Spring Team (The Pivotal Team) has
    provided the following three approaches.</p>
  <ul>
    <li>Using Spring Boot CLI Tool</li>
    <li>Using Spring STS IDE</li>
    <li>Using Spring Initializr Website</li>
  </ul>
  <p>
    We will discuss one by one in detail with some good examples in coming posts. We can find Spring Initializr Website
    at: http://start.spring.io/<br /> We can develop two flavors of Spring-Based Applications using Spring Boot
  </p>
  <ul>
    <li>Java-Based Applications</li>
    <li>Groovy Applications</li>
  </ul>
  <p>We can use Spring Boot CLI or Spring STS IDE or Spring Initializr Website to develop Spring Boot Groovy
    Applications. However, we can use Spring STS IDE or Spring Initializr Website to develop Spring Boot Java
    Applications.</p>
  <p>Anyhow, Groovy is also JVM language almost similar to Java Language. We can combine both Groovy and Java into
    one Project. Because like Java files, Groovy files are finally compiled into *.class files only. Both *.groovy and
    *.java files are converted to *.class file (Same byte code format).</p>

  <div style="width: 1037px">
    <img src="./images/java_groovy_compiler2.png" alt="java groovy compiler" width="1037" height="350"
      sizes="(max-width: 1037px) 100vw, 1037px">
    <p align="center">java groovy compiler</p>
    <p>
    Spring Boot Framework Programming model is inspired by Groovy Programming model. Spring Boot internally uses some
    Groovy based techniques and tools to provide default imports and configuration.
    </p>
    <p>Spring Boot Framework also combined existing Spring Framework annotations into some simple or single
      annotations. We will explore those annotations one by one in coming posts with some real-time examples.</p>
    <p>Spring Boot Framework drastically changes Spring-Java Based Applications Programming model into new
      Programming model. As of now, Spring Boot is at initial stage only but future is all about Spring Boot only.</p>
    <p>Happy Spring Boot Framework Learning!</p>
    http://www.journaldev.com/7969/introduction-to-spring-boot
  </div>
</body>
</html>