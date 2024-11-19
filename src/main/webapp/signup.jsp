<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>SIGN UP</h1>
<br/>
<p style="color:red"><%=  request.getAttribute("errorMessage")
        != null ? request.getAttribute("errorMessage") : ""%>
</p>
<p style="color:green"><%=  request.getAttribute("successMessage")
        != null ? request.getAttribute("successMessage") : ""%>
</p>
<form method="POST" action="signup">
    <table>
        <tr>
            <td>
                <label style="color: red">Username:</label>
            </td>
            <td>
                <input name="username" type="text"/>
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
        <tr>
            <td>
                <label>Re-password:</label>
            </td>
            <td>
                <input name="repassword" type="password"/>
            </td>
        </tr>
    </table>
    <input type="submit"/>
    <%--   input type submit -> trỏ tới signup servlet -> function doPost --%>
    <a href="/login">Login</a>
    <%--    Click vào button thì chuyển sang form đăng ký--%>
</form>
</body>
</html>
