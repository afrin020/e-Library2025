package elibrary2024;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.InputStream;

public class DashboardController {

    @FXML
    private Label welcomeLabel;
    
    @FXML
    private ImageView logoImageView; // Add this for your SMUCT logo
    
    @FXML
    private ImageView book1ImageView; // Example for book images
    
    public void initialize() {
        welcomeLabel.setText("Welcome to the Dashboard!");
        loadImages();
    }
    
    private void loadImages() {
        try {
            // Load SMUCT logo
            InputStream logoStream = getClass().getResourceAsStream("/elibrary2024/images/SMUCT-logo-01.png");
            if (logoStream != null) {
                Image logoImage = new Image(logoStream);
                logoImageView.setImage(logoImage);
            } else {
                System.err.println("Logo image not found!");
            }
            
            // Load book images (example for one book)
            InputStream bookStream = getClass().getResourceAsStream("/elibrary2024/images/book1.jpeg");
            if (bookStream != null) {
                Image bookImage = new Image(bookStream);
                book1ImageView.setImage(bookImage);
            } else {
                System.err.println("Book image not found!");
            }
            
        } catch (Exception e) {
            System.err.println("Error loading images: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

