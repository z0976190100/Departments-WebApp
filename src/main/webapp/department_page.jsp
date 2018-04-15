<%@ page import="com.depart.project.persistense.entity.EmployeeRegister" %>
<%@ page import="com.depart.project.service.utils.MessageManager" %>
<%@ page import="static com.depart.project.service.utils.MessageManager.responseMessages" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab" rel="stylesheet">
    <title>deppage</title>

</head>
<body style="font-family:'Roboto Slab'">

<h3>welcome, ${applicationScope.username}</h3>

<form action="/quit" method="post"><input type="submit" value="Quit"/></form>

<hr/>

<c:set var="depptitle" value="${param.depptitle}" scope="page" />
<c:set var="deppid" value="${param.deppid}" scope="page" />
<h1>DEPARTMENT of <c:out value="${depptitle}" /></h1>
<h3>EMPLOYEES</h3>
<form action="/employee_add_page.jsp" method="post" style="font-family:'Roboto Slab'">
    <input name="command" type="hidden" value="empadd">
    <input name="deppid" type="hidden" value="${deppid}" />
    <input name="depptitle" type="hidden" value="${depptitle}" />
    <input type="submit" value="Add new Employee"/>
</form>
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

    <% EmployeeRegister.listUpdate(Long.valueOf(request.getParameter("deppid")));%>

<c:forEach var="empp" items="${EmployeeRegister.emppList}">

    <tr>

        <td><c:out value="${empp.firstName}" /></td>
        <td><c:out value="${empp.lastName}" /></td>
        <td><c:out value="${empp.login}" /></td>
        <td><c:out value="${empp.pass}" /></td>
        <td><c:out value="${empp.birthDate}" /></td>
        <td bgcolor="#99ff33">
            <form action="/employee_update_page.jsp" method="post">
                <input name="empid" type="hidden" value="${empp.id}" />
                <input name="empfname" type="hidden" value="${empp.firstName}" />
                <input name="emplname" type="hidden" value="${empp.lastName}" />
                <input name="emplogin" type="hidden" value="${empp.login}" />
                <input name="emppass" type="hidden" value="${empp.pass}" />
                <input name="deppid" type="hidden" value="${deppid}" />
                <input name="depptitle" type="hidden" value="${depptitle}" />
                <input name="command" type="hidden" value="deppedit" />
                <input type="submit" value="Edit" />
            </form>
        </td>

        <td bgcolor="#cc3300">
            <form action="/employeedelete" method="post">
                <input name="empid" type="hidden" value="${empp.id}" />
                <input name="command" type="hidden" value="empdelete" />
                <input name="deppid" type="hidden" value="${deppid}" />
                <input name="depptitle" type="hidden" value="${depptitle}"  />
                <input type="submit" value="Delete" />
            </form>
        </td>
    </tr>
</c:forEach>
</table>


<hr/>

<c:forEach var="mess" items="${MessageManager.responseMessages.split(\"#\")}">
    <h3><c:out value="${mess}"/></h3>
</c:forEach>

<form action="/main.jsp" method="get">
    <input name="command" type="hidden" value="depplist" />
    <input type="submit" value="<- Back to Main page"/>
</form>

<% responseMessages = ""; %>
</body>
</html>
