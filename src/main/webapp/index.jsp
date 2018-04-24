<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab" rel="stylesheet">
    <title>login page</title>
</head>
<body style="font-family:'Roboto Slab'">

<h2>login to start</h2>

<hr/>

<form name="login-form" method="POST" action="/tologin">
    <input type="hidden" name="command" value="login"/>
    Login:<br/>
    <input type="email" name="login" placeholder="my@email.dot.com" value="" required /><br/>
    Password:<br/>
    <input type="password" name="pass" value="" required /><br/>
    <input type="submit" value="Start">
</form>

<c:if test="${(requestScope.responseMessages.get(\"LOGIN_PASSWORD_PROBLEM_MESSAGE\") != null)}" >
    <p style="color:red"><c:out value="${requestScope.responseMessages.get(\"LOGIN_PASSWORD_PROBLEM_MESSAGE\")}"/></p>
</c:if>

</body>
</html>
