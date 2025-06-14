package com.tubespbo.tbperpustakaan.controller;

import com.tubespbo.tbperpustakaan.dao.AuthDAO;
import com.tubespbo.tbperpustakaan.model.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AuthDAO authDAO = new AuthDAO();

        try {
            Admin loggedInAdmin = authDAO.loginAdmin(email, password);

            if (loggedInAdmin != null) {
                HttpSession session = request.getSession();
                session.setAttribute("loggedInAdmin", loggedInAdmin);
                session.setAttribute("username", loggedInAdmin.getUsername());
                session.setAttribute("accountType", "ADMIN");
                session.setAttribute("adminRoleID", loggedInAdmin.getRoleID());
                response.sendRedirect("adminDashboard.jsp");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("message", "Email atau password salah.");
                session.setAttribute("messageType", "danger");
                response.sendRedirect("loginAdmin.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("loginAdmin.jsp");
        }
    }
}