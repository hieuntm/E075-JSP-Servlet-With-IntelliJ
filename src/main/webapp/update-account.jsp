<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 21.11.2024
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update account</title>
</head>
<body>
<%--Gọi hàm doPOST--%>
    <form action="account" method="POST">
        <table>
            <tr>
                <td>Id</td>
                <td><input type="number" name="id" value="<%= request.getAttribute("accountId")%>" disabled/></td>
            </tr>
            <tr>
                <td>Username</td>
                <td><input type="text" name="username"  value="<%= request.getAttribute("username")%>"/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="text" name="password"  value="<%= request.getAttribute("password")%>"/></td>
            </tr>
            <tr>
                <td>Role</td>
                <td><input type="text" name="role"  value="<%= request.getAttribute("role")%>"/></td>
            </tr>
        </table>
        <input type="submit"/>
    </form>
</body>
</html>
