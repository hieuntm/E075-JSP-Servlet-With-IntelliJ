<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>LOGIN</h1>
<br/>
<p style="color:red"><%=  request.getAttribute("errorMessage")
        != null ? request.getAttribute("errorMessage") : ""%>
</p>
<form method="POST" action="login">
    <table>
        <tr>
            <td>
                <label style="color: red">Username:</label>
            </td>
            <td>
                <input name="username"/>
            </td>
        </tr>
        <tr>
            <td>
                <label>Password:</label>
            </td>
            <td>
                <input name="password" type="password"/>
            </td>
        </tr>
    </table>
    <input type="submit"/>
    <a href="/signup">Sign up</a>
<%--    Click vào button thì chuyển sang form đăng ký--%>
</form>
</body>
</html>
