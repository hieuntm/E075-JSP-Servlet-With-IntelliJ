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
@WebServlet(name = "index-servlet", value = "")
public class IndexServlet extends HttpServlet {

    private AccountService accountService;

    @Override
    public void init(ServletConfig config) {
        accountService = new AccountService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("IndexServlet doGet");
        List<Account> test = accountService.getAllAccount();
        System.out.println(test);
        req.setAttribute("accounts", test);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
