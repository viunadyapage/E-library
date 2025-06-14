
package com.mycompany.perpustakaan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryDAO {

    // Create / Insert
    public void addLibrary(Library library) {
        String sql = "INSERT INTO library (id, name, location, phoneNumber, operationalHour) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, library.getId());
            stmt.setString(2, library.getName());
            stmt.setString(3, library.getLocation());
            stmt.setString(4, library.getPhoneNumber());
            stmt.setString(5, library.getOperationalHour());
            stmt.executeUpdate();
            System.out.println("Data berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan: " + e.getMessage());
        }
    }

    // Read
    public List<Library> getAllLibraries() {
        List<Library> list = new ArrayList<>();
        String sql = "SELECT * FROM library";

        try (Connection conn = Koneksi.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Library lib = new Library(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getString("phoneNumber"),
                    rs.getString("operationalHour")
                );
                list.add(lib);
            }
        } catch (SQLException e) {
            System.out.println("Gagal membaca data: " + e.getMessage());
        }

        return list;
    }

    // Update
    public void updateLibrary(Library library) {
        String sql = "UPDATE library SET name=?, location=?, phoneNumber=?, operationalHour=? WHERE id=?";

        try (Connection conn = Koneksi.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, library.getName());
            stmt.setString(2, library.getLocation());
            stmt.setString(3, library.getPhoneNumber());
            stmt.setString(4, library.getOperationalHour());
            stmt.setString(5, library.getId());
            stmt.executeUpdate();
            System.out.println("Data berhasil diperbarui.");
        } catch (SQLException e) {
            System.out.println("Gagal update: " + e.getMessage());
        }
    }

    // Delete
    public void deleteLibrary(String id) {
        String sql = "DELETE FROM library WHERE id=?";

        try (Connection conn = Koneksi.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Data berhasil dihapus.");
        } catch (SQLException e) {
            System.out.println("Gagal hapus: " + e.getMessage());
        }
    }
}
