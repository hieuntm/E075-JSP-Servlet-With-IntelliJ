package com.example.jweb_start;

import account.AccountService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

import java.io.IOException;
import java.util.List;


// root: /
// account: /accounts
// login : /login
// signup: /signup
@WebServlet(name = "account-servlet", value = "/account")
public class AccountServlet extends HttpServlet {

    private AccountService accountService;

    @Override
    public void init(ServletConfig config) {
        accountService = new AccountService();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String role = req.getParameter("role");
        String password = req.getParameter("password");

        accountService.updateAccount(username, password, role);

        List<Account> test = accountService.getAllAccount();
        req.setAttribute("accounts", test);
        req.getRequestDispatcher("index.jsp").forward(req, resp);    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("IndexServlet doGet");

        String task = req.getParameter("task");
        if (task.equals("delete")) {
            // req.getParam -> mặc định trả về string
//            long accountId = Long.parseLong(req.getParameter("accountId"));
            String username = req.getParameter("username");
            accountService.deleteAccount(username);
            // Thực hiện xong 1 tác vụ
            // Thực hiện xong rồi -> trả lại view
        }

        if (task.equals("update")) {
            long accountId = Long.parseLong(req.getParameter("accountId"));
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String role = req.getParameter("role");
            // Đẩy ra 1 cái view khác, thường thì cái view này nó sẽ gần giống như view tạo mới
            req.setAttribute("accountId", accountId);
            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.setAttribute("role", role);

            req.getRequestDispatcher("update-account.jsp").forward(req, resp);
        }


        List<Account> test = accountService.getAllAccount();
        req.setAttribute("accounts", test);
        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }
}
