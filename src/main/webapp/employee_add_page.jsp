<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 10.04.2018
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.service.utils.MessageManager" %>
<%@ page import="static com.service.utils.MessageManager.responseMessages" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab" rel="stylesheet">
    <title>CHECK-IN</title>
</head>
<body style="font-family:'Roboto Slab'">

<c:set var="depptitle" value="${param.depptitle}" scope="page"/>
<c:set var="deppid" value="${param.deppid}" scope="page"/>

<h1>DEPARTMENT of <c:out value="${depptitle}"/><c:out value="${deppid}"/></h1>
<h3>EMPLOYEE CHECK-IN</h3>
<hr/>

<form action="/employee" method=post>

    <input name="command" type="hidden" value="empadd">
    <input name="empdep" type="hidden" value="${deppid}"/>
    <input name="depptitle" type="hidden" value="${depptitle}"/>

    <table border=2 cellpadding=10>
        <th bgcolor="#999966" colspan=2>
            <h4><sup>*</sup> these are required fields</h4>
        </th>
        <tr>
            <td valign=top style="width:200px">
                <b>First Name<sup>*</sup></b>
                <br>
                <input type="text" name="empfname" value="" size=15 maxlength=20></td>
            <td valign=top style="width:200px">
                <b>Last Name<sup>*</sup></b>
                <br>
                <input type="text" name="emplname" value="" size=15 maxlength=20></td>
        </tr>
        <tr>
            <td valign=top colspan="2">
                <b>Date of Birth<sup>*</sup></b>
                <br>
                <input type="text" name="birthDate" placeholder="DD.MM.YYYY" value="" size=25 maxlength=125>
                <br></td>
        </tr>
        <tr>
            <td valign=top colspan="2">
                <b>E-mail (as login)<sup>*</sup></b>
                <br>
                <input type="text" name="emplogin" placeholder="my@mail.dot.com" value="" size=25 maxlength=125>
                <br></td>
        </tr>
        <tr>
            <td valign=top>
                <b>Password<sup>*</sup></b>
                <br>
                <input type="password" name="emppass" size=10 value=""
                       maxlength=10></td>
            <td valign=top>
                <b>Confirm Password<sup>*</sup></b>
                <br>
                <input type="password" name="emppass2" size=10 value=""
                       maxlength=10></td>
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

<c:forEach var="mess" items="${MessageManager.responseMessages.split(\"#\")}">
    <h3><c:out value="${mess}"/></h3>
</c:forEach>

<form action="/main.jsp" method="get">
    <input name="command" type="hidden" value="depplist">
    <input type="submit" value="<- Back to Main page"/>

    <% responseMessages = ""; %>

</form>
</body>
</html>