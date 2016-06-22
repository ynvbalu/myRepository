<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html lang="en">

<head>
<style>
body {
  white-space: pre;
}

table {
  table-layout: auto;
  border-collapse: collapse;
  width: 100%;
}

/* tr.even { background: red; }
tr.odd { background: white; } */
table td {
  border: 1px solid #ccc;
  vertical-align: top;
  color: green;
  border-color: maroon;
}

table td.absorbing-column {
  width: 100%;
}
</style>
</head>

<body>
  <table>
    <%-- <tr>
      <td>No</td>
      <td><marquee direction="left" scrolldelay="85">${startUpMessage}</marquee></td>
      <td>${startUpMessage}</td>
    </tr> --%>
    <tr>
      <td width="8">No</td>
      <td>Query &nbsp&nbsp<a href="/downloadExcel/${name}"> Export to Excel</a> &nbsp&nbsp<a
          href="/downloadPDF/${name}"> Export to PDF</a> <a href="/downloadText/${name}"> Export to File</a>
      </td>
    </tr>
    <c:forEach var="valueFromTheLists" items="${finalOutput}" varStatus="loop">

      <c:set var="valueWithOutSpace" value="${fn:replace(valueFromTheLists, 'and', '<br>and')}" />
      <c:set var="valueWithOutSpace" value="${fn:replace(valueWithOutSpace, 'AND', '<br>AND')}" />
      <c:if test="${not fn:contains(valueWithOutSpace, 'SELECT * from')}">
        <c:set var="valueWithOutSpace" value="${fn:replace(valueWithOutSpace, 'FROM', '<br>FROM')}" />
        <c:set var="valueWithOutSpace" value="${fn:replace(valueWithOutSpace, 'from', '<br>from')}" />
        <c:set var="valueWithOutSpace" value="${fn:replace(valueWithOutSpace, 'values (', '<br>values (')}" />
      </c:if>

      <tr class="${loop.index % 2 == 0 ? 'even' : 'odd'}">
        <td>${loop.index}</td>
        <%-- <td>/*${loop.index}*/${valueWithOutSpace}</td> --%>
        <td>${valueWithOutSpace}</td>
      </tr>
    </c:forEach>
  </table>
</body>
</html>
