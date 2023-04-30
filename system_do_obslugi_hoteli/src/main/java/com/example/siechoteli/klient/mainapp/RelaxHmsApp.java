package com.example.siechoteli.klient.mainapp;

import com.example.siechoteli.klient.login.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.ArrayList;


/**
 * This is a main class which starts our main app.
 * We override start method of Application Class here.
 */
public class RelaxHmsApp extends Application {

    static Instant start;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RelaxHmsApp.class.getResource("/com/example/siechoteli/login/loginScen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        String icon = this.getClass().getResource("/com/example/siechoteli/images/LOGO.png").toExternalForm();
        stage.getIcons().add(new Image(icon));

        stage.setTitle("Relax - HMS!");
        stage.setScene(scene);
        stage.show();
        start = Instant.now();
    }

    // here is a static string which we set with username data in our LoginController class
    public static String login;

    /**
     * This is a function which will always inwoke when we close our program with X.
     * If we do so, we invoke static method staticStop which will send data with employee time logs to
     * our database.
     */
    @Override
        public void stop(){
            staticStop();
        }


    /**
     * This is static function. We made it static to be able to acces it from
     * {@link com.example.siechoteli.klient.mainscene.MainSceneController2}
     * It measures difference between ap starting time and current time.
     * It sends this difference with employeeID to database in formated way
     * using server.
     */
    public static void staticStop(){
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start , end);

            ArrayList<String> myArgList = new ArrayList<>();

            if(login.equals("marek123")){
                myArgList.add("INSERT INTO `emploggedtime`(`LogID`, `empID`, `TimeLogged`) VALUES (null,1,'" +
                        "Employee: "  + login + " has been logged for: " + timeElapsed.toHours() + " hours " + timeElapsed.toMinutes() + " minutes " + timeElapsed.getSeconds() + " seconds.')");
            }else {
                myArgList.add("INSERT INTO `emploggedtime`(`LogID`, `empID`, `TimeLogged`) VALUES (null,2,'" +
                        "Employee: "  + login + " has been logged for: " + timeElapsed.toHours() + " hours " + timeElapsed.toMinutes() + " minutes " + timeElapsed.getSeconds() + " seconds.')");
            }
            Message msg =new Message("_sendLoggedTime",  myArgList);
            Client client  =new Client(25565, "127.0.0.1");
            Message reply = client.executeRequest(msg);
        }




    public static void main(String[] args) {
        launch();
    }
}