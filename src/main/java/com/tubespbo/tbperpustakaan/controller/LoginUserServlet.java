/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.tubespbo.tbperpustakaan.controller; // Sesuaikan package

import com.tubespbo.tbperpustakaan.dao.AuthDAO;
// import com.tubespbo.tbperpustakaan.model.Account; // Atau User jika DAO mengembalikan User
import com.tubespbo.tbperpustakaan.model.User; // Menggunakan User dari revisi DAO

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AuthDAO authDAO;

    @Override
    public void init() {
        authDAO = new AuthDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login_user.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User loggedInUser = authDAO.validateUserLogin(email, password);

        if (loggedInUser != null) {
            // Login berhasil
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", loggedInUser);
            session.setAttribute("userEmail", loggedInUser.getEmail());
            session.setAttribute("username", loggedInUser.getUsername());
            session.setAttribute("accountType", "USER");

            response.sendRedirect("user_dashboard.jsp"); 
        } else {
            // Login gagal
            request.setAttribute("errorMessage", "Email atau password salah, atau akun tidak aktif.");
            request.getRequestDispatcher("login_user.jsp").forward(request, response);
        }
    }
}