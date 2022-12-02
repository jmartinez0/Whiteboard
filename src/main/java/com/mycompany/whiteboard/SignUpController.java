package com.mycompany.whiteboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SignUpController implements Initializable {

    @FXML
    private TextField usernameField, nameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox typeOfUserComboBox;
    @FXML
    ImageView whiteboardLogoImageView;
    Image whiteboardLogo = new Image(getClass().getResourceAsStream("WhiteboardLogo.png"));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        whiteboardLogoImageView.setImage(whiteboardLogo);
        usernameField.setFocusTraversable(false);
        passwordField.setFocusTraversable(false);
        nameField.setFocusTraversable(false);
        String[] options = {"Student", "Faculty"};
        typeOfUserComboBox.getItems().addAll(options);
        typeOfUserComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    public void switchToLogIn() throws IOException {
        App.setRoot("LogIn");
    }

}
