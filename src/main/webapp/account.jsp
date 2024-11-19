<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Login successfully</h1>
<%--Lấy theo key--%>
<h3>Hello <%= request.getAttribute("username")%> Tesst</h3>
<h4>Your role:  <%= request.getAttribute("role")%></h4>

<h2 style="color: red">Đẩy list products dưới dạng bảng lên JSP</h2>
<h3>B1. Tạo productService, tạo Product class</h3>
<h3>B2. Sau khi đăng nhập thành công -> đẩy sang trang account.jsp</h3>
<h3>Kèm attribute là 1 list Product, thông qua param, hoặc session</h3>
<img src="simple-list.png" height="300" width="900"/>
</body>
</html>