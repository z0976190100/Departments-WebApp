<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 20.03.2018
  Time: 1:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static com.service.utils.MessageManager.responseMessages" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab" rel="stylesheet">
    <title>update dis emp</title>
</head>
<body style="font-family:'Roboto Slab'">
<h3>enjoy your might to CHANGE!</h3>
<form action="/employee" method="get">
    <input name="command" type="hidden" value="empadd" />
    <input name="empid" type="hidden" value=<%=request.getParameter("empid")%>>
    <input name="deppid" type="hidden" value=<%=request.getParameter("deppid")%> />
    <input name="empfname" placeholder="first Name" value=<%=request.getParameter("empfname")%> /><br/>
    <input name="emplname" placeholder="last Name" value=<%=request.getParameter("emplname")%> /><br/>
    <input name="emplogin" placeholder="login" value=<%=request.getParameter("emplogin")%> /><br/>
    <input name="emppass" placeholder="pass" value=<%=request.getParameter("emppass")%> /><br/>
    <input name="depptitle" type="hidden" value=<%=request.getParameter("depptitle")%> />
    <input type="submit" value="Upd"/>
</form>

<form action="/deppage.jsp" method="get">
    <input name="deppid" type="hidden" value=<%=request.getParameter("deppid")%> />
    <input name="depptitle" type="hidden" value=<%=request.getParameter("depptitle")%> />
    <input name="command" type="hidden" value="depplist">
    <input type="submit" value="<- Back to DEP page"/>
</form>

<% String [] arr = responseMessages.split("#"); %>
<% for(String mess : arr){ %>
<h3><%=mess%></h3>
<% } %>
<% responseMessages = ""; %>
</body>
</html>
