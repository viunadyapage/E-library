/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.tubespbo.tbperpustakaan.controller;

import com.tubespbo.tbperpustakaan.dao.BeritaDAO;
import com.tubespbo.tbperpustakaan.dao.KomentarDAO;
import com.tubespbo.tbperpustakaan.model.Berita;
import com.tubespbo.tbperpustakaan.model.Komentar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/berita")
public class BeritaServlet extends HttpServlet {
    private BeritaDAO beritaDAO;
    private KomentarDAO komentarDAO;
    
    @Override
    public void init() throws ServletException {
        beritaDAO = new BeritaDAO();
        komentarDAO = new KomentarDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            showBeritaList(request, response);
        } else {
            switch (action) {
                case "view":
                    viewBerita(request, response);
                    break;
                case "add":
                    showAddForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteBerita(request, response);
                    break;
                default:
                    showBeritaList(request, response);
            }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        switch (action) {
            case "create":
                createBerita(request, response);
                break;
            case "update":
                updateBerita(request, response);
                break;
            default:
                showBeritaList(request, response);
        }
    }
    
    private void showBeritaList(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Berita> beritaList = beritaDAO.getDaftarBerita();
        request.setAttribute("beritaList", beritaList);
        request.getRequestDispatcher("/WEB-INF/views/berita-list.jsp").forward(request, response);
    }
    
    private void viewBerita(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Berita berita = beritaDAO.getBeritaById(id);
        List<Komentar> komentarList = komentarDAO.getKomentarByBerita(id);
        
        request.setAttribute("berita", berita);
        request.setAttribute("komentarList", komentarList);
        request.getRequestDispatcher("/WEB-INF/views/berita-detail.jsp").forward(request, response);
    }
    
    private void showAddForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/berita-form.jsp").forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Berita berita = beritaDAO.getBeritaById(id);
        request.setAttribute("berita", berita);
        request.getRequestDispatcher("/WEB-INF/views/berita-form.jsp").forward(request, response);
    }
    
    private void createBerita(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        String judul = request.getParameter("judul");
        String isi = request.getParameter("isi");
        String penulis = request.getParameter("penulis");
        
        Berita berita = new Berita();
        berita.setJudul(judul);
        berita.setIsi(isi);
        berita.setPenulis(penulis);
        berita.setTanggal(new Date());
        
        boolean success = beritaDAO.tambahBerita(berita);
        if (success) {
            response.sendRedirect("berita?message=success");
        } else {
            response.sendRedirect("berita?message=error");
        }
    }
    
    private void updateBerita(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String judul = request.getParameter("judul");
        String isi = request.getParameter("isi");
        String penulis = request.getParameter("penulis");
        
        Berita berita = new Berita();
        berita.setId(id);
        berita.setJudul(judul);
        berita.setIsi(isi);
        berita.setPenulis(penulis);
        
        boolean success = beritaDAO.editBerita(berita);
        if (success) {
            response.sendRedirect("berita?message=updated");
        } else {
            response.sendRedirect("berita?message=error");
        }
    }
    
    private void deleteBerita(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean success = beritaDAO.hapusBerita(id);
        
        if (success) {
            response.sendRedirect("berita?message=deleted");
        } else {
            response.sendRedirect("berita?message=error");
        }
    }
}