<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 10.04.2018
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab" rel="stylesheet">
    <title>CHECK-IN</title>
</head>
<body style="font-family:'Roboto Slab'">

<h3>welcome, ${applicationScope.username}</h3>

<form action="/quit" method="post"><input type="submit" value="Quit"/></form>

<hr/>

<c:set var="depptitle" value="${param.depptitle}" scope="page"/>
<c:set var="deppid" value="${param.deppid}" scope="page"/>

<h1>DEPARTMENT of <c:out value="${depptitle}" /></h1>
<hr/>

<form action="/employees/employee" method="get">

    <input name="command" type="hidden" value="employeeUpdate">
    <input name="deppid" type="hidden" value="${deppid}"/>
    <input name="depptitle" type="hidden" value="${depptitle}"/>
    <input name="empid" type="hidden" value="${param.empid}" />

    <table border=2 cellpadding=10>
        <th bgcolor="#999966" colspan=2>
            <c:choose>
                <c:when test="${(requestScope.responseMessages.get(\"EMPLOYEE_RECORD_UPDATE_SUCCESS_MESSAGE\") != null)}">
                    <p style="color:green">
                        <c:out value="${requestScope.responseMessages.get(\"EMPLOYEE_RECORD_UPDATE_SUCCESS_MESSAGE\")}"/>
                    </p>
                </c:when>
                <c:otherwise>
                    <p><sup>*</sup>EMPLOYEE EDIT</p>
                </c:otherwise>
            </c:choose>
        </th>
        <tr>
            <td valign=top style="width:200px">
                <b>First Name<sup>*</sup></b>
                <br>
                <input type="text"
                       name="empfname"
                       value="${param.empfname}"
                       pattern="[A-Za-z]+"
                       title="First Name can contain letters only!"
                       size=15 maxlength=20
                       required />
            </td>
            <td valign=top style="width:200px">
                <b>Last Name<sup>*</sup></b>
                <br>
                <input type="text"
                       name="emplname"
                       value="${param.emplname}"
                       pattern="[A-Za-z]+"
                       title="Last Name can contain letters only!"
                       size=15 maxlength=20
                       required />
            </td>
        </tr>
        <tr>
            <td valign=top colspan="2">
                <b>Date of Birth<sup>*</sup></b>
                <br>
                <input type="date"
                       name="birthDate"
                       value="${param.birthDate}"
                       min="${requestScope.minDate}" max="${requestScope.maxDate}"
                       required />
                <br></td>
        </tr>
        <tr>
            <td valign=top colspan="2">
                <b>E-mail (as login)<sup>*</sup></b>
                <br>
                <input type="text"
                       name="emplogin"
                       placeholder="my@mail.dot.com"
                       value="${param.emplogin}"
                       size=25 maxlength=125
                       disabled />
                <br>
                <%--<c:if test="${(requestScope.responseMessages.get(\"LOGIN_SAVE_PROBLEM_MESSAGE\") != null)}" >
                    <p style="color:red"><c:out value="${requestScope.responseMessages.get(\"LOGIN_SAVE_PROBLEM_MESSAGE\")}"/></p>
                </c:if>
                <c:if test="${(requestScope.responseMessages.get(\"INVALID_EMAIL_MESSAGE\") != null)}" >
                    <p style="color:red"><c:out value="${requestScope.responseMessages.get(\"INVALID_EMAIL_MESSAGE\")}"/></p>
                </c:if>--%>
            </td>
        </tr>
        <tr>
            <td valign=top>
                <b>Password<sup>*</sup></b>
                <br>
                <input type="password"
                       name="emppass"
                       value="${param.emppass}"
                       size=10
                       maxlength=10
                       required />
            </td>
            <td valign=top>
                <b>Confirm Password<sup>*</sup></b>
                <br>
                <input type="password"
                       name="emppass2"
                       value="${param.emppass}"
                       size=10
                       maxlength=10
                       required />
                <br>
                <c:if test="${(requestScope.responseMessages.get(\"FAILED_PASS_CONFIRMATION_MESSAGE\") != null)}" >
                    <p style="color:red"><c:out value="${requestScope.responseMessages.get(\"FAILED_PASS_CONFIRMATION_MESSAGE\")}"/></p>
                </c:if>

            </td>
            <br>
        </tr>
        <tr>
            <td align=center bgcolor="#99ff33">
                <input type="submit" value="Submit">
            <td align=center bgcolor="#cc3300">
                <input type="reset" value="Reset">
            </td>
        </tr>
    </table>
</form>
<hr/>



<form action="/employeeslist" method="post">
    <input name="command" type="hidden" value="departmentList">
    <input name="deppid" type="hidden" value="${deppid}"/>
    <input name="depptitle" type="hidden" value="${depptitle}"/>
    <input type="submit" value="<- Back to Department page"/>


</form>
</body>
</html>
