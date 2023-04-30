package com.example.siechoteli.klient.rooms;


import com.example.siechoteli.klient.hotels.HotelsController;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * RoomsController
 * <p>This class mostly switch scenes. We also here prepare an image for future loading into circle</p>
 * For more info please see: {@link HotelsController}
 * What is different from above example is that now, we open initiate Modality on new Scenes what causes opening
 * in separate window.
 */
public class RoomsController implements Initializable {

    @FXML
    private Circle circleInside1;

    @FXML
    private Circle circleInside2;

    @FXML
    private Circle circleInside3;


    private Parent root;
    private Stage stage;
    private Scene scene;

    public void backToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/siechoteli/mainscene/mainScene2.fxml"));
        //"here we get our stage"
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void changeToSpectra(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/siechoteli/rooms/RoomsSpectra.fxml"));
        //"here we get our stage"
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        String icon = this.getClass().getResource("/com/example/siechoteli/images/LOGO.png").toExternalForm();
        stage.getIcons().add(new Image(icon));
        stage.setTitle("Relax - HMS!");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }


    public void changeToLJH(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/siechoteli/rooms/RoomsSpectra.fxml"));
        //"here we get our stage"
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        String icon = this.getClass().getResource("/com/example/siechoteli/images/LOGO.png").toExternalForm();
        stage.getIcons().add(new Image(icon));
        stage.setTitle("Relax - HMS!");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }


    public void changeToColony(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/siechoteli/rooms/RoomsSpectra.fxml"));
        //"here we get our stage"
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        String icon = this.getClass().getResource("/com/example/siechoteli/images/LOGO.png").toExternalForm();
        stage.getIcons().add(new Image(icon));
        stage.setTitle("Relax - HMS!");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    /**
     * This is functions which loads image into Image object.
     * It is used later to laod images into circles.
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
     * When this scene is launching we instantly
     * load our images into circles using loadImage function
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
        circleInside1.setFill(new ImagePattern(img));

        try {
            img = loadImage("/com/example/siechoteli/images/hotels2.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        circleInside2.setFill(new ImagePattern(img));

        try {
            img = loadImage("/com/example/siechoteli/images/hotels3.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        circleInside3.setFill(new ImagePattern(img));
    }

}

