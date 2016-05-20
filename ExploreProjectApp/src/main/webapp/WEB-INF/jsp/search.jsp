<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<head>

 <meta charset="utf-8">
  <title>jQuery UI Autocomplete - Default functionality</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">


<style>
table {
  table-layout: auto;
  border-collapse: collapse;
  width: 100%;
}

table td {
  border: 1px solid #ccc;
  vertical-align: top;
  color: green;
  border-color: maroon;
  font-weight: normal;
}

table td.absorbing-column {
  width: 100%;
}
</style>

</head>

<body>
  <%-- <table>
    <c:forEach var="value" items="${finalOutput}" varStatus="loop">
      <tr>
        <td>${loop.index}</td>
        <td>${value}</td>
      </tr>
    </c:forEach>
    <script type="text/javascript">
        var value = "${finalOutput}";
        alert(value);
</script>
  </table> --%>
  
   <script>
  $(function() {
    var availableTags = [
      "ActionScript",
      "AppleScript",
      "Asp",
      "BASIC",
      "C",
      "C++",
      "Clojure",
      "COBOL",
      "ColdFusion",
      "Erlang",
      "Fortran",
      "Groovy",
      "Haskell",
      "Java",
      "JavaScript",
      "Lisp",
      "Perl",
      "PHP",
      "Python",
      "Ruby",
      "Scala",
      "Scheme"
    ];
    
    
    var myArray2 = new Array();
    myArray2= new Array('${finalOutput}');
    var carter = myArray2.toString();
    carter = carter.replace("[","");
    carter = carter.replace("]","");
    carter = carter.split(",");
    
    console.log(carter);
    
    $( "#tags" ).autocomplete({
      source: carter
    });
  });
  </script>
</head>
<body>
 
<div class="ui-widget">
  <label for="tags">Tags: </label>
  <input id="tags" value="${searchStr}">
</div>
  
</body>
</html>
