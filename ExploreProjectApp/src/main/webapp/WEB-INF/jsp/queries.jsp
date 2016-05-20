<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.*"%>
<%@page import="java.util.stream.*"%>
<%@page import="java.util.*"%>
<%@page import="java.nio.file.*"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="sample.jsp.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Queries in the log</title>

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
  <%
    List<String> finalOutput = null;
    
    try {
      String LOCATION = "C:\\applications\\myprojects_debug\\queries.txt";
      finalOutput = Files.readAllLines(Paths.get(LOCATION));
    } catch (Exception e) {
      //output = "Check your file name";
    }
  %>
  <table>
    <tr>
      <td>No.</td>
      <td>Query Template</td>
    </tr>
    <%
    int j = 0;
    String myTemp = "";
    if (finalOutput != null) {
      for (int i = 0; i < finalOutput.size(); i++) {
        String temp = finalOutput.get(i);
        myTemp = temp.toUpperCase();
        if (temp!= null && !temp.startsWith("/*******")){
          j++;
          temp = temp.replaceAll("from", "FROM");
          if(!temp.startsWith("SELECT *") && !temp.contains("REMS_TXN_SEQUENCE.nextval")) {
            temp = temp.replaceAll("FROM", "<br><font color='blue'>FROM</font>");
          } else {
            temp = temp.replaceAll("FROM", "<font color='blue'>FROM</font>");
          }
          temp = temp.replaceAll("select", "<font color='blue'>SELECT</font>")
              .replaceAll("SELECT","<font color='blue'>SELECT</font>");
          if(temp.startsWith("SELECT *")) {
            temp = temp.replaceAll("FROM", "<font color='blue'>FROM</font>");
          }
          
          temp = temp.replaceAll("where", "WHERE")
              .replaceAll("WHERE", "<br> <font color='blue'>WHERE</font>")
              .replaceAll("asc", "<font color='blue'>asc</font>")
              .replaceAll("desc;", "<font color='blue'>DESC;</font>")
              .replaceAll("ORDER BY","<br> <font color='blue'>ORDER BY</font>")
              .replaceAll("and", "AND")
              .replaceAll("trunc", "TRUNC")
              .replaceAll("is null", "IS NULL")
              .replaceAll("sysdate", "SYSDATE")
              .replaceAll("END_DT>SYSDATE", "END_DT > SYSDATE")
              .replaceAll("insert into", "<font color='blue'>INSERT INTO</font>")
              .replaceAll(" values ", "<br><font color='blue'>VALUES</font>")
              .replaceAll(" AND", "<br>&nbsp;AND")
              .replaceAll("order by", "<br> <font color='blue'>ORDER BY</font>")
              .replaceAll("NULL", "<font color='blue'>NULL</font>")
              .replaceAll("SYSDATE", "<font color='blue'>SYSDATE</font>")
              .replaceAll("TRUNC", "<font color='blue'>TRUNC</font>")
              .replaceAll("HANDler", "Handler");
    %>
    <tr>
      <td><%=j%></td>
      
      <%
      if(myTemp.contains("FROM") && myTemp.contains("WHERE")) {
        if(!myTemp.contains(("ROW_NUMBER() OVER("))) {
          myTemp = myTemp.substring(myTemp.indexOf("FROM")+5 , myTemp.indexOf("WHERE"));
        } else {
          myTemp = myTemp.substring(myTemp.lastIndexOf("FROM")+5 , myTemp.indexOf("WHERE"));
        }
          myTemp = myTemp.replaceAll(",", "<BR>");
      }
    
    //System.out.println(myTemp);
    %>
    <td><%=temp%>
    <%-- <marquee direction="left" scrolldelay="85"><%=myTemp.trim()%></marquee> --%>
    <%-- <%=myTemp.trim()%> --%>
    </td>
    </tr>
    <%
      } else {
    %>
    <tr>
      <td></td>
      <td style="color: purple;"><%=temp.substring(0, temp.length()-2)%></td>
    </tr>
    <%
    }
 }
    }
 %>

  </table>


</body>
</html>