package com.mycompany.whiteboard;

import java.io.IOException;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }
    
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
    
    @FXML
    private void showAdminRegister() throws IOException {
        App.setRoot("registerAdmin");
    }
    
    @FXML
    private void showFacultyRegister() throws IOException {
        App.setRoot("registerFaculty");
    }
    
    @FXML
    private void showStudentRegister() throws IOException {
        App.setRoot("registerStudent");
    }
    
}
