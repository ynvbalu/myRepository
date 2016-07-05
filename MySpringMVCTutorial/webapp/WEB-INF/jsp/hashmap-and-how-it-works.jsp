<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hashmap how it works</title>
</head>
<body>
	<p>
		<strong>HashMap</strong> is not only one of the frequently used data
		structures, but also one of the popular interview topics.
	</p>
	<p>
		<span style="font-size: large; color: brown;">Q1.</span> How does a
		HashMap store data?<br /> <span
			style="font-size: large; color: brown;">A1.</span> As key/value
		pairs. You can store and retrieve values with the keys.
	</p>
	<p>
		<span style="font-size: large; color: brown;">Q2.</span> What is the
		HashMap lookup time in Big O notation?<br /> <span
			style="font-size: large; color: brown;">A2.</span> It is O(<strong>n</strong>)
		= O(k * n). On average it is O(1) if the hashCode() method spreads out
		the buckets as discussed below.
	</p>
	<p>
		<span style="font-size: large; color: brown;">Q3</span> How does a
		HashMap internally store data?<br /> <span
			style="font-size: large; color: brown;">A3.</span> A backing array
		for the buckets and a linked list to store key/value pairs.
	</p>
	<p>
		<span style="font-size: large; color: brown;">Backing Array
			with buckets:</span> as shown below.
	</p>
	......................................................

	<p>
		<strong>1) </strong>When you put an object into a map with a key and a
		value, <strong>hashCode()</strong> method is implicitly invoked, and
		hash code value say 123 is returned. Two different keys can return the
		same hash value. A good hashing algorithm spreads out the numbers. In
		the above example, let&#8217;s assume that
		(&#8220;John&#8221;,01/01/1956) key and (&#8220;Peter&#8221;,
		01/01/1995) key return the same hash value of <strong>123</strong>.
	</p>
	<p>
		<strong>2) </strong>Now when a hashCode value of say 123 is returned
		and the initial HashMap capacity is say 10, how does it know which
		index of the backing array to store it in? This is where the HashMap
		internally invokes the <strong>hash(int )</strong> &#038; <strong>indexFor(int
			h, int length)</strong> methods. This is known as the <strong>hashing</strong>
		function. This function in a very simple explanation is like
	</p>
	
</body>
</html>