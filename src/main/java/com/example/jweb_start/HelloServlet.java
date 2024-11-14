package com.example.jweb_start;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

// Annotation -> ngày xưa, dùng file xml để config, cấu hình
// Bây giờ sinh ra Annotation để đơn giản việc config thằng java code

// Sắp tới học Spring boot -> 100% dùng các loại Annotation
//@WebServlet -> đánh dấu cái file java này là 1 servlet
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}