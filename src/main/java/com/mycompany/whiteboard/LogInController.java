package com.mycompany.whiteboard;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
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
        // Create a new stage to open a taller window
        Stage oldStage = (Stage) logInButton.getScene().getWindow();
        oldStage.close();
        Stage newStage = new Stage();
        Scene scene = new Scene(App.loadFXML("SignUp"), 500, 600);
        newStage.setTitle("Whiteboard");
        newStage.getIcons().add(whiteboardLogo);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    public void logIn() throws IOException, FirebaseAuthException {
        // Read the username field and if it's an authenticated user check if it's an admin, faculty, or student
        UserRecord user = App.fauth.getInstance().getUser(usernameField.getText());
        if ((boolean) user.getCustomClaims().get("admin")) {
            // Create a new stage to open a larger window for all three views
            Stage oldStage = (Stage) logInButton.getScene().getWindow();
            oldStage.close();
            Stage newStage = new Stage();
            Scene scene = new Scene(App.loadFXML("AdminView"), 960, 600);
            newStage.setTitle("Whiteboard");
            newStage.getIcons().add(whiteboardLogo);
            newStage.setScene(scene);
            newStage.show();
        } else if ((boolean) user.getCustomClaims().get("faculty")) {
            Stage oldStage = (Stage) logInButton.getScene().getWindow();
            oldStage.close();
            Stage newStage = new Stage();
            Scene scene = new Scene(App.loadFXML("FacultyView"), 960, 600);
            newStage.setTitle("Whiteboard");
            newStage.getIcons().add(whiteboardLogo);
            newStage.setScene(scene);
            newStage.show();
        } else if ((boolean) user.getCustomClaims().get("student")) {
            Stage oldStage = (Stage) logInButton.getScene().getWindow();
            oldStage.close();
            Stage newStage = new Stage();
            Scene scene = new Scene(App.loadFXML("StudentView"), 960, 600);
            newStage.setTitle("Whiteboard");
            newStage.getIcons().add(whiteboardLogo);
            newStage.setScene(scene);
            newStage.show();
        }
    }

}
