module com.mycompany.whiteboard {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.whiteboard to javafx.fxml;
    exports com.mycompany.whiteboard;
    
}
