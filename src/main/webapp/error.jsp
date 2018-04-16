<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 01.03.2018
  Time: 2:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab" rel="stylesheet">
    <title>error</title>

</head>
<body style="font-family:'Roboto Slab'">
<h1>WHOA! AN ERROR.</h1>
<c:out value="${requestScope.errorMessage}" />
</body>
</html>
