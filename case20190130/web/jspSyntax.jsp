<%--
  Created by IntelliJ IDEA.
  User: Choel
  Date: 2019/1/30
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%!
    int i = 0;

    private int count() {
        i++;
        return i;
    }
%>
<p>调用count方法的输出<%=count()%>
</p>
<p>调用count方法的输出<%=count()%>
</p>
<br>
<%
    int j = 0;
    j++;
    out.print("j=" + j);
%>
<p>j=<%=j%>
</p>
<%@ include file="footer.jsp" %>