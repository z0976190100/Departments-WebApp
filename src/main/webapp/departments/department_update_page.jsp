<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab" rel="stylesheet">
    <title>Update Department</title>
</head>
<body style="font-family:'Roboto Slab'">
<h3>Change the title</h3>
<form action="/department" method="get">
    <input name="command" type="hidden" value="departmentUpdate"/>
    <input name="deppid" type="hidden" value="${param.deppid}" />
    <input name="newdepptitle" type="text" value="${param.depptitle}" pattern="[a-zA-Z]+" required />
    <input type="submit" value="Flop!"/>
</form>
<hr/>
<c:if test="${(requestScope.responseMessages.get(\"DEPTITLE_SAVE_PROBLEM_MESSAGE\") != null)}" >
    <p style="color:red"><c:out value="${requestScope.responseMessages.get(\"DEPTITLE_SAVE_PROBLEM_MESSAGE\")}"/></p>
</c:if>
<form action="/departmentlist" method="post">
    <input name="command" type="hidden" value="departmentList" />
    <input type="submit" value="<- Back to Main page"/>
</form>
</body>
</html>
