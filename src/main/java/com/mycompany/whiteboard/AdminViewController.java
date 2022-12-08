package com.mycompany.whiteboard;

import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AdminViewController implements Initializable {

    @FXML
    private Button manageUsersButton, createUsersButton, logOutButton;
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> usernameColumn, emailColumn, fullNameColumn, typeOfUserColumn;
    @FXML
    private Label nameLabel;
    @FXML
    private ImageView whiteboardLogoImageView;
    Image whiteboardLogo = new Image(getClass().getResourceAsStream("WhiteboardLogo.png"));

    private ObservableList<User> listOfUsers = FXCollections.observableArrayList();
    private User currentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        whiteboardLogoImageView.setImage(whiteboardLogo);
        manageUsers();
    }

    @FXML
    public void updateName(String username) {
        nameLabel.setText(username);
    }

    @FXML
    public void manageUsers() {
        Platform.runLater(() -> manageUsersButton.setStyle("-fx-background-color: #0071e3;" + "-fx-text-fill: white;"));
        Platform.runLater(() -> createUsersButton.setStyle("-fx-background-color: white;" + "-fx-text-fill: black;"));
        try {
            listAllUsers();
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(AdminViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        typeOfUserColumn.setCellValueFactory(new PropertyValueFactory<User, String>("type"));
        table.getItems().clear();
        // Export user records
        ListUsersPage page = App.fauth.getInstance().listUsers(null);
        while (page != null) {
            for (ExportedUserRecord user : page.getValues()) {
                UserRecord userRecord = App.fauth.getInstance().getUser(user.getUid());
                System.out.println(user.getEmail());
                currentUser = new User(user.getUid(), user.getEmail(), user.getDisplayName(), checkTypeOfUser(userRecord));
                listOfUsers = table.getItems();
                listOfUsers.add(currentUser);
                table.setItems(listOfUsers);
            }
            page = page.getNextPage();
        }
    }

    public String checkTypeOfUser(UserRecord user) {
        if ((boolean) user.getCustomClaims().get("admin")) {
            return "Admin";
        }
        if ((boolean) user.getCustomClaims().get("faculty")) {
            return "Faculty";
        }
        if ((boolean) user.getCustomClaims().get("student")) {
            return "Student";
        }
        return null;
    }

}
