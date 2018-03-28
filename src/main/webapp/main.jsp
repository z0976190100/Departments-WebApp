<%@ page import="com.persistense.entity.DepartmentRegister " %>
<%@ page import="com.persistense.entity.DepartmentEntityImpl" %>
<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab" rel="stylesheet">
    <title>DEPS</title>
</head>
<body style="font-family:'Roboto Slab'">
<%--<c:out value="ertyui"/>--%>
<h3>welcome,  ${cookie.user.value}</h3>
<form action="/index.jsp" method="get"><input type="submit" value="Exit"/></form>
<hr/>
<jsp:useBean id="depps" class="com.persistense.entity.DepartmentRegister" scope="application"></jsp:useBean>
<h1>DEPARTMENTS</h1>
<span></span>
<table border="2" cellpadding="5">
    <tr bgcolor="#999966">
       <%-- <th>id</th>--%>
        <th>Title</th>
        <th>Employees</th>
        <th colspan="3">Actions</th>
    </tr>
    <% DepartmentRegister.listUpdate(); %>
    <% int counter = 1; %>
    <% for (DepartmentEntityImpl depp : depps.deppList) { %>

    <tr>

      <%--  <td id=<%=depp.id%>><%=counter++%>
        </td>--%>
        <td contenteditable="true"><%=depp.title%>
        </td>
        <td><%=depp.empQuant%>
        </td>


        <td bgcolor="#99ff33">
            <form action="/depupdate.jsp" method="get">
                <input name="deppid" type="hidden" value="<%=depp.id%>"/>
                <input name="command" type="hidden" value="depupdate"/>
                <input name="depptitle" type="hidden" value="<%=depp.title%>"/>
                <input type="submit" value="Improve"/>
            </form>
        </td>
        <td bgcolor="#99ff33">
            <form action="/deppage.jsp" method="get">
                <input name="deppid" type="hidden" value= <%=depp.id%>>
                <input name="depptitle" type="hidden" value=<%=depp.title%>>
                <input name="command" type="hidden" value="depplist">
                <input type="submit" value="DEP page"/>
            </form>
        </td>
        <td bgcolor="#cc3300">
            <form action="/departmentdelete" method="post">
                <input name="deppid" type="hidden" value=<%=depp.id%>>
                <input name="command" type="hidden" value="deppdelete"/>
                <input type="submit" value="Demolish"/>
            </form>
        </td>
    </tr>
    <% } %>
</table>
<hr/>
<table>
    <tr>
        <td>
            <form action="/department" method="post">
                <input name="command" type="hidden" value="deppadd">
                <input name="newdepptitle" placeholder="New DEPARTMENT Name" value="">
                <input type="submit" value="Increase"/>
            </form>
        </td>
    </tr>
</table>

</body>
</html>