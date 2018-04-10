<%@ page import="com.persistense.entity.EmployeeRegister" %>
<%@ page import="com.persistense.entity.EmployeeEntityImpl" %>
<%@ page import="static com.service.utils.MessageManager.responseMessages" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab" rel="stylesheet">
    <title>deppage</title>

</head>
<body style="font-family:'Roboto Slab'">
<%--<%
    String depptitle =
            request.getParameter("depptitle");
%>--%>
<c:set var="depptitle" value="${param.depptitle}" scope="page" />
<h1>DEPARTMENT of <c:out value="${depptitle}" /></h1>
<h3>EMPLOYEES</h3>
<br/>
<table border="2" cellpadding="5">
    <tr bgcolor="#999966">
        <th>First name</th>
        <th>Last name</th>
        <th>login</th>
        <th>pass</th>
        <th>date</th>
        <th colspan="3">Actions</th>
    </tr>
    <% EmployeeRegister er = new EmployeeRegister(); %>
    <% er.listUpdate(Long.valueOf(request.getParameter("deppid")));%>
    <% int counter = 1; %>
    <% for (EmployeeEntityImpl empp : er.emppList) { %>

    <tr>

        <td><%=empp.firstName%></td>
        <td><%=empp.lastName%></td>
        <td><%=empp.login%></td>
        <td><%=empp.pass%></td>
        <td></td>
        <td bgcolor="#99ff33">
            <form action="/empupdate.jsp" method="post">
                <input name="empid" type="hidden" value="<%=empp.id%>" />
                <input name="empfname" type="hidden" value="<%=empp.firstName%>" />
                <input name="emplname" type="hidden" value="<%=empp.lastName%>" />
                <input name="emplogin" type="hidden" value="<%=empp.login%>" />
                <input name="emppass" type="hidden" value="<%=empp.pass%>" />
                <input name="deppid" type="hidden" value="<%=request.getParameter("deppid")%>" />
                <input name="deppid" type="hidden" value="<%=empp.id%>" />
                <input name="depptitle" type="hidden" value="${depptitle}" />
                <input name="command" type="hidden" value="deppedit" />
                <input type="submit" value="Improve" />
            </form>
        </td>

        <td bgcolor="#cc3300">
            <form action="/employeedelete" method="post">
                <input name="empid" type="hidden" value="<%=empp.id%>" />
                <input name="command" type="hidden" value="deppdelete" />
                <input name="deppid" type="hidden" value="<%=request.getParameter("deppid")%>" />
                <input name="depptitle" type="hidden" value="${depptitle}"  />
                <input type="submit" value="Demolish" />
            </form>
        </td>
    </tr>
    <% } %>
</table>

<hr/>
<form action="/employee" method="post" style="font-family:'Roboto Slab'">
    <input name="command" type="hidden" value="empadd">
    <input name="empdep" type="hidden" value="<%=request.getParameter("deppid")%>" />
    <input name="empfname" placeholder="first Name" value="">
    <input name="emplname" placeholder="last Name" value="">
    <input name="emplogin" placeholder="login" value="">
    <input name="emppass" placeholder="pass" value="">
    <input name="deppid" type="hidden" value="<%=request.getParameter("deppid")%>" />
    <input name="depptitle" type="hidden" value="${depptitle}"  />

    <input type="submit" value="Increase"/>
</form>

<form action="/main.jsp" method="get">
    <input name="command" type="hidden" value="depplist" />
    <input type="submit" value="<- Back to Main page"/>
</form>


<% String[] arr = responseMessages.split("#"); %>
<% for (String mess : arr) { %>
<h3><%=mess%>
</h3>
<% } %>
<% responseMessages = ""; %>
</body>
</html>
