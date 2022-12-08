package com.mycompany.whiteboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FacultyViewController implements Initializable {

    @FXML private Button coursesButton, logOutButton;
    @FXML private Label nameLabel;
    @FXML private ImageView whiteboardLogoImageView;
    Image whiteboardLogo = new Image(getClass().getResourceAsStream("WhiteboardLogo.png"));
    
    @Override public void initialize(URL url, ResourceBundle rb) {
        whiteboardLogoImageView.setImage(whiteboardLogo);
        viewCourses();
    }

    @FXML public void updateName(String username) {
        nameLabel.setText(username);
    }
    
    @FXML public void viewCourses() {
        coursesButton.setStyle("-fx-background-color: #0071e3;" + "-fx-text-fill: white;");
    }
    
    @FXML public void logOut() throws IOException {
        Stage oldStage = (Stage) logOutButton.getScene().getWindow();
        oldStage.close();
        Stage newStage = new Stage();
        Scene scene = new Scene(App.loadFXML("LogIn"), 500, 500);
        newStage.setTitle("Whiteboard");
        newStage.setScene(scene);
        newStage.show();
    }
}
