package elibrary2024;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Constants {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/elib";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "";

    public static void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Optional: Test DB connection
    public static void testConnection() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            if (conn != null) {
                System.out.println("✅ Connected to MySQL database.");
            } else {
                System.out.println("❌ Connection failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
