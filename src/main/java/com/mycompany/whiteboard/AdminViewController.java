package com.mycompany.whiteboard;

import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminViewController implements Initializable {

    @FXML
    private VBox manageUsersScreen;
    @FXML
    private HBox createUsersScreen;
    @FXML
    private Button manageUsersButton, createUsersButton, logOutButton;
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> usernameColumn, emailColumn, fullNameColumn, typeOfUserColumn;
    @FXML
    private Label nameLabel, userCreateLabel, errorLabel;
    @FXML
    private TextField usernameField, emailField, nameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox typeOfUserComboBox;
    @FXML
    private ImageView whiteboardLogoImageView;
    Image whiteboardLogo = new Image(getClass().getResourceAsStream("WhiteboardLogo.png"));

    private ObservableList<User> listOfUsers = FXCollections.observableArrayList();
    private User currentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        whiteboardLogoImageView.setImage(whiteboardLogo);
        String[] options = {"Student", "Faculty", "Admin"};
        typeOfUserComboBox.getItems().addAll(options);
        typeOfUserComboBox.getSelectionModel().selectFirst(); // All new users are students by default
        manageUsers();
    }

    @FXML
    public void updateName(String username) {
        nameLabel.setText(username);
    }

    @FXML
    public void manageUsers() {
        manageUsersButton.setStyle("-fx-background-color: #0071e3;" + "-fx-text-fill: white;");
        createUsersButton.setStyle("-fx-background-color: white;" + "-fx-text-fill: black;");
        manageUsersScreen.setVisible(true);
        createUsersScreen.setVisible(false);
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
        createUsersScreen.setVisible(true);
        manageUsersScreen.setVisible(false);
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

    @FXML
    public void modifyUser() throws IOException, FirebaseAuthException {
        currentUser = table.getSelectionModel().getSelectedItem();
        UserRecord user = App.fauth.getInstance().getUser(currentUser.getUsername());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyUser.fxml"));
        Parent modify = loader.load();
        ModifyUserController modifyUserController = loader.getController();
        modifyUserController.initializeTextFields(user.getEmail(), user.getDisplayName());
        modifyUserController.updateName(user.getUid());
        Stage newStage = new Stage();
        Scene scene = new Scene(modify, 300, 300);
        newStage.setTitle("Modify User");
        newStage.getIcons().add(whiteboardLogo);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    public void deleteUser() throws FirebaseAuthException {
        currentUser = table.getSelectionModel().getSelectedItem();
        App.fauth.getInstance().deleteUser(currentUser.getUsername());
        listAllUsers();
    }

    @FXML
    public void createUser() throws IOException, FirebaseAuthException {
        try {
            // Make a new user record create request
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setUid(usernameField.getText().trim())
                .setPassword(passwordField.getText().trim())
                .setDisplayName(nameField.getText().trim())
                .setEmail(emailField.getText().trim());
            // Make a new UserRecord instance and use the data from the create request
            UserRecord userRecord = App.fauth.createUser(request);
            // Read the combo box to determine if the new user is a student, faculty, or admin
            if (typeOfUserComboBox.getValue().equals("Faculty")) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("faculty", true);
                App.fauth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);
                claims.put("admin", false); // Set the other claims to false so they are not null when trying to log in
                App.fauth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);
                claims.put("student", false);
                App.fauth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);
                userCreateLabel.setText("Successfully created new user");
                userCreateLabel.setStyle("-fx-text-fill: green");
                usernameField.clear();
                emailField.clear();
                nameField.clear();
                passwordField.clear();
            }
            if (typeOfUserComboBox.getValue().equals("Student")) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("student", true);
                App.fauth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);
                claims.put("admin", false);
                App.fauth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);
                claims.put("faculty", false);
                App.fauth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);
                userCreateLabel.setText("Successfully created new user");
                userCreateLabel.setStyle("-fx-text-fill: green");
                usernameField.clear();
                emailField.clear();
                nameField.clear();
                passwordField.clear();
            }
            if (typeOfUserComboBox.getValue().equals("Admin")) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("student", false);
                App.fauth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);
                claims.put("admin", true);
                App.fauth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);
                claims.put("faculty", false);
                App.fauth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);
                userCreateLabel.setText("Successfully created new user");
                userCreateLabel.setStyle("-fx-text-fill: green");
                usernameField.clear();
                emailField.clear();
                nameField.clear();
                passwordField.clear();
            }
        } catch (FirebaseAuthException ex) {
            errorLabel.setStyle("-fx-text-fill: #db2727");
            errorLabel.setText("Username/Email already in use");
            userCreateLabel.setStyle("-fx-text-fill: #db2727");
        } catch (IllegalArgumentException iae) {
            if (usernameField.getText().isEmpty() ||
                passwordField.getText().isEmpty() ||
                nameField.getText().isEmpty() ||
                emailField.getText().isEmpty()) {
                errorLabel.setStyle("-fx-text-fill: #db2727");
                errorLabel.setText("All fields must be filled out");
            } else if (passwordField.getText().length() < 6) {
                errorLabel.setStyle("-fx-text-fill: #db2727");
                errorLabel.setText("Password must be at least 6 characters long");
            } else {
                errorLabel.setStyle("-fx-text-fill: #db2727");
                errorLabel.setText("Email format must be name@example.com");
            }
        }
    }

}
