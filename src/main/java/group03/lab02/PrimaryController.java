package group03.lab02;

import java.io.IOException;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PrimaryController {

    //private String user;
    //private String pass;
    @FXML
    private TextArea TAtest;

    @FXML
    private TextField TFuser;

    @FXML
    private TextField TFpass;

    @FXML
    protected void handlelogin() {
        login();
    }

    public void login() {
        //Scanner scan = new Scanner (new File("the\\dir\\myFile.extension"));
        Scanner keyboard = new Scanner(System.in);

        Users userList = new Users();

        //Admin
        userList.add(1, "Admin555", "puppies");

        //Student
        userList.add(3, "ImTheStudent", "kittens");

        User theUser = userList.get(TFuser.getText());

        if (theUser != null) {

                TAtest.setText(theUser.login(TFpass.getText()));

        } else {
            TAtest.setText("User does not exist");

        }
    }

    @FXML
    private void switchToSignUp() throws IOException {
        App.setRoot("SignUpScreen");
    }
}
