package com.example.siechoteli.klient.guests;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Allows us to choose a proper scene on button click
 */
public class ChooseSceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    /***
     * After pressing one of switching scene button this function forwards us to guests tab
     * @param event event runs when you push appropriate button
     * @throws IOException
     */


    public void switchToGuests(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/siechoteli/guests/guests.fxml"));
        //"here we get our stage"
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /***
     * After pressing one of switching scene button this function forwards us to searchRooms tab
     * @param event event runs when you push appropriate button
     * @throws IOException
     */

    public void switchToSearchRooms(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/siechoteli/guests/chooseRoomsSearch.fxml"));
        //"here we get our stage"
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        String icon = this.getClass().getResource("/com/example/siechoteli/images/LOGO.png").toExternalForm();
        stage.getIcons().add(new Image(icon));
        stage.setTitle("Relax - HMS!");
        stage.initModality(Modality.NONE);
        stage.setResizable(false);
        stage.show();

    }

    /***
     * After pressing one of switching scene button this function forwards us to MainScene2 tab
     * @param event event runs when you click appropriate button
     * @throws IOException
     */

    public void backToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/siechoteli/mainscene/mainScene2.fxml"));
        //"here we get our stage"
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
