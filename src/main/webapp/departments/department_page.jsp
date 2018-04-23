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
<c:set var="emppList" value="${requestScope.employeesList}" />

<h1>DEPARTMENT of <c:out value="${depptitle}" /></h1>
<h3>EMPLOYEES</h3>
<form action="/employees/employee_add_page.jsp" method="post" style="font-family:'Roboto Slab'">
    <input name="command" type="hidden" value="employeeAdd">
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
    <c:if test="${empty emppList}">
        <tr>
            <td colspan="8">
        <c:out value="Noone to get fired yet... Sad...Add a looser."/>
            </td>
        </tr>
    </c:if>

<c:forEach var="empp" items="${emppList}">

    <tr>
        <td><c:out value="${empp.firstName}" /></td>
        <td><c:out value="${empp.lastName}" /></td>
        <td><c:out value="${empp.login}" /></td>
        <td><c:out value="${empp.pass}" /></td>
        <td><c:out value="${empp.birthDate}" /></td>
        <td bgcolor="#99ff33">
            <form action="/employees/employee_update_page.jsp" method="post">
                <input name="command" type="hidden" value="employeeUpdate" />
                <input name="empid" type="hidden" value="${empp.id}" />
                <input name="empfname" type="hidden" value="${empp.firstName}" />
                <input name="emplname" type="hidden" value="${empp.lastName}" />
                <input name="emplogin" type="hidden" value="${empp.login}" />
                <input name="emppass" type="hidden" value="${empp.pass}" />
                <input name="birthDate" type="hidden" value="${empp.birthDate}" />
                <input name="deppid" type="hidden" value="${deppid}" />
                <input name="depptitle" type="hidden" value="${depptitle}" />
                <input type="submit" value="Edit" />
            </form>
        </td>

        <td bgcolor="#cc3300">
            <form action="/employeedelete" method="post">
                <input name="command" type="hidden" value="employeeDelete" />
                <input name="empid" type="hidden" value="${empp.id}" />
                <input name="deppid" type="hidden" value="${deppid}" />
                <input name="depptitle" type="hidden" value="${depptitle}"  />
                <input type="submit" value="Delete" />
            </form>
        </td>
    </tr>
</c:forEach>
</table>

<hr/>

<form action="/departmentlist" method="post">
    <input name="command" type="hidden" value="departmentList" />
    <input type="submit" value="<- Back to Main page"/>
</form>

</body>
</html>
