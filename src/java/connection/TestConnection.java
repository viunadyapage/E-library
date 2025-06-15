import java.sql.Connection;
import connection.DatabaseConnection;

public class TestConnection {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        
        if (conn != null) {
            System.out.println("✅ Koneksi ke database BERHASIL!");
        } else {
            System.out.println("❌ Gagal konek ke database.");
        }
    }
}
