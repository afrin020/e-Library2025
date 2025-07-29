package elibrary2024;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegisterSceneController {

    @FXML
    private Text msgText;

    @FXML
    private TextField fullName;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private void handleSubmitAction() {
        msgText.setText("");

        String nameText = fullName.getText().trim();
        String emailText = email.getText().trim();
        String passText = password.getText().trim();

        if (nameText.isEmpty() || emailText.isEmpty() || passText.isEmpty()) {
            msgText.setText("Error: Please fill in all fields.");
            return;
        }

        if (insertUser(nameText, emailText, passText)) {
            Constants.showAlert("Registration successful!");
            openLogin(); // Redirect to login after successful registration
        } else {
            msgText.setText("Error: Could not register user.");
        }
    }

    private boolean insertUser(String fullName, String email, String password) {
        String insertSQL = "INSERT INTO users (fullname, email, password) VALUES (?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
            return false;
        }

        try (Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, fullName);
            pstmt.setString(2, email);
            pstmt.setString(3, password);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    public void openLogin() {
        try {
            Stage currentStage = (Stage) email.getScene().getWindow();
            currentStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScene.fxml"));
            Parent root = loader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(root));
            loginStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            Constants.showAlert("Could not open login screen.");
        }
    }
}
