package com.mycompany.whiteboard;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
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
public class AccessFirestore {
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
    private void addRecord(ActionEvent event) {
        addData();
    }

        @FXML
    private void readRecord(ActionEvent event) {
        readFirestore();
    }
    
    public void addData() {
        DocumentReference docRef = App.fstore.collection("Students").document(UUID.randomUUID().toString());
        // Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("Username", usernameField.getText());
        data.put("Password", passwordField.getText());
        data.put("Name", nameField.getText());
        data.put("Major", majorField.getText());
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
    }
    
    
        public boolean readFirestore()
         {
             key = false;

        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future =  App.fstore.collection("Students").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents;
        try 
        {
            documents = future.get().getDocuments();
            if(documents.size()>0)
            {
                System.out.println("Outing....");
                for (QueryDocumentSnapshot document : documents) 
                {
                    outputField.setText(outputField.getText()+ document.getData().get("Name")+ " , Username: "+
                            document.getData().get("Username")+ " , Password: "+
                            document.getData().get("Password")+ " \n ");
                    System.out.println(document.getId() + " => " + document.getData().get("Name"));
                    user = new User(String.valueOf(document.getData().get("Name")), 
                            document.getData().get("Username").toString(),
                            (document.getData().get("Password").toString()));
                    listOfUsers.add(user);
                }
            }
            else
            {
               System.out.println("No data"); 
            }
            key=true;
            
        }
        catch (InterruptedException | ExecutionException ex) 
        {
             ex.printStackTrace();
        }
        return key;
    }
}
