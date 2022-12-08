package com.mycompany.whiteboard;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
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
import javax.swing.JFileChooser;

public class FacultyViewController implements Initializable {

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

    @FXML
    public static void handleUploadFile() throws IOException {

        uploadfile();

    }

    public static void uploadfile() throws IOException {
        
        /*JFileChooser fileChooser = new JFileChooser();
        
        String filePath = "path/to/your/file";
        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            //FileInputStream serviceAccount = new FileInputStream(file);
            //FileInputStream serviceAccount = new FileInputStream("whiteboardkey.json");

            // The ID of your GCP project
            String projectId = "WhiteboardCSC325";
            // The ID of your GCS bucket
            String bucketName = "whiteboardcsc325.appspot.com";
            // The ID of your GCS object
            Scanner myObj = new Scanner(System.in);
            System.out.println("input file name: ");
            String objectName = myObj.nextLine();
            // String objectName = "your-object-name";
            // The path to your file to upload
            
            Storage.BlobTargetOption precondition = Storage.BlobTargetOption.doesNotExist();
            
            Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
            BlobId blobId = BlobId.of(bucketName, objectName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
            storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath)), precondition);

            System.out.println(
                    "File " + filePath + " uploaded to bucket " + bucketName + " as " + objectName);
        }*/

    }
}
