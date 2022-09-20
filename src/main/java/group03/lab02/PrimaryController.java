package group03.lab02;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSignUp() throws IOException {
        App.setRoot("SignUpScreen");
    }
}
