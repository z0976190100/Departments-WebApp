<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@ page import="static com.service.helpers.MessageManager.responseMessages" %>
<html>
<head>
    <title>login page</title>
</head>
<body>
<h2>login to start</h2>

<hr/>

<form name="login-form" method="POST" action="/main">
    <input type="hidden" name="command" value="login"/>
    Login:<br/>
    <input type="text" name="login" value=""><br/>
    Password:<br/>
    <input type="password" name="pass" value=""><br/>
    <input type="submit" value="Start">
</form>

<% String [] arr = responseMessages.split("#"); %>
<% for(String mess : arr){ %>
<h3><%=mess%></h3>
<% } %>
<% responseMessages = ""; %>

</body>
</html>
