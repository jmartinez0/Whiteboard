module group03.lab02 {
    requires javafx.controls;
    requires javafx.fxml;

    opens group03.lab02 to javafx.fxml;
    exports group03.lab02;
}
