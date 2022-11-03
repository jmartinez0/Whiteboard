package com.mycompany.whiteboard;

import com.google.cloud.firestore.Firestore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static Firestore fstore;
    private final FirestoreContext context = new FirestoreContext();
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        fstore = context.firebase();
        scene = new Scene(loadFXML("login"), 1024, 576);
        stage.setTitle("Whiteboard");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}