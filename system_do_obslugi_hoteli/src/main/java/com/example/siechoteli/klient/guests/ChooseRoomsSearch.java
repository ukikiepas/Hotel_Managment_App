package com.example.siechoteli.klient.guests;

import com.example.siechoteli.utilities.Warnings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

/**
 * This functions is ussed for getting a proper data set in {@link com.example.siechoteli.klient.guests.RoomsController}
 * What we do here is just using simple choiceboxes to get a proper query which is later used in RoomsController.
 */
public class ChooseRoomsSearch implements Initializable {

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private ChoiceBox<String> hotelNameChoiceBox;

    private String hotelsNamesString[] = {"Spectra", "LJH", "Colony", "All hotels" };

    @FXML
    private ChoiceBox<String>  availabilityChoiceBox;

    private String availabilityString[] = {"ALL", "ONLY FREE"};

    @FXML
    private Button searchButton;

    /**
     * We can filter with which status we wanna see hotel
     * here are two main options available or each hotel
     *
     *
     *
     */
    public void search(ActionEvent event) throws IOException {


        String choice = "ERROR";

        do{
            try{
                String hotel = hotelNameChoiceBox.getValue();
                String availability = availabilityChoiceBox.getValue();
                //security measures
                if(hotel.equals("Hotel") || availability.equals("Availability") ){
                    Warnings.warningAllOption();
                    break;
                }
                System.out.println(hotel);
                System.out.println(availability);
                if(hotel.equals("Spectra") && availability.equals("ALL")){
                    choice = "Spectra";
                }else if(hotel.equals("Spectra") && availability.equals("ONLY FREE")){
                    choice = "FSpectra";
                }else if(hotel.equals("LJH") && availability.equals("ALL")){
                    choice = "LJH";
                }else if(hotel.equals("LJH") && availability.equals("ONLY FREE")){
                    choice = "FLJH";
                }else if(hotel.equals("Colony") && availability.equals("ALL")){
                    choice = "Colony";
                }else if(hotel.equals("FColony") && availability.equals("ONLY FREE")){
                    choice = "FColony";
                }else if(hotel.equals("All hotels") && availability.equals("ALL")){
                    choice = "All hotels";
                }else if(hotel.equals("All hotels") && availability.equals("ONLY FREE")){
                    choice = "FAll hotels";
                }


            }catch (Exception e){
                Warnings.warningAlertDataInput();
            }


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/siechoteli/guests/searchRooms.fxml"));
            root = loader.load();

            RoomsController roomsController = loader.getController();
            roomsController.showRooms(choice);
            roomsController.setColor();

            //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }while (false);



    }

    /**
     * This function runs everytime when we intialize our class (create a new scene)
     * Here we basically jsut add items to our chociceBoxes.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hotelNameChoiceBox.getItems().addAll(hotelsNamesString);
        availabilityChoiceBox.getItems().addAll(availabilityString);
    }
}
