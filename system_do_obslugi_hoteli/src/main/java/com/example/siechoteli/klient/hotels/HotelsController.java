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
 * <p>We also here prepare an image for future loading into circle</p>
 * @author Lukasz Kiepas
 */
public class HotelsController implements Initializable {

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private Circle circleInside;

    /**
     *  This function handles next back and switch scene to mainScene2.fxml.
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

    /**
     * This function handles next button and switch scene to hotels2.fxml.
     * @param event In this case ActionEvent reacts on button press [back button]
     * @throws IOException
     *
     */
    public void nextButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/siechoteli/hotels/hotels2.fxml"));
        //"here we get our stage"
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This function is used for loading image as an input stream, cause that is the only way that
     * has worked properly. We use it later in initialize method to load image into Circle
     * @param filename
     * @return
     * @throws IOException
     */
    private Image loadImage(String filename) throws IOException {
        try (InputStream is = getClass().getResourceAsStream(filename)) {
            if (is == null) {
                throw new IOException("InputStream is invalid for filename " + filename);
            }
            return new Image(is);
        }
    }


    /**
     * This function loads image into circle to make image look rounded.
     * We use initialize function because we want to load image into circle immediately after switching scenes
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img  = null;
        try {
            img = loadImage("/com/example/siechoteli/images/hotels.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        circleInside.setFill(new ImagePattern(img));
    }
}
