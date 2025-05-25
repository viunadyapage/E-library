
package com.mycompany.perpustakaan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryDAO {
    private final String url = "jdbc:mysql://localhost:3306/perpustakaan";
    private final String user = "root"; // ganti jika user/password MySQL kamu beda
    private final String password = "";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void addLibrary(Library lib) {
        String query = "INSERT INTO library (id, name, location, phoneNumber, operationalHour) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, lib.getId());
            stmt.setString(2, lib.getName());
            stmt.setString(3, lib.getLocation());
            stmt.setString(4, lib.getPhoneNumber());
            stmt.setString(5, lib.getOperationalHour());
            stmt.executeUpdate();
            System.out.println("Library added successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<Library> getAllLibraries() {
        List<Library> libraries = new ArrayList<>();
        String query = "SELECT * FROM library";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                libraries.add(new Library(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getString("phoneNumber"),
                        rs.getString("operationalHour")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return libraries;
    }

    public void updateLibrary(Library lib) {
        String query = "UPDATE library SET name=?, location=?, phoneNumber=?, operationalHour=? WHERE id=?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, lib.getName());
            stmt.setString(2, lib.getLocation());
            stmt.setString(3, lib.getPhoneNumber());
            stmt.setString(4, lib.getOperationalHour());
            stmt.setString(5, lib.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Library updated successfully.");
            } else {
                System.out.println("Library not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteLibrary(String id) {
        String query = "DELETE FROM library WHERE id=?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Library deleted successfully.");
            } else {
                System.out.println("Library not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
