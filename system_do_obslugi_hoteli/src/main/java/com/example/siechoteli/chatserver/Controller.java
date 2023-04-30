package com.example.siechoteli.chatserver;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This is a main controller of our LiveChatServer app
 */
public class Controller implements Initializable {


    @FXML
    private Button button_send;

    @FXML
    private VBox vbox_messages;

    @FXML
    private TextField tf_message;

    @FXML
    private ScrollPane sp_main;

    private Server2 server2;

    /**
     * This function Overides initialize method. We need that class because our class implements Initializable
     * Here we create new server object with lines server2 = new Server2(new ServerSocker(port));
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            //creating new server socket connection
            server2 = new Server2(new ServerSocket(1234));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("EROR creating server!!!");
        }
        //Overriding and adding listener for vbox
        vbox_messages.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                sp_main.setVvalue((Double) newValue);
            }
        });


        /**
         * This is blocking method which is waiting for
         * Message from client
         */
        server2.receiveMessageFromClient(vbox_messages);

        /**
         * Here we provide specific implemenations on button send.
         * This is Client part of app.
         */
        button_send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String messageToSend = tf_message.getText();
                if(!messageToSend.isEmpty()){
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER_RIGHT);
                    hBox.setPadding(new Insets(5,5,5,10));

                    Text text = new Text(messageToSend);
                    TextFlow textFlow = new TextFlow(text);

                    textFlow.setStyle("-fx-color: rgb(239,242,255);" +
                            "-fx-background-color: rgb(15,125,242);" +
                            "-fx-background-radius: 20px;");

                    textFlow.setPadding(new Insets(5,10,5,10));
                    text.setFill(Color.color(0.934, 0.945,0.996));

                    hBox.getChildren().add(textFlow);
                    vbox_messages.getChildren().add(hBox);

                    server2.sendMessageToClient(messageToSend);
                    tf_message.clear();

                }
            }
        });


    }

    /**
     * This function is used for GUI.
     * After sending a message we want a message to be displayed on GUI.
     * And that is exactly what this function does.
     * @param messageFromClient
     * @param vbox
     */
    public static void addLabel(String messageFromClient, VBox vbox){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5,5,5,10));

        Text text = new Text(messageFromClient);
        TextFlow textFlow = new TextFlow(text);

        textFlow.setStyle(
                "-fx-background-color: rgb(233,233,235);" +
                "-fx-background-radius: 20px;");

        textFlow.setPadding(new Insets(5,10,5,10));
        hBox.getChildren().add(textFlow);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vbox.getChildren().add(hBox);
            }
        });
    }
}
