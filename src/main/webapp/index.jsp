<%@ page import="model.Product" %>
<%@ page import="model.Account" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>LOGIN</h1>
<br/>
<div>
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
</div>
<div>
    <div>
        <table border="1">
            <th>Id</th>
            <th>Username</th>
            <th>Password</th>
            <th>Role</th>
            <%
                // block code
                List<Account> accounts =
                        (List<Account>) request.getAttribute("accounts");
                for (Account account : accounts) {
            %>
            <tr>
                <td><%= account.getId() %>
                </td>
                <td><%= account.getUsername() %>
                </td>
                <td><%= account.getPassword() %>
                </td>
                <td><%= account.getRole() %>
                </td>
                <td>
                    <a href="/account?task=update&username=<%= account.getUsername() %>&accountId=<%= account.getId() %>&role=<%= account.getRole() %>&password=<%= account.getPassword() %>">Update</a>
<%--           Gọi đến doGet     -> Gọi đến account servet để xử lý việc update    --%>
                </td>
                <td>
                    <a href="/account?task=delete&username=<%= account.getUsername() %>">Delete</a>
<%--           Gọi đến doGet     -> gọi đến account servlet để xử lý việc delete    --%>
<%--        Sẽ sinh thêm những param đi kèm trên url            --%>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</div>
</body>
</html>
