<%--
  Created by IntelliJ IDEA.
  User: Choel
  Date: 2019/1/30
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>count visit times</title>
</head>
<body>
<%
    if(application.getAttribute("count") == null) {
        application.setAttribute("count", 0);
    }
    int count = (Integer)application.getAttribute("count");
    application.setAttribute("count", count + 1);
%>
<h2>
    欢迎您访问本页，本也已经被访问过
    <font color="#ff0000">
        <%=application.getAttribute("count")%>
    </font>
    次了
</h2>
</body>
</html>
