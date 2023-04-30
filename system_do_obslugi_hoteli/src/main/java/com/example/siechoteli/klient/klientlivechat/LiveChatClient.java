

package com.example.siechoteli.klient.klientlivechat;

import com.example.siechoteli.klient.mainapp.RelaxHmsApp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class does almost exactly the same as package :{@link com.example.siechoteli.chatserver}
 */
public class LiveChatClient extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RelaxHmsApp.class.getResource("/com/example/siechoteli/livechat/sample2.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        String icon = this.getClass().getResource("/com/example/siechoteli/images/LOGO.png").toExternalForm();
        stage.getIcons().add(new Image(icon));

        stage.setTitle("LiveChat - Worker");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
