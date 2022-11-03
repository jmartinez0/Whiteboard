package com.mycompany.whiteboard;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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
    private Button readButton;
    @FXML
    private TextArea outputField;
    private boolean key;
    private ObservableList<User> listOfUsers = FXCollections.observableArrayList();
    private User user;
    public ObservableList<User> getListOfUsers() {
        return listOfUsers;
    }
    
    void initialize() {
        AccessDataViewModel accessDataViewModel = new AccessDataViewModel();
        nameField.textProperty().bindBidirectional(accessDataViewModel.usernameProperty());
        usernameField.textProperty().bindBidirectional(accessDataViewModel.passwordProperty());
        nameField.textProperty().bindBidirectional(accessDataViewModel.nameProperty());
        writeButton.disableProperty().bind(accessDataViewModel.isWritePossibleProperty().not());
    }
    
    @FXML
    public void homeButton() throws IOException {
        App.setRoot("login");
    }
    
    @FXML
    public void addStudentData() {
        DocumentReference docRef = App.fstore.collection("Users").document(UUID.randomUUID().toString());
        // Add document data  with id "alovelace" using a hashmap
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
    public void addFacultyData() {
        DocumentReference docRef = App.fstore.collection("Users").document(UUID.randomUUID().toString());
        // Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("Username", usernameField.getText());
        data.put("Password", passwordField.getText());
        data.put("Name", nameField.getText());
        data.put("AccessLevel", 1);
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
    }
    
    @FXML
    public void addAdminData() {
        DocumentReference docRef = App.fstore.collection("Users").document(UUID.randomUUID().toString());
        // Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("Username", usernameField.getText());
        data.put("Password", passwordField.getText());
        data.put("AccessLevel", 2);
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
    }
}
