package dao;

import java.sql.*;
import java.util.*;
import model.Library;
import connection.DatabaseConnection;

public class LibraryDAO {
    public static List<Library> getAllLibraries() {
        List<Library> list = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM library");

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
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public static void insertLibrary(Library lib) throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO library VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, lib.getId());
        pst.setString(2, lib.getName());
        pst.setString(3, lib.getLocation());
        pst.setString(4, lib.getPhoneNumber());
        pst.setString(5, lib.getOperationalHour());
        pst.executeUpdate();
    }

    public static void updateLibrary(Library lib) throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO library (name, location, phoneNumber, operationalHour) VALUES (?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, lib.getName());
        pst.setString(2, lib.getLocation());
        pst.setString(3, lib.getPhoneNumber());
        pst.setString(4, lib.getOperationalHour());
        pst.setString(5, lib.getId());
        pst.executeUpdate();
    }

    public static void deleteLibrary(String id) throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pst = conn.prepareStatement("DELETE FROM library WHERE id=?");
        pst.setString(1, id);
        pst.executeUpdate();
    }

    public static Library getLibraryById(String id) throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM library WHERE id=?");
        pst.setString(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return new Library(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("location"),
                rs.getString("phoneNumber"),
                rs.getString("operationalHour")
            );
        }
        return null;
    }
}
