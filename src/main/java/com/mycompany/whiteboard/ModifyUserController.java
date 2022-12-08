package com.mycompany.whiteboard;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.UpdateRequest;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 *
 * @author juan
 */
public class ModifyUserController implements Initializable {

    @FXML
    private Label usernameLabel;
    @FXML
    private TextField emailField, fullNameField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void updateName(String username) {
        usernameLabel.setText(username);
    }

    public void initializeTextFields(String email, String fullName) {
        emailField.setText(email);
        fullNameField.setText(fullName);
    }

    @FXML
    public void cancel() {
        Stage stage = (Stage) usernameLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void confirm() throws IOException {
        UpdateRequest request = new UpdateRequest(usernameLabel.getText())
                .setEmail(emailField.getText())
                .setDisplayName(fullNameField.getText());
        try {
            UserRecord userRecord = App.fauth.getInstance().updateUser(request);
            cancel();
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(ModifyUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
