<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Login successfully</h1>
<%--Lấy theo key--%>
<h3>Hello <%= request.getAttribute("username")%></h3>
<h4>Your role:  <%= request.getAttribute("role")%></h4>
</body>
</html>