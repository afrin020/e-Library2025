package elibrary2024;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginSceneController {

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    private final String demoEmail = "admin@example.com";
    private final String demoPassword = "admin123";

    @FXML
    private void handleSubmitAction() {
        String email = userName.getText().trim();
        String pass = password.getText().trim();

        if (email.isEmpty() || pass.isEmpty()) {
            Constants.showAlert("Please enter both email and password.");
            return;
        }

        if (email.equals(demoEmail) && pass.equals(demoPassword)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                Parent root = loader.load();

                Stage dashboardStage = new Stage();
                dashboardStage.setTitle("Dashboard");
                dashboardStage.setScene(new Scene(root));
                dashboardStage.show();

                // Close login window
                Stage currentStage = (Stage) userName.getScene().getWindow();
                currentStage.close();
            } catch (Exception e) {
                e.printStackTrace();
                Constants.showAlert("Could not load dashboard.");
            }
        } else {
            Constants.showAlert("Invalid email or password.");
        }
    }

    @FXML
    private void openRegister() {
        try {
            Stage currentStage = (Stage) userName.getScene().getWindow();
            currentStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("registerscene.fxml"));
            Parent root = loader.load();

            Stage registerStage = new Stage();
            registerStage.setScene(new Scene(root));
            registerStage.setTitle("Register");
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            Constants.showAlert("Could not load register page.");
        }
    }
}
