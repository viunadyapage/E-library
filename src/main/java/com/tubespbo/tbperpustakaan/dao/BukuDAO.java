package com.tubespbo.tbperpustakaan.dao;  

import com.tubespbo.tbperpustakaan.model.Buku;  
import com.tubespbo.tbperpustakaan.utils.DBConnection;  

import java.sql.*;  
import java.util.ArrayList;  
import java.util.List;  

public class BukuDAO {  //ini semua bisa dilakukan oleh admin ya

    // Tambah buku baru  
    public void tambahBuku(Buku buku) {  
        String sql = "INSERT INTO buku (idBuku, judul, penulis, penerbit, tahunTerbit, idStatus) VALUES (?, ?, ?, ?, ?, ?)";  
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {  
            stmt.setString(1, buku.getIdBuku());  
            stmt.setString(2, buku.getJudul());  
            stmt.setString(3, buku.getPenulis());  
            stmt.setString(4, buku.getPenerbit());  
            stmt.setInt(5, buku.getTahunTerbit());  
            stmt.setString(6, buku.getIdStatus());  
            stmt.executeUpdate();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  

    // Edit buku  
    public void editBuku(Buku buku) {  
        String sql = "UPDATE buku SET judul=?, penulis=?, penerbit=?, tahunTerbit=?, idStatus=? WHERE idBuku=?";  
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {  
            stmt.setString(1, buku.getJudul());  
            stmt.setString(2, buku.getPenulis());  
            stmt.setString(3, buku.getPenerbit());  
            stmt.setInt(4, buku.getTahunTerbit());  
            stmt.setString(5, buku.getIdStatus());  
            stmt.setString(6, buku.getIdBuku());  
            stmt.executeUpdate();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  

    // Hapus buku  
    public void hapusBuku(String idBuku) {  
        String sql = "DELETE FROM buku WHERE idBuku=?";  
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {  
            stmt.setString(1, idBuku);  
            stmt.executeUpdate();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  

    // Ubah status buku  
    public void ubahStatus(String idBuku, String statusId) {  
        String sql = "UPDATE buku SET idStatus=? WHERE idBuku=?";  
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {  
            stmt.setString(1, statusId);  
            stmt.setString(2, idBuku);  
            stmt.executeUpdate();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
    
    // Mendapatkan daftar semua buku  
    public List<Buku> getAllBuku() {  
        List<Buku> list = new ArrayList<>();  
        String sql = "SELECT * FROM buku";  

        try (Connection conn = DBConnection.getConnection();  
             PreparedStatement stmt = conn.prepareStatement(sql);  
             ResultSet rs = stmt.executeQuery()) {  
            while (rs.next()) {  
                Buku buku = new Buku();  
                buku.setIdBuku(rs.getString("idBuku"));  
                buku.setJudul(rs.getString("judul"));  
                buku.setPenulis(rs.getString("penulis"));  
                buku.setPenerbit(rs.getString("penerbit"));  
                buku.setTahunTerbit(rs.getInt("tahunTerbit"));  
                buku.setIdStatus(rs.getString("idStatus"));  
                list.add(buku);  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return list;  
    }  
    
    

    // Mendapatkan buku berdasarkan ID  
    public Buku getBukuById(String idBuku) {  
//        Buku buku = null;  
//        String sql = "SELECT * FROM buku WHERE idBuku=?";  
//        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {  
//            stmt.setString(1, idBuku);  
//            try (ResultSet rs = stmt.executeQuery()) {  
//                if (rs.next()) {  
//                    buku = new Buku();  
//                    buku.setIdBuku(rs.getString("idBuku"));  
//                    buku.setJudul(rs.getString("judul"));  
//                    buku.setPenulis(rs.getString("penulis"));  
//                    buku.setPenerbit(rs.getString("penerbit"));  
//                    buku.setTahunTerbit(rs.getInt("tahunTerbit"));  
//                    buku.setIdStatus(rs.getString(""
//                            + ""
//                            + 
        return null;
    }
}