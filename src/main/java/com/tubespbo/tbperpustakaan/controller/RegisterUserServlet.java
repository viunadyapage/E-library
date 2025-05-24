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
        authDAO = new AuthDAO(); // Inisialisasi DAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Arahkan ke halaman JSP untuk menampilkan form registrasi
        request.getRequestDispatcher("register_user.jsp").forward(request, response);
        // Jika registerUser.jsp ada di WEB-INF/jsp:
        // request.getRequestDispatcher("/WEB-INF/jsp/registerUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Ambil parameter dari form
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password"); // Ini password plain text
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");

        // TODO: Tambahkan validasi input di sini (misalnya, apakah email sudah ada, password cukup kuat, field tidak kosong)
        // Jika validasi gagal, kirim pesan error dan kembali ke form registrasi. Contoh:
        // if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
        //     request.setAttribute("errorMessage", "Email dan Password tidak boleh kosong.");
        //     request.getRequestDispatcher("registerUser.jsp").forward(request, response);
        //     return;
        // }

        // Buat objek User baru
        User newUser = new User(email, password, username, name, phoneNumber, address);

        // Panggil DAO untuk registrasi
        boolean isRegistered = authDAO.registerUser(newUser);

        if (isRegistered) {
            // Registrasi berhasil
            System.out.println("Registrasi berhasil untuk user: " + newUser.getEmail() + " dengan ID: " + newUser.getAccountID());
            request.setAttribute("successMessage", "Registrasi berhasil! ID Akun Anda: " + newUser.getAccountID() + ". Silakan login.");
            // Arahkan ke halaman login atau halaman sukses lainnya
            request.getRequestDispatcher("login_user.jsp").forward(request, response);
        } else {
            // Registrasi gagal
            System.err.println("Registrasi gagal untuk user: " + newUser.getEmail());
            request.setAttribute("errorMessage", "Registrasi gagal. Email atau username mungkin sudah digunakan, atau terjadi kesalahan server.");
            request.getRequestDispatcher("register_user.jsp").forward(request, response);
        }
    }
}