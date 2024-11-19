package com.example.jweb_start;

import account.AccountService;
import account.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

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
// Mac dùng control + option + O => Xoá unused import
// Windows dùng control + alt + O
// Mac dùng control + option + L => Format code
// Windows dùng control + alt + L
@WebServlet(name = "login-servlet", value = "/login")
public class LoginServlet extends HttpServlet {

    private AccountService accountService;
    private ProductService productService;

    @Override
    public void init(ServletConfig config) {
        accountService = new AccountService();
        productService = new ProductService();
    }

    // Xử lý form action submit
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet doPost");

        // request -> lấy dữ liệu
        // response -> trả về dữ liệu hoặc view

        String username = req.getParameter("username"); // Lấy data
        String password = req.getParameter("password");

        System.out.println("Username: " + username);
        System.out.println("Pass: " + password);

        Account account = accountService.getAccountByUsernameAndPassword(username, password);

        String view = "account.jsp";
        if (account == null) {
            view = "index.jsp";
            req.setAttribute("errorMessage", "Username or password is incorrect");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(view);
            requestDispatcher.forward(req, resp);
            // sử dụng redirect -> đăng nhập sai -> bắt đăng nhập lại
        }

        // account có đăng nhập
        // Đẩy dữ liệu lên view kiểu gì?
        req.setAttribute("username", account.getUsername());
        req.setAttribute("role", account.getRole());
        req.setAttribute("products", productService.getAllProducts()); // => Đẩy kèm 1 list các product lên trên view này, việc của mình
        // từ view hiển thị nó ra
        // Truyền kèm data thông qua request
        // Nó sẽ đẩy dữ liệu ra cái view ở phần dispatcher -> view
        // Key - value
        RequestDispatcher rd = req.getRequestDispatcher(view);
        rd.forward(req, resp);
    }

    // Xử lý a href
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet doGet");
        // chưa redirect
        // chưa forward
        resp.sendRedirect("index.jsp");
    }
}