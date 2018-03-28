
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Department</title>
</head>
<body>
<h3>Change the title</h3>
<form action="/department" method="get">
    <input name="deppid" type="hidden" value="<%=request.getParameter("deppid")%>"/>
    <input name="command" type="hidden" value="deppupdate"/>
    <input name="newdeptitle" type="text" value="<%=request.getParameter("depptitle")%>" />
    <input type="submit" value="Flop!"/>
</form>
</body>
</html>
