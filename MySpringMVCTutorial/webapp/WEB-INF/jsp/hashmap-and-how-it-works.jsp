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
</body>
</html>