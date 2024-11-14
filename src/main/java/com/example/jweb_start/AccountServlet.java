package com.example.jweb_start;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


// B1. Extends HttpServlet
// HttpServlet nằm trong thư viện jakarta.servlet-api trong file pom ở project
// B2. Override lại 2 function doPost và doGet
// Và nếu mà có thẻ a trỏ tới servlet account này thì nó sẽ gọi hàm doGet
// nếu mà có 1 action từ form, thì nó sẽ gọi hàm doPost
// B3. Thêm cái annotation
// B4. Khi tạo 1 servlet mới thì chạy lại project
// B5. Sau khi sửa file servlet, thì chạy lại project
// B6. Sau mỗi action, doPost hoặc doGet thì phải trả về 1 view nào đấy
// thông qua requestDispatcher
@WebServlet(name="account-servlet", value="/account")
public class AccountServlet extends HttpServlet {

    //
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AccountServlet doPost");

        // request -> lấy dữ liệu
        // response -> trả về dữ liệu hoặc view

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println("Username: " + username);
        System.out.println("Pass: " + password);

        String view = "error.jsp";
        if(username.equals("admin") && password.equals("123")) {
            view = "account.jsp";
        }

        RequestDispatcher rd = req.getRequestDispatcher(view);
        rd.forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AccountServlet doGet");
    }
}