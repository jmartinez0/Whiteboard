package com.mycompany.whiteboard;

import java.io.IOException;
import java.net.URL;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class StudentViewController implements Initializable {

    @FXML
    private Label logOutLabel;
    @FXML
    private ImageView whiteboardLogoImageView;
    Image whiteboardLogo = new Image(getClass().getResourceAsStream("WhiteboardLogoWhite.png"));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        whiteboardLogoImageView.setImage(whiteboardLogo);
    }

    @FXML
    public void courses() {

    }
    
    @FXML
    public void handleGetDoc() {

    }

    public static void getDoc(String projectId, String bucketName,
            String objectName, String destFilePath) {

        // The ID of your GCP project
        projectId = "WhiteboardCSC325";
        // The ID of your GCS bucket
        bucketName = "whiteboardcsc325";
        // The ID of your GCS object
        Scanner myObj = new Scanner(System.in);
        System.out.println("input file name: ");
        objectName = myObj.nextLine();
        // The path to which the file should be downloaded
        System.out.println("input where file should be downloaded:");
        destFilePath = myObj.nextLine();

        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();

        Blob blob = storage.get(BlobId.of(bucketName, objectName));
        blob.downloadTo(Paths.get(destFilePath));

        System.out.println("Downloaded object " + objectName + " from bucket name " + bucketName + " to " + destFilePath);

        myObj.close();
    }

    @FXML
    public void handleLogOut() throws IOException {

        logOut();

    }

    public void logOut() throws IOException {
        Stage oldStage = (Stage) logOutLabel.getScene().getWindow();
        oldStage.close();
        Stage newStage = new Stage();
        Scene scene = new Scene(App.loadFXML("LogIn"), 500, 500);
        newStage.setTitle("Whiteboard");
        newStage.setScene(scene);
        newStage.show();
    }
}
