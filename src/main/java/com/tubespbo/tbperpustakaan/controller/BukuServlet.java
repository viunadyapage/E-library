package com.tubespbo.tbperpustakaan.controller;

import com.tubespbo.tbperpustakaan.dao.BukuDAO;
import com.tubespbo.tbperpustakaan.model.Buku;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/BukuServlet")
public class BukuServlet extends HttpServlet {
    private BukuDAO bukuDAO = new BukuDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            // Default: list semua buku
            request.setAttribute("listBuku", bukuDAO.getAllBuku());
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/list_buku.jsp");
            dispatcher.forward(request, response);
        } else {
            switch (action) {
                case "delete":
                    String id = request.getParameter("id");
                    bukuDAO.hapusBuku(id);
                    response.sendRedirect("BukuServlet");
                    break;
                case "edit":
                    String idEdit = request.getParameter("id");
                    Buku buku = bukuDAO.getBukuById(idEdit);
                    request.setAttribute("buku", buku);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("view/form_buku.jsp");
                    dispatcher.forward(request, response);
                    break;
                // Tambahkan aksi lain kalau perlu
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idBuku = request.getParameter("idBuku");
        String judul = request.getParameter("judul");
        String penulis = request.getParameter("penulis");
        String penerbit = request.getParameter("penerbit");
        int tahun = Integer.parseInt(request.getParameter("tahunTerbit"));
        String idStatus = request.getParameter("idStatus");

        Buku buku = new Buku();
        buku.setIdBuku(idBuku);
        buku.setJudul(judul);
        buku.setPenulis(penulis);
        buku.setPenerbit(penerbit);
        buku.setTahunTerbit(tahun);
        buku.setIdStatus(idStatus);

        String action = request.getParameter("action");
        if ("add".equals(action)) {
            bukuDAO.tambahBuku(buku);
        } else if ("update".equals(action)) {
            bukuDAO.editBuku(buku);
        }
        response.sendRedirect("BukuServlet");
    }
}