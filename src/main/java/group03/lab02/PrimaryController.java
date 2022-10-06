package group03.lab02;

import java.io.IOException;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PrimaryController {
    
    
    private String user;
    private String pass;
    
    
    @FXML
    private TextArea TAtest;
    @FXML
    private TextField TFuser;
    @FXML
    private TextField TFpass;
            

    @FXML
    protected void handlelogin() {
        System.out.println("button pressed");
        login();
    }

    @FXML
    private void switchToSignUp() throws IOException {
        App.setRoot("SignUpScreen");
    }
    
    
    @FXML
    public void login() {
        //Scanner scan = new Scanner (new File("the\\dir\\myFile.extension"));
        Scanner keyboard = new Scanner(System.in);

        user = "user";
        pass = "123";
         
        if (TFuser.getText().equals(user) && TFpass.getText().equals(pass)) {
            TAtest.setText("your login message");
        } else {
            TAtest.setText("your error message");
        }
    }
}
