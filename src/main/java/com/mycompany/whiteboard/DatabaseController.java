package com.mycompany.whiteboard;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author juan
 */
public class DatabaseController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField majorField;
    @FXML
    private Button writeButton;

    @FXML
    public void homeButton() throws IOException {
        App.setRoot("login");
    }

    @FXML
    public void handleAddStudentData() {

        addStudentData();

    }

    public void addStudentData() {
        DocumentReference docRef = App.fstore.collection("Users").document(UUID.randomUUID().toString());
        Map<String, Object> data = new HashMap<>();
        data.put("Username", usernameField.getText());
        data.put("Password", passwordField.getText());
        data.put("Name", nameField.getText());
        data.put("Major", majorField.getText());
        data.put("AccessLevel", 0);
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
    }

    @FXML
    public void handleAddFacultyData() {

        addFacultyData();

    }

    public void addFacultyData() {
        DocumentReference docRef = App.fstore.collection("Users").document(UUID.randomUUID().toString());
        Map<String, Object> data = new HashMap<>();
        data.put("Username", usernameField.getText());
        data.put("Password", passwordField.getText());
        data.put("Name", nameField.getText());
        data.put("AccessLevel", 1);
        ApiFuture<WriteResult> result = docRef.set(data);
    }

    @FXML
    public void handleAddAdminData() {

        addAdminData();

    }

    public void addAdminData() {
        DocumentReference docRef = App.fstore.collection("Users").document(UUID.randomUUID().toString());
        Map<String, Object> data = new HashMap<>();
        data.put("Username", usernameField.getText());
        data.put("Password", passwordField.getText());
        data.put("AccessLevel", 2);
        ApiFuture<WriteResult> result = docRef.set(data);
    }
}
