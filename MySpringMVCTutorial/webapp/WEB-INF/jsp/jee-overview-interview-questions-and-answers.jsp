<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>8 Java EE (aka JEE) Overview interview questions &
	answers</title>
</head>
<body>
	<p>
		<span style="font-size: large; color: brown;">Q1.</span> When a
		company requires Java EE experience, what are they really asking for?<br />
		<span style="font-size: large; color: brown;">A1.</span> Java EE (i.e.
		Enterprise Edition) is a collection of specifications for developing
		and deploying enterprise applications. In general, Java enterprise
		applications refer to applications written in Java &#038; packaged as
		war or ear files and hosted on application servers like Tomcat, JBoss,
		IBM Webspehere, etc. Some of the JEE core technologies &#038; APIs are
		JPA, JDBC, JNDI, EJBs, RMI, JSP, JSF, Java Servlets, XML, JMS, Java
		IDL, JTS, JTA, JavaMail, and JAF.
	</p>
	<p>
		<span style="font-size: large; color: brown;">Q2.</span> Full stack
		developers are in more demand. What does the term &#8220;<strong>full
			stack Java developer</strong>&#8221; mean?<br /> <span
			style="font-size: large; color: brown;">A2.</span> Have you seen job
		advertisements like&#8230;.
	</p>
	<p>
		<span style="font-size: large; color: #21610B;">&#8220;engineering
			team is looking for a <strong>Full Stack Java Developer </strong>&#8230;&#8230;&#8221;
		</span>
	</p>
	<p>Don&#8217;t be too overwhelmed by companies looking for the
		following skill sets in a full-stack developer. This site covers many
		of these topics to increase your awareness and skills as a full stack
		Java developer:</p>
	<p>
		<span style="font-size: large; color: #21610B;">Java, JEE,
			Spring, Hibernate, Maven, Relational databases, NoSQL databases, SQL,
			JavaScript, HTML, CSS, JSON, XML, Unix, UML , ERD for data modelling,
			AngularJS, Hadoop, AWS, Node.js, etc. </span>
	</p>
	<p>Some are mandatory, whilst the others are optional and you need
		to be flexible enough to learn those skills. Full stack developer is a
		very loose term with experience in 2 more of the categories listed
		below.</p>
	<p>
		<strong>1)</strong> UI Frameworks such as React, AngularJS and JQuery.
		Javascript, HTML, and CSS skills are essential.
	</p>
	<p>
		<strong>2)</strong> Web service development skills using Java, JEE,
		Spring, Hibernate, RDBMS databases &#038; SQL.
	</p>
	<p>
		<strong>3)</strong> Integration skills like message queues, JMS (E.g.
		Webspeher MQ, ActiveMQ, etc), ESB (Apache Camel, Mule), FIX Protocol,
		etc
	</p>
	<p>
		<strong>4)</strong> ETL and ELT skills like Spring batch, Hadoop,
		Spark, Sqoop, Flume, etc.
	</p>
	<p>
		<strong>5)</strong> Infrastructure skills like Unix, AWS, Cloud
		computing, System monitoring, application/system, security, etc.
	</p>
	<p>
		The high-level architecture diagram in <strong>Q5</strong> shows that
		user interface was written in JavaScript based
		technologies/frameworks, and web services were written in Java,
		Spring, Hibernate based technologies/frameworks. Developing this
		application requires a <strong>full stack web developer</strong>. The
		back-end systems can be integrated with the other systems via
		messaging, RESTful web services, and ETL as explained in <a
			href="http://www.java-success.com/java-architecture-interview-questions-answers/">7+
			Java integration styles &#038; patterns interview questions &#038;
			answers</a>.
	</p>
	<h4>Transition from:</h4>
	<p>Core Java Developer =&gt; Full stack Web Developer =&gt; Full
		stack Java developer</p>
	<p>
		<span style="font-size: large; color: brown;">Q3.</span> What is Java
		EE? What are JEE technologies and services?<br /> <span
			style="font-size: large; color: brown;">A3.</span> Java platform
		Enterprise Edition or Java EE is a Java computing platform providing
		APIs and run time services.
	</p>

	<div style="width: 708px">
		<img src="./images/cj1.png" width="699" height="753"
			sizes="(max-width: 699px) 100vw, 699px">
		<p align="center">JEE big picture</p>
		<h4>1) JEE APIs like</h4>
		<p>
			&#8212; javax.servlet.* for <strong>Servlet &amp; JSP</strong> to
			service HTTP protocol.
		</p>
		<p>
			&#8212; javax.faces.* for Java Server Faces (<strong>JSF</strong>) to
			create user interfaces.
		</p>
		<p>&#8212; javax.el (Expression Language) used for binding JSF
			components.</p>
		<p>
			&#8212; javax.enterprise.inject.* for defining injection annotations
			for the Contexts and Dependency Injects (<strong>CDI</strong>).
		</p>
		<p>
			&#8212; javax.enterprise.context for <strong>CDI</strong>
		</p>
		<p>
			&#8212; javax.ejb.* the Enterprise Java Beans (i.e. <strong>EJB</strong>s)
		</p>
		<p>
			&#8212; javax.persistence.* for Java Persistence API (i.e. <strong>JPA</strong>)
		</p>
		<p>
			&#8212; javax.transaction.* for Java Transaction API (<strong>JTA</strong>)
		</p>
		<p>
			&#8212; javax.jms.* for Java Message Service (i.e. <strong>JMS</strong>)
			to send and receive messages from Message Oriented Middlewares.
		</p>
		<p>
			javax.resource.* for Java EE Connector Architecture (<strong>JCA</strong>)
			to integrate with EIS(Enterprise Integration Systems) systems.
		</p>
		<h4>2) runtime environment</h4>
		<p>for developing and running enterprise software packages like
			ear (Enterprise ARchive), war (Web ARchive), or jar (Java ARchive)
			deployed to an JEE application server like JBoss server having web
			and EJB containers. The modular components running in an application
			server make use of annotations and conventions to configure or wire
			up all the components and modules. Optionally, XML based deployment
			descriptor files can be used to override annotations.</p>
	</div>
</body>
</html>