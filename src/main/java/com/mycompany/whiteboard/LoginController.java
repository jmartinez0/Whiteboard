package com.mycompany.whiteboard;

import java.io.IOException;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private void switchToAdmin() throws IOException {
        App.setRoot("admin");
    }
    
    @FXML
    private void switchToFaculty() throws IOException {
        App.setRoot("faculty");
    }
    
    @FXML
    private void switchToStudent() throws IOException {
        App.setRoot("student");
    }

}
