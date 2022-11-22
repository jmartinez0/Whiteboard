package com.mycompany.whiteboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SignUpController implements Initializable {
    
    @FXML private TextField usernameField, nameField, emailField;
    @FXML private PasswordField passwordField;
    @FXML ImageView whiteboardLogoImageView;
    Image whiteboardLogo = new Image(getClass().getResourceAsStream("WhiteboardLogo.png"));
        
    @Override public void initialize(URL url, ResourceBundle rb) {
        whiteboardLogoImageView.setImage(whiteboardLogo);
        usernameField.setFocusTraversable(false);
        passwordField.setFocusTraversable(false);
        nameField.setFocusTraversable(false);
        emailField.setFocusTraversable(false);
    }
    
    @FXML public void switchToLogIn() throws IOException {
        App.setRoot("LogIn");
    }
    
    
    
}
