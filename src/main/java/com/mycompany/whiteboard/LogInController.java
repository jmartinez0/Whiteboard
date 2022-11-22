package com.mycompany.whiteboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LogInController implements Initializable {
    
    @FXML ImageView whiteboardLogoImageView;
    Image whiteboardLogo = new Image(getClass().getResourceAsStream("WhiteboardLogo.png"));
    @FXML private TextField usernameField, passwordField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        whiteboardLogoImageView.setImage(whiteboardLogo);
        usernameField.setFocusTraversable(false);
        passwordField.setFocusTraversable(false);
    }
    
    
   
}
