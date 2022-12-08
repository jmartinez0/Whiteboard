package com.mycompany.whiteboard;

import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AdminViewController implements Initializable {

    @FXML
    private Button manageUsersButton, createUsersButton, logOutButton;
    @FXML
    private Label nameLabel;
    @FXML
    private ImageView whiteboardLogoImageView;
    Image whiteboardLogo = new Image(getClass().getResourceAsStream("WhiteboardLogo.png"));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        whiteboardLogoImageView.setImage(whiteboardLogo);
        manageUsers();
        try {
            listAllUsers();
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(AdminViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void updateName(String username) {
        nameLabel.setText(username);
    }

    @FXML
    public void manageUsers() {
        manageUsersButton.setStyle("-fx-background-color: #0071e3;" + "-fx-text-fill: white;");
        createUsersButton.setStyle("-fx-background-color: white;" + "-fx-text-fill: black;");
    }
    
    @FXML
    public void createUsers() {
        manageUsersButton.setStyle("-fx-background-color: white;" + "-fx-text-fill: black;");
        createUsersButton.setStyle("-fx-background-color: #0071e3;" + "-fx-text-fill: white;");
    }

    @FXML
    public void logOut() throws IOException {
        Stage oldStage = (Stage) logOutButton.getScene().getWindow();
        oldStage.close();
        Stage newStage = new Stage();
        Scene scene = new Scene(App.loadFXML("LogIn"), 500, 500);
        newStage.setTitle("Whiteboard");
        newStage.setScene(scene);
        newStage.show();
    }

    public void listAllUsers() throws FirebaseAuthException {
        ListUsersPage page = App.fauth.getInstance().listUsers(null);
        while (page != null) {
            for (ExportedUserRecord user : page.getValues()) {
                System.out.println("User: " + user.getUid());
                System.out.println("email: " + user.getEmail());
                System.out.println("full name: " + user.getDisplayName());
                checkTypeOfUser(user);
            }
            page = page.getNextPage();
        }
    }
    
    public String checkTypeOfUser(ExportedUserRecord user) {
        System.out.println(user.getCustomClaims());
        return "s";
    }

}
