<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab" rel="stylesheet">
    <title>Update Department</title>
</head>
<body style="font-family:'Roboto Slab'">
<h3>Change the title</h3>
<form action="/department" method="get">
    <input name="deppid" type="hidden" value="<%=request.getParameter("deppid")%>"/>
    <input name="command" type="hidden" value="deppupdate"/>
    <input name="newdepptitle" type="text" value="<%=request.getParameter("depptitle")%>"/>
    <input type="submit" value="Flop!"/>
</form>
<hr/>
<%--<c:forEach var="mess" items="${MessageManager.responseMessages.split(\"#\")}">
    <h3><c:out value="${mess}"/></h3>
</c:forEach>--%>
</body>
</html>
