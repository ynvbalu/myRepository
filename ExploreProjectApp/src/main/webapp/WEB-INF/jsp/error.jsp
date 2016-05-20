<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<head>
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
  <table>
    <c:forEach var="value" items="${finalOutput}" varStatus="loop">
      <tr>
        <td>${loop.index}</td>
        <td>${value}</td>
      </tr>
    </c:forEach>
  </table>
</body>
</html>
