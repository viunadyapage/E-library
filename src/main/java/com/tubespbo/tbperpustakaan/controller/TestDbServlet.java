package com.tubespbo.tbperpustakaan.controller; // Sesuaikan dengan package Anda

import com.tubespbo.tbperpustakaan.utils.DBConnection; // Sesuaikan dengan package DbConnection Anda

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date; // Untuk mengambil waktu dari DB
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/testDbConnection") // Ini adalah URL mapping untuk servlet
public class TestDbServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hasil Tes Koneksi Database</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Status Koneksi Database</h1>");

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();

            if (conn != null && !conn.isClosed()) {
                out.println("<p style='color:green;'>Koneksi ke database 'perpusdb' BERHASIL!</p>");

                String sql = "SELECT NOW() AS currentTime";

                try (PreparedStatement pstmt = conn.prepareStatement(sql);
                     ResultSet rs = pstmt.executeQuery()) {

                    if (rs.next()) {
                        Date dbTime = rs.getTimestamp("currentTime");
                        out.println("<p>Waktu saat ini di server database: " + dbTime.toString() + "</p>");
                    } else {
                        out.println("<p style='color:orange;'>Query sederhana berhasil, tapi tidak ada hasil (SELECT NOW()).</p>");
                    }
                } catch (SQLException eQuery) {
                    out.println("<p style='color:red;'>Koneksi berhasil, tapi GAGAL menjalankan query sederhana: " + eQuery.getMessage() + "</p>");
                    eQuery.printStackTrace(out); // Cetak stack trace ke output HTML untuk debug
                }

            } else {
                out.println("<p style='color:red;'>Gagal mendapatkan koneksi ke database (koneksi null atau sudah ditutup).</p>");
            }

        } catch (SQLException e) {
            out.println("<p style='color:red;'>Koneksi ke database GAGAL! Error: " + e.getMessage() + "</p>");
            out.println("<h3>Detail Error (SQLException):</h3>");
            out.println("<pre>");
            e.printStackTrace(out); // Cetak stack trace ke output HTML untuk debug
            out.println("</pre>");
            out.println("<p><strong>Pastikan:</strong></p>");
            out.println("<ul>");
            out.println("<li>Server MySQL Anda berjalan.</li>");
            out.println("<li>Database 'perpusdb' sudah dibuat.</li>");
            out.println("<li>Kredensial (DB_USER, DB_PASSWORD) di DbConnection.java sudah benar.</li>");
            out.println("<li>MySQL Connector/J JAR sudah ada di classpath/dependencies.</li>");
            out.println("</ul>");
        } catch (Exception e) { // Menangkap exception lain seperti dari Class.forName() di DbConnection
            out.println("<p style='color:red;'>Terjadi error umum: " + e.getMessage() + "</p>");
            out.println("<h3>Detail Error (Exception Umum):</h3>");
            out.println("<pre>");
            e.printStackTrace(out); // Cetak stack trace ke output HTML untuk debug
            out.println("</pre>");
        } finally {
            out.println("</body>");
            out.println("</html>");
            if (conn != null) {
                try {
                    conn.close(); // Selalu tutup koneksi setelah selesai digunakan
                } catch (SQLException eClose) {
                    // Bisa di-log, tapi untuk tes ini kita abaikan error saat menutup
                    System.err.println("Error saat menutup koneksi: " + eClose.getMessage());
                }
            }
            out.close();
        }
    }
}