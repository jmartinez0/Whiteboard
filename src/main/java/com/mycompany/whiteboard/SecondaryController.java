package com.mycompany.whiteboard;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SecondaryController {
    
    @FXML
    Label userIDLabel;
    
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("LoginScreen");
    }
    
    private int generateUserID(){
        return 12345678;
    }

    @FXML
    public void initialize() {
        
        userIDLabel.setText("Your user ID is " + generateUserID() + ".");
        
    }
}
