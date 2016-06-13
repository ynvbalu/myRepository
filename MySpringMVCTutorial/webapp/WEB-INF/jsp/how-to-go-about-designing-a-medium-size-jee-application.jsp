<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>How to go about designing a medium size JEE application</title>
</head>
<body>
  <p>A very popular open-ended question to judge your Java/JEE experience.</p>
  <p>
    <b><span style="font-size: large;">Q</span></b>. How would you go about designing a medium sized JEE application?<br />
    <b><span style="font-size: large;">A</span></b>. Don&#8217;t start with 3-tier architecture, logical layers,
    Spring/Hibernate framework etc. The phases of designing any systems are:
  </p>
  <p>
    <span style="color: #036303;">Requirements Gathering</span> =&gt; <span style="color: #036303;">Baseline
      Architecture</span> =&gt; <span style="color: #036303;">Design Alternatives &#038; impact analysis</span> =&gt; <span
      style="color: #036303;">Choice of technologies/frameworks/tools etc</span> =&gt; <span style="color: #036303;">Capacity/Infrastructure
      planning</span> =&gt; <span style="color: #036303;">Logical/physical modelling</span>.
  </p>

  <h5>#1. Ask the right questions: Gather functional &#038; non functional requirements</h5>
  <p>
    <strong>Non functional requirements</strong> include:
  </p>
  <ol>
    <li>How many transactions per minute or hour should the system handle?</li>
    <li>How many concurrent users should it handle?</li>
    <li>Where does it get the data from? database, web services, topics/queues, etc. What integration styles are
      required? Identify the communication protocols and message formats between the client and server.</li>
    <li>Any requirements to externalize business rules?</li>
    <li>Any special security requirements like two factor authentication, two-way SSL, WS-security to encrypt
      security card details, etc</li>
    <li>Data retention, auditing, logging, fault tolerance, system monitoring, and disaster recovery requirements.</li>
    <li>Any load balancing and caching requirements.</li>
    <li>Any static resources to be on CDN (Content Delivery Networks) for performace</li>
  </ol>
  <h5>#2. Draw a proposed solution diagram.</h5>
  <p>
    You must know the different integration styles and high level architectures like SOA, WOA, MOM, EDA, etc. Identify
    all the key components of the solution. Describe how each high level requirement is going to be addressed by the
    overall solution and its components. This is known as the <strong>baseline architecture</strong>.
  </p>

  <p>Each functional and non-functional requirement needs to be mapped to the technical solution. Gaps in
    requirements need to be identified.</p>

  <h5>#3. Identify the design alternatives, and analyze pros and cons of each alternative.</h5>
  <p>
    If you just take technical design alone, there will be many possible design alternatives, and each alternative has
    its own pros and cons along with likely trade-offs to be made in your design decisions. You will have to list the
    relevant <strong>assumptions</strong>, <strong>potential risks</strong>, <strong>likelihood and impacts</strong> of
    those risks to the business. At times, <strong>tactical solutions</strong> need to be favored over <strong>strategical
      solution</strong> due to business demands, budgetary constraints, and time to market. List all design choices and pros and
    cons for each choice. It is also imperative to not cut corners as a particular choice might look attractive now, but
    in a longer term require more rework and budget.
  </p>
  <p>So, design is often all about making the informed choices and trade offs. You make the design choices based on
    the functional and non-functional requirements, budgetary and non-budgetary constraints, environmental and political
    factors, and collective experience. Your architectural decisions need to adhere to the frameworks, policies and
    standards in place and need to be approved by the relevant stake holders, architecture review board, superiors, and
    peers. So, this requires good communication skills both written and oral to convince the relevant stake holders. You
    need to look at things from both business and technology perspective, and present it based on the target audience
    without too much technical jargon.</p>
  <p>
    <strong>Examples</strong>:
  </p>
  <ol>
    <li>RESTful web service Vs. SOAP web service</li>
    <li>Web Service Vs. messaging using a MOM</li>
    <li>Build new component, reuse existing, or buy, etc</li>
  </ol>
  <p>Look at from different key areas like Transaction Management, Security, Performance, etc. Click on each diagram
    to enlarge.</p>
  <p>
    <strong>Example 1</strong>: SOAP Vs RESTful
  </p>
  <div style="width: 1691px">
    <img src="./images/rest_soap.png" alt="SOAP Vs RESTful" width="1681" height="814"
      sizes="(max-width: 1681px) 100vw, 1681px">
    <p>SOAP Vs RESTful</p>
  </div>
  <div style="width: 1695px">
    <img src="./images/over_http_or_messaging.png" alt="Over HTTP or Messaging" width="1685" height="521"
      sizes="(max-width: 1685px) 100vw, 1685px">
    <p>Over HTTP or Messaging</p>
  </div>
  <div style="width: 1571px">
    <img src="./images/client_side_vs_server_side_mashups.png" alt="Client side Vs Server side mashups" width="1561"
      height="843" sizes="(max-width: 1561px) 100vw, 1561px">
    <p>Client side Vs Server side mashups</p>
  </div>
  <h5>#4. Make a decision on technology stack and frameworks to be used</h5>
  <p>&#8212; AngularJS for web tier and Spring/Hibernate for the service and data tiers.</p>
  <p>&#8212; Git for source control &#038; Jenkins for continuous integration.</p>
  <p>&#8212; Eclipse or InteliJ IDE for development.</p>
  <p>
    &#8212; unit testing, integration testing, and performance testing frameworks &#038; tools.
  </p>
  <p>&#8212; JBoss application server to run the web services</p>
  <p>
    Build a <strong>vertical slice</strong> for a typical use case as a proof of concept for the baseline architecture.
    Revise and improve on your design in the successive iterations. 
  </p>
  <h5>#5. Infrastructure &#038; Capacity planning</h5>
  <p>
    &#8212; <strong>Infrastructure planning:</strong> hosts, servers, operating system, application/web servers,
    firewall rules, inter zone connectivity, etc
  </p>
  <p>
    &#8212; <strong>Capacity planning:</strong> physical memory, hard disk space, CPU cores, JVM heap sizes, etc
  </p>
  <h5>#6. Logical &#038; physical modelling</h5>
  <p>
    &#8212; Identify the data requirements, and come up with logical and physical ER (<strong>Entity-Relationship</strong>)
    diagrams.
</body>
</html>