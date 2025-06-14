/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.tubespbo.tbperpustakaan.controller;

import com.tubespbo.tbperpustakaan.dao.KomentarDAO;
import com.tubespbo.tbperpustakaan.model.Komentar;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author q
 */
@WebServlet(name = "AddKomentar", urlPatterns = {"/AddKomentar"})
public class AddKomentar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddKomentar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddKomentar at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   private static final long serialVersionUID = 1L;
    private final KomentarDAO komentarDAO = new KomentarDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        // Get parameters from request
        String idBerita = request.getParameter("id_berita");
        String isi = request.getParameter("isi");
        String idPengguna = request.getParameter("username"); //  getSession().getAttribute("username"); // Assuming accountID is stored as Integer in session

        // Validate input
        if (idBerita == null || isi == null || idPengguna == null || isi.trim().isEmpty()) {
            response.getWriter().write("Data tidak lengkap atau komentar kosong." + " -- " + idBerita + "   "+  idPengguna );
            return;
        }

                    Komentar komentar = new Komentar();

        
        try {
            // Parse idBerita
            int idBeritaInt = Integer.parseInt(idBerita);

            // Create Komentar object
            komentar.setIsi(isi);
            komentar.setTanggal(new Date()); // Current timestamp
            komentar.setIdPengguna(idPengguna);
            komentar.setIdBerita(idBeritaInt);
            
response.getWriter().write("will komentarDAO.tambahKomentar" );
            // Save comment using DAO
            komentarDAO.tambahKomentar(komentar);

            response.getWriter().write("Komentar berhasil dikirim.");
        } catch (NumberFormatException e) {
            response.getWriter().write("Error: ID berita tidak valid.");
        } catch (SQLException e) {
            response.getWriter().write("Error: Gagal menyimpan komentar ke database." + komentar );
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
