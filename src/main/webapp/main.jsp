<%@ page import="com.persistense.entity.DepartmentRegister" %>
<%@ page import="com.controller.DepartmentGetPostServlet" %>
<%@ page import="static com.service.utils.MessageManager.responseMessages" %>
<%@ page import="com.service.utils.MessageManager" %>
<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab" rel="stylesheet">
    <title>DEPS</title>
</head>
<body style="font-family:'Roboto Slab'">

<h3>welcome, ${cookie.user.value}</h3>

<form action="/quit" method="post"><input type="submit" value="Quit"/></form>

<hr/>

<h1>DEPARTMENTS</h1>

<span></span>

<table border="2" cellpadding="5">
    <tr bgcolor="#999966">
        <th>Title</th>
        <th>Employees</th>
        <th colspan="3">Actions</th>
    </tr>

    <% DepartmentRegister.listUpdate(); %>

    <c:forEach var="depp" items="${DepartmentRegister.deppList}">

        <tr>
            <td><c:out value="${depp.title}"/></td>
            <td><c:out value="${depp.empQuant}"/></td>
            <td bgcolor="#99ff33">
                <form action="/department_page.jsp" method="get">
                    <input name="deppid" type="hidden" value="${depp.id}"/>
                    <input name="depptitle" type="hidden" value="${depp.title}"/>
                    <input name="command" type="hidden" value="depplist"/>
                    <input type="submit" value="Employees list"/>
                </form>
            </td>
            <td bgcolor="#99ff33">
                <form action="/depupdate.jsp" method="get">
                    <input name="deppid" type="hidden" value="${depp.id}"/>
                    <input name="command" type="hidden" value="depupdate"/>
                    <input name="depptitle" type="hidden" value="${depp.title}"/>
                    <input type="submit" value="Rename"/>
                </form>
            </td>
            <td bgcolor="#cc3300">
                <form action="/departmentdelete" method="post">
                    <input name="deppid" type="hidden" value="${depp.id}"/>
                    <input name="command" type="hidden" value="deppdelete"/>
                    <input type="submit" value="Delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<hr/>
<c:forEach var="mess" items="${MessageManager.responseMessages.split(\"#\")}">
    <h3><c:out value="${mess}"/></h3>
</c:forEach>
<table>
    <tr>
        <td>
            <form action="/department" method="post">
                <input name="command" type="hidden" value="deppadd" />
                <input name="newdepptitle" placeholder="New DEPARTMENT Title" value="${DepartmentGetPostServlet.depTitleInputValue}" /> <%----%>
                <input type="submit" value="Add new Department"/>
            </form>
        </td>
    </tr>
</table>

<% responseMessages = ""; %>

</body>
</html>