package com.mycompany.whiteboard;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
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
    private ComboBox<String> typeOfUserComboBox;
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
        typeOfUserComboBox.getSelectionModel().selectFirst(); // All new users are students by default
    }

    @FXML
    public void switchToLogIn() throws IOException {
        // Create a new stage that's identical to the original log in screen
        Stage oldStage = (Stage) signUpButton.getScene().getWindow();
        oldStage.close();
        Stage newStage = new Stage();
        Scene scene = new Scene(App.loadFXML("LogIn"), 500, 500);
        newStage.setTitle("Whiteboard");
        newStage.getIcons().add(whiteboardLogo);
        newStage.setScene(scene);
        newStage.show();
    }
    
    @FXML
    public void signUp() throws IOException, FirebaseAuthException {
        // Make a new user record create request
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setUid(usernameField.getText().trim())
                .setPassword(passwordField.getText().trim())
                .setDisplayName(nameField.getText().trim())
                .setEmail(emailField.getText().trim());
        try {
            // Make a new userRecord instance and use the data from the create request
            UserRecord userRecord;
            userRecord = App.fauth.createUser(request);
            System.out.println("Successfully created new user: " + userRecord.getUid());
            // Read the combo box to determine if the new user is a student or faculty
            if (typeOfUserComboBox.getValue().equals("Faculty")) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("faculty", true);
                App.fauth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);
                claims.put("admin", false); // Set the other claims to false so they are not null when trying to log in
                App.fauth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);
                claims.put("student", false);
                App.fauth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);
            }
            if (typeOfUserComboBox.getValue().equals("Student")) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("student", true);
                App.fauth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);
                claims.put("admin", false);
                App.fauth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);
                claims.put("faculty", false);
                App.fauth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);
            }
            Stage oldStage = (Stage) signUpButton.getScene().getWindow();
            oldStage.close();
            Stage newStage = new Stage();
            Scene scene = new Scene(App.loadFXML("LogIn"), 500, 500);
            newStage.setTitle("Whiteboard");
            newStage.getIcons().add(whiteboardLogo);
            newStage.setScene(scene);
            newStage.show();
        } catch (FirebaseAuthException ex) {
            System.out.println("Could not create new user");
        }
    }

}
