package com.example.siechoteli.klient.hotels;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class controls hotel preview scene
 * This class does basically the same what HotelsController
 * Please see: {@link HotelsController}
 * @author Lukasz Kiepas
 */
public class HotelsController2 implements Initializable {

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private Circle circleInside;

    /**
     * This function handles back button.
     * @param event In this case ActionEvent reacts on button press [back button]
     * @throws IOException
     *
     */
    public void backToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/siechoteli/mainscene/mainScene2.fxml"));
        //"here we get our stage"
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void nextButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/siechoteli/hotels/hotels3.fxml"));
        //"here we get our stage"
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void previousButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/siechoteli/hotels/hotels.fxml"));
        //"here we get our stage"
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private Image loadImage(String filename) throws IOException {
        try (InputStream is = getClass().getResourceAsStream(filename)) {
            if (is == null) {
                throw new IOException("InputStream is invalid for filename " + filename);
            }
            return new Image(is);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img  = null;
        try {
            img = loadImage("/com/example/siechoteli/images/hotels2.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        circleInside.setFill(new ImagePattern(img));
    }
}
