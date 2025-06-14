/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.tubespbo.tbperpustakaan.controller;

import com.tubespbo.tbperpustakaan.dao.AuthDAO;
import com.tubespbo.tbperpustakaan.model.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet("/viewUsers")
public class ViewUsersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || !"ADMIN".equals(session.getAttribute("accountType"))) {
            response.sendRedirect("login.jsp");
            return;
        }

        AuthDAO dao = new AuthDAO();
        try {
            List<User> users = dao.getAllUsers();
            request.setAttribute("userList", users);
            request.getRequestDispatcher("admin/viewUsers.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
