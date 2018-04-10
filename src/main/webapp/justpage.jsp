<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 10.04.2018
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab" rel="stylesheet">
    <title>just</title>
</head>
<body style="font-family:'Roboto Slab'">
<form action="#" method=post>
    <h2>EMPLOYEE CHECK-IN</h2>
    <hr/>
        <table border="2" cellpadding="5">
            <th bgcolor="#999966" colspan=2>
                <h4><sup>*</sup> these are required fields</h4>
            </th>
            <tr>
                <td valign=top>
                    <b>First Name<sup>*</sup></b>
                    <br>
                    <input type="text" name="firstName" value="" size=15 maxlength=20></td>
                <td valign=top>
                    <b>Last Name<sup>*</sup></b>
                    <br>
                    <input type="text" name="lastName" value="" size=15 maxlength=20></td>
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
                    <input type="text" name="email" placeholder="my@mail.dot.com" value="" size=25 maxlength=125>
                    <br></td>
            </tr>
            <tr>
                <td valign=top>
                    <b>Password<sup>*</sup></b>
                    <br>
                    <input type="password" name="password1" size=10 value=""
                           maxlength=10></td>
                <td valign=top>
                    <b>Confirm Password<sup>*</sup></b>
                    <br>
                    <input type="password" name="password2" size=10 value=""
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
<form action="/main.jsp" method="get">
    <input name="command" type="hidden" value="depplist">
    <input type="submit" value="<- Back to Main page"/>
</form>
</body>
</html>
