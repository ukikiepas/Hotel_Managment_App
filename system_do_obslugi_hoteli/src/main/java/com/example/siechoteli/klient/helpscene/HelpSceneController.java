package com.example.siechoteli.klient.helpscene;

import com.example.siechoteli.klient.mainapp.Client;
import com.example.siechoteli.klient.mainapp.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


/**
 * Controler for our sending ticker scene.
 * Here we can send a proper data to database and also switch scenes.
 */
public class HelpSceneController implements Initializable {



    @FXML
    private ChoiceBox<String> hotelNamesChoiceBox;

    private String hotelsNamesString[] = {"Spectra", "LJH", "Colony"};

    @FXML
    private TextField yourNameTextField;

    @FXML
    private TextField employeeIDTextField;

    @FXML
    private TextField contactNumberTextField;

    @FXML
    private TextField issueTextField;




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

    public void sendIssue(ActionEvent event) throws IOException{
            insertData();
    }

    /**
     * it is sending query to server via query which contains our issue with a certain hotel.
     */
    private void insertData(){

        String query = "INSERT INTO ISSUESANDPROBLEMS VALUES('" + hotelNamesChoiceBox.getValue() + "', '" + yourNameTextField.getText() + "', " + employeeIDTextField.getText()
                        + ", " +contactNumberTextField.getText() + ", '" +issueTextField.getText() + "', NULL)";


        List<String> list = new ArrayList<>();
        list.add(query);
        Message msg =new Message("_insertTicket", list);
        Client client  =new Client(25565, "127.0.0.1");
        Message reply = client.executeRequest(msg);

        if(reply.header.equals("1")){
            informationAlert();
            emptyTextFields();
        }else if(reply.header.equals("0")){
            warningAlert();
            emptyTextFields();
        }
        //executeQuery(query);
    }


    /**
     * it clears TextFields
     */
    private void emptyTextFields(){
        hotelNamesChoiceBox.setValue("Choose hotel");
        yourNameTextField.clear();
        employeeIDTextField.clear();
        contactNumberTextField.clear();
        issueTextField.clear();
    }
    /**
     * it appears when we successfully sended our report to database
     */
    private void informationAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information dialog");
        alert.setContentText("You have successfully sent your report.");
        alert.setHeaderText("Success");
        alert.showAndWait();
    }
    /**
     * it appears when we porvided inapropriate data
     */
    private void warningAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText("Provide proper data inputs please!");
        alert.setHeaderText("Something went wrong !");
        alert.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        hotelNamesChoiceBox.getItems().addAll(hotelsNamesString);

    }
}
