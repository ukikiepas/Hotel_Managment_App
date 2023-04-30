package com.example.siechoteli.klient.login;

import com.example.siechoteli.klient.mainapp.Message;
import com.example.siechoteli.klient.mainapp.Client;
import com.example.siechoteli.klient.mainapp.RelaxHmsApp;
import com.example.siechoteli.utilities.Warnings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Logging class.
 * Thiss class is used for fist scene - loging scene
 * Login data is being validated with database records.
 * @author Lukasz Kiepas
 *
 */
public class LoginController implements Initializable {

    @FXML
    private  TextField loginTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label errorLabel;






    private Stage stage;
    private Scene scene;
    private Parent root;





    /**
     *Simple function which encryptes our password.
     * @return String
     */
    public String Encryption(String value)
    {


        String encryptedpassword = null;
        try
        {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(value.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            encryptedpassword = s.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        /* Display the unencrypted and encrypted passwords. */
        return encryptedpassword;

    }


    /**
     * This function get validate our data and if it is good
     * It's allow as to log in
     * @param event
     * @throws IOException
     */
    public void loginMethod(ActionEvent event) throws IOException {
        com.example.siechoteli.klient.mainapp.RelaxHmsApp.login = loginTextField.getText();

        List<String> passList = new ArrayList<>();
        passList.add(loginTextField.getText());
        passList.add(Encryption(passwordTextField.getText()));

        Message msg =new Message("_login", passList);
        Client client  =new Client(25565, "127.0.0.1");
        Message reply = client.executeRequest(msg);

        //password checking - change to check password in DATABASE (FUTUE ME :) )
        if(reply.header.equals("1")){
            root = FXMLLoader.load(getClass().getResource("/com/example/siechoteli/mainscene/mainScene2.fxml"));
            //"here we get our stage"
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            Warnings.warningAlert();
        }






    }





    /**
     * This function only sets passwordField to be unfocused
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passwordTextField.setFocusTraversable(false);
    }
}