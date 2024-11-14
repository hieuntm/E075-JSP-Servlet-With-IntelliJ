<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>LOGIN</h1>
<br/>
<a href="account">Click me</a>
<form method="POST" action="account">
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
    <button>Sign up</button>
</form>
</body>
</html>
