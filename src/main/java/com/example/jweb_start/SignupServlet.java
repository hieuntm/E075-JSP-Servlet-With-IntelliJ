package com.example.jweb_start;

import account.AccountService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

import java.io.IOException;


@WebServlet(name = "signup-servlet", value = "/signup")
public class SignupServlet extends HttpServlet {

    private AccountService accountService;

    @Override
    public void init(ServletConfig config) {
        accountService = new AccountService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("When click signup button");
        // Khi mà không redirect thông qua resp
        // Or không trả về dispatcher sang view (req) thì nó sẽ trả trang trắng
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");

        System.out.println("Username: " + username);
        System.out.println("Pass: " + password);
        System.out.println("Repass: " + repassword);

        if(!password.equals(repassword)){
            // chuyển tiếp trực tiếp không kèm data
//            resp.sendRedirect("signup.jsp");
            //
            // kèm thông báo, mật khẩu không trùng khớp
            // Muốn kèm data
            // attribute -> object -> dưới dạng key-value
            // từ key đẩy vào 1 object
            // từ key lấy ra 1 object
            req.setAttribute("errorMessage", "Password not match");
            RequestDispatcher rd = req.getRequestDispatcher("signup.jsp");
            rd.forward(req, resp);
        }
        // 2 Mật khẩu đúng
        Account account = accountService.getAccountByUsernameAndPassword(username, password);
        if(account != null){
            // Đã tồn tại account trong database
            req.setAttribute("errorMessage", "Username is taken");
            RequestDispatcher rd = req.getRequestDispatcher("signup.jsp");
            rd.forward(req, resp);
        }

        // Đăng ký thành công
        accountService.insertAccount(username, password);

        req.setAttribute("successMessage", "Signup success");
        RequestDispatcher rd = req.getRequestDispatcher("signup.jsp");
        rd.forward(req, resp);
    }

    // JSP -> JavaServer Page -> Sự kết hợp của java code và html
    // các sử dụng <%= %> để chèn code java vào html

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SignupServlet doGet");
//         trả ra view
        // redirect to signup.jsp -> chuyển tiếp sang view khác
        // ko kèm data
        resp.sendRedirect("signup.jsp");
        // đối với doGet -> Thường thì là lấy view -> redirect
        // redirect -> chuyển tiếp, không kèm dữ liệu
        // đối với doPost -> submit data và dispatcher tới một view khác
        // dispatcher.forward -> chuyển tiếp có kèm data
    }
}