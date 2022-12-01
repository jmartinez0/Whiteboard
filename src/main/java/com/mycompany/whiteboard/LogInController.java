package com.mycompany.whiteboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LogInController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button logInButton;
    @FXML
    private ImageView whiteboardLogoImageView;
    Image whiteboardLogo = new Image(getClass().getResourceAsStream("WhiteboardLogo.png"));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        whiteboardLogoImageView.setImage(whiteboardLogo);
        usernameField.setFocusTraversable(false);
        passwordField.setFocusTraversable(false);
    }

    @FXML
    public void switchToSignUp() throws IOException {
        App.setRoot("SignUp");
    }
    
    @FXML
    public void handleLogIn() throws IOException {
        
        logIn();
        
    }

    @FXML
    public void logIn() throws IOException {
        if (usernameField.getText().equals("admin")) {
            Stage oldStage = (Stage) logInButton.getScene().getWindow();
            oldStage.close();
            Stage newStage = new Stage();
            Scene scene = new Scene(App.loadFXML("AdminView"), 960, 600);
            newStage.setTitle("Whiteboard");
            newStage.setScene(scene);
            newStage.show();
        }
        if (usernameField.getText().equals("faculty")) {
            Stage oldStage = (Stage) logInButton.getScene().getWindow();
            oldStage.close();
            Stage newStage = new Stage();
            Scene scene = new Scene(App.loadFXML("FacultyView"), 960, 600);
            newStage.setTitle("Whiteboard");
            newStage.setScene(scene);
            newStage.show();
        }
        if (usernameField.getText().equals("student")) {
            Stage oldStage = (Stage) logInButton.getScene().getWindow();
            oldStage.close();
            Stage newStage = new Stage();
            Scene scene = new Scene(App.loadFXML("StudentView"), 960, 600);
            newStage.setTitle("Whiteboard");
            newStage.setScene(scene);
            newStage.show();
        }
    }

}
