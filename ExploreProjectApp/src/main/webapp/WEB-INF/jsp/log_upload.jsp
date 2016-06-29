<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<title>Queries</title>
</head>
<body>
  <table border="1" width="100%">
    <thead>
      <tr>
        <td colspan="4"><font color="blue">/******** *****/
        </font></td>
      </tr>
    </thead>
    <tfoot>
      <tr>
        <td colspan="4"><font color="blue">/********&copy; 2016 - 2019*****/</td>
      </tr>
    </tfoot>
    <tbody>
      <tr>
        <td colspan="4"><c:forEach var="valueFromTheLists" items="${finalOutput}" varStatus="loop">
            <c:set var="valueWithOutSpace" value="${fn:replace(valueFromTheLists, 'and', '<br>and')}" />
            <c:set var="valueWithOutSpace" value="${fn:replace(valueWithOutSpace, 'AND', '<br>AND')}" />
            <c:if test="${not fn:contains(valueWithOutSpace, 'SELECT * from')}">
              <c:set var="valueWithOutSpace" value="${fn:replace(valueWithOutSpace, ' FROM', '<br>FROM')}" />
              <c:set var="valueWithOutSpace" value="${fn:replace(valueWithOutSpace, 'from', '<br>from')}" />
              <c:set var="valueWithOutSpace" value="${fn:replace(valueWithOutSpace, 'values (', '<br>values (')}" />
            </c:if>
            <p>
              <font color="black">/*(${loop.index})*/</font><font color="blue">${valueWithOutSpace}</font>
            </p>
          </c:forEach></td>
      </tr>
    </tbody>
  </table>
</body>
</html>