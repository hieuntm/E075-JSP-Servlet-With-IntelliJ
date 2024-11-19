<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
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
<h4>Your role:  <%= request.getAttribute("role")%>
</h4>

<h2 style="color: red">Đẩy list products dưới dạng bảng lên JSP</h2>
<h3>B1. Tạo productService, tạo Product class</h3>
<h3>B2. Sau khi đăng nhập thành công -> đẩy sang trang account.jsp</h3>
<h3>Kèm attribute là 1 list Product, thông qua param, hoặc session</h3>
<img src="simple-list.png" height="300" width="900"/>

<div>
    <h2>Search bar</h2>
    <input type="text" placeholder="Search name of product..."/>
    <button>Search</button>
</div>

<div>
    <table border="1">
        <th>Product Code</th>
        <th>Product Name</th>
        <th>Product Line</th>
        <th>Product Scale</th>
        <th>Product Vendor</th>
        <th>Product Description</th>
        <th>Quantity In Stock</th>
        <th>Buy Price</th>
        <th>MSRP</th>
        <%
            // block code
            List<Product> products =
                    (List<Product>) request.getAttribute("products");
            for (Product product : products) {
        %>
        <tr>
            <%--         Get Value, Lấy giá trị 1 biến   --%>
            <td><%= product.getProductCode() %>
            </td>
            <td><%= product.getProductName() %>
            </td>
            <td><%= product.getProductLine() %>
            </td>
            <td><%= product.getProductScale() %>
            </td>
            <td><%= product.getProductVendor() %>
            </td>
            <td><%= product.getProductDescription() %>
            </td>
            <td><%= product.getQuantityInStock() %>
            </td>
            <td><%= product.getBuyPrice() %>
            </td>
            <td><%= product.getMSRP() %>
            </td>
            <td>
                <a href="">Update</a>
            </td>
            <td>
                <a href="">Delete</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>