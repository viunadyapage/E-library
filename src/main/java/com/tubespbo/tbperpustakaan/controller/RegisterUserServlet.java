package com.tubespbo.tbperpustakaan.controller;

import com.tubespbo.tbperpustakaan.dao.AuthDAO;
import com.tubespbo.tbperpustakaan.model.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register") // URL mapping untuk servlet ini
public class RegisterUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AuthDAO authDAO;

    @Override
    public void init() {
        authDAO = new AuthDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register_user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");

        User newUser = new User(email, password, username, name, phoneNumber, address);

        boolean isRegistered = authDAO.registerUser(newUser);

        if (isRegistered) {
            System.out.println("Registrasi berhasil untuk user: " + newUser.getEmail() + " dengan ID: " + newUser.getAccountID());
            request.setAttribute("successMessage", "Registrasi berhasil! ID Akun Anda: " + newUser.getAccountID() + ". Silakan login.");
            request.getRequestDispatcher("login_user.jsp").forward(request, response);
        } else {
            System.err.println("Registrasi gagal untuk user: " + newUser.getEmail());
            request.setAttribute("errorMessage", "Registrasi gagal. Email atau username mungkin sudah digunakan, atau terjadi kesalahan server.");
            request.getRequestDispatcher("register_user.jsp").forward(request, response);
        }
    }
}