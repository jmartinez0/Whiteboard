package com.mycompany.whiteboard;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SignUpController implements Initializable {

    @FXML
    private TextField usernameField, nameField, emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button signUpButton;
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
        emailField.setFocusTraversable(false);
        String[] options = {"Student", "Faculty"};
        typeOfUserComboBox.getItems().addAll(options);
        typeOfUserComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    public void switchToLogIn() throws IOException {
        Stage oldStage = (Stage) signUpButton.getScene().getWindow();
        oldStage.close();
        Stage newStage = new Stage();
        Scene scene = new Scene(App.loadFXML("LogIn"), 500, 500);
        newStage.setTitle("Whiteboard");
        newStage.getIcons().add(whiteboardLogo);
        newStage.setScene(scene);
        newStage.show();
    }

    public void signUp() {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setUid(usernameField.getText())
                .setPassword(passwordField.getText())
                .setDisplayName(nameField.getText().trim())
                .setEmail(emailField.getText().trim());

        UserRecord userRecord;
        try {
            userRecord = App.fauth.createUser(request);
            System.out.println("Successfully created new user: " + userRecord.getUid());
        } catch (FirebaseAuthException ex) {
            // Logger.getLogger(FirestoreContext.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Could not create new user");
        }
        try {
            App.setRoot("LogIn");
        } catch (IOException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
