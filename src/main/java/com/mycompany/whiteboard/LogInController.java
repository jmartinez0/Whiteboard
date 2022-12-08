package com.mycompany.whiteboard;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label errorLabel;
    @FXML
    private ImageView whiteboardLogoImageView;
    Image whiteboardLogo = new Image(getClass().getResourceAsStream("WhiteboardLogo.png"));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        whiteboardLogoImageView.setImage(whiteboardLogo);
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
        try {
            String name = usernameField.getText();
            // String password = passwordField.getText();
            UserRecord user = App.fauth.getInstance().getUser(name);
            if ((boolean) user.getCustomClaims().get("admin")) {
            // Create a new stage to open a larger window for all three views
            Stage oldStage = (Stage) logInButton.getScene().getWindow();
            oldStage.close();
            // Share name to the next stage
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminView.fxml"));
            Parent admin = loader.load();
            AdminViewController adminController = loader.getController();
            adminController.updateName(user.getDisplayName());
            Stage newStage = new Stage();
            Scene scene = new Scene(admin, 960, 600);
            newStage.setTitle("Whiteboard");
            newStage.getIcons().add(whiteboardLogo);
            newStage.setScene(scene);
            newStage.show();
        } else if ((boolean) user.getCustomClaims().get("faculty")) {
            Stage oldStage = (Stage) logInButton.getScene().getWindow();
            oldStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FacultyView.fxml"));
            Parent faculty = loader.load();
            FacultyViewController facultyController = loader.getController();
            facultyController.updateName(user.getDisplayName());
            Stage newStage = new Stage();
            Scene scene = new Scene(faculty, 960, 600);
            newStage.setTitle("Whiteboard");
            newStage.getIcons().add(whiteboardLogo);
            newStage.setScene(scene);
            newStage.show();
        } else if ((boolean) user.getCustomClaims().get("student")) {
            Stage oldStage = (Stage) logInButton.getScene().getWindow();
            oldStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentView.fxml"));
            Parent student = loader.load();
            StudentViewController studentController = loader.getController();
            studentController.updateName(user.getDisplayName());
            Stage newStage = new Stage();
            Scene scene = new Scene(student, 960, 600);
            newStage.setTitle("Whiteboard");
            newStage.getIcons().add(whiteboardLogo);
            newStage.setScene(scene);
            newStage.show();
        }
        } catch (FirebaseAuthException ex) {
            errorLabel.setStyle("-fx-text-fill: #db2727");
            errorLabel.setText("Incorrect username/password"); 
        } catch (IllegalArgumentException iae) {
            errorLabel.setStyle("-fx-text-fill: #db2727");
            errorLabel.setText("Empty username/password");
        }
    }


}
