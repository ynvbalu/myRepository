<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Queries with AJAX call</title>

<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

<script type="text/javascript">
   function myAjax() {
      $.ajax({
        url : '/log/ajax/TEST_2016-05-31_11_22.log',
        success : function (data) {
          $('#result').html(data);
        }
      });
  }
</script>

<script type="text/javascript">
     var intervalId = 0;
     intervalId = setInterval(myAjax, 10000);
</script>
</head>
<body>
  <div align="center">
    <div id="result"></div>
    <br>
    <p>Hi From Naga</p>
  </div>
</body>
</html>