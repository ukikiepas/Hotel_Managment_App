package com.example.siechoteli.klient.mainscene;


import com.example.siechoteli.klient.klientlivechat.LiveChatClient;
import com.example.siechoteli.klient.mainapp.RelaxHmsApp;
import com.example.siechoteli.utilities.Warnings;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * MainSceneController 2 is controller which handles main menu
 * What it does is basically just switching scenes.
 */
public class MainSceneController2
{



    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Switch our scene to Hotels Scene
     * @param event
     * @throws IOException
     */
    public void switchToHotels(ActionEvent event) throws IOException {

            root = FXMLLoader.load(getClass().getResource("/com/example/siechoteli/hotels/hotels.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


    }

    /**
     * Switch our scene to Login Scene
     * And whats more executes a static function from RelaxHmsApp
     * that sends a data into our database table which show how long
     * particular wokrer has been logged in
     * @param event
     * @throws IOException
     */
    public void switchToLogin(ActionEvent event) throws IOException {
        com.example.siechoteli.klient.mainapp.RelaxHmsApp.staticStop();
        root = FXMLLoader.load(getClass().getResource("/com/example/siechoteli/login/loginScen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Switch our scene to Guests Scene
     * @param event
     * @throws IOException
     */
    public void switchToGuests(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/siechoteli/guests/chooseScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Switch our scene to Help Scene
     * @param event
     * @throws IOException
     */
    public void switchToHelp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/siechoteli/helpscene/helpScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switch our scene to Rooms Scene
     * @param event
     * @throws IOException
     */
    public void switchToRooms(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/siechoteli/rooms/rooms.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens new windows which is used to chat with consultant
     * @param event
     * @throws IOException
     */
    public void switchToLiveChat(ActionEvent event) throws IOException {

        try{
            LiveChatClient liveChatClient = new LiveChatClient();
            Stage stage = new Stage();
            liveChatClient.start(stage);
        }catch (Exception e){
            Warnings.warningAlertBusy();
            e.printStackTrace();
        }

    }


    }



