package com.example.siechoteli.klient.guests;


import com.example.siechoteli.klient.mainapp.Client;
import com.example.siechoteli.klient.mainapp.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class handles hotel rooms table.
 * Thanks to that class we are able to see hotel room table.
 */
public class RoomsController implements Initializable {

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private TableColumn<Rooms, Integer> columnRoomID;

    @FXML
    private TableColumn<Rooms, Integer> columnRoomNumber ;

    @FXML
    private TableColumn<Rooms, Integer> columnPrice_PN;

    @FXML
    private TableColumn<Rooms, Integer> columnBedQuantity;

    @FXML
    private TableColumn<Rooms, String> columnStandard;

    @FXML
    private TableColumn<Rooms, String> columnAvaliable;

    @FXML
    private TableColumn<Rooms, String> columnStartDate;

    @FXML
    private TableColumn<Rooms, String> columnFinishDate;

    @FXML
    private TableColumn<Rooms, String> columnHotelName;

    @FXML
    private TableView<Rooms> tableViewRooms;


    /***
     * After pressing one of switching scene button this function forwards us to MainScene2 tab
     * @param event event runs when you click appropriate button
     * @throws IOException
     */



    /**
     * This function uses server to get output from database.
     * We ask for data from roomslist table.
     * Then we return roomsList which holds all data.
     */
    public ObservableList<Rooms> getRoomsList(String option) {
        ObservableList<Rooms> roomsList = FXCollections.observableArrayList();
        List<String> queries = new ArrayList<>();
        
        String query = null;
        /*Spectra  -> whole spectra
         *FSpectra -> only free rooms
          LJH i Colony to samo
         */
        if(option.equals("Spectra")){
             query = "Select * FROM roomsList WHERE hotelName = 'Spectra';";
        } else if (option.equals("FSpectra")) {
             query = "Select * FROM roomsList WHERE hotelName = 'Spectra' AND Available = 'FREE';";
        }  else if(option.equals("LJH")){
             query = "Select * FROM roomsList WHERE hotelName = 'LJH';";
        } else if (option.equals("FLJH")) {
             query = "Select * FROM roomsList WHERE hotelName = 'LJH' AND Available = 'FREE';";
        } else if(option.equals("Colony")){
             query = "Select * FROM roomsList WHERE hotelName = 'Colony';";
        } else if (option.equals("FColony")) {
             query = "Select * FROM roomsList WHERE hotelName = 'Colony' AND Available = 'FREE';";
        } else if(option.equals("All hotels")){
            query = "Select * FROM roomsList ;";
        } else if (option.equals("FAll hotels")) {
            query = "Select * FROM roomsList WHERE Available = 'FREE';";
        }

        System.out.println("TO MOJE QUERY" + query);
        queries.add(query);
        Message msg =new Message("_getRoomsList", queries);
        Client client  =new Client(25565, "127.0.0.1");
        Message reply = client.executeRequest(msg);

        int i = Integer.parseInt(reply.header);

        Rooms rooms;


        for(int x =-1; x<i;){

            rooms = new Rooms(Integer.valueOf(reply.arguments.get(++x)), Integer.valueOf(reply.arguments.get(++x)),
                    Integer.valueOf(reply.arguments.get(++x)), Integer.valueOf(reply.arguments.get(++x)),
                    reply.arguments.get(++x), reply.arguments.get(++x),
                    reply.arguments.get(++x), reply.arguments.get(++x), reply.arguments.get(++x));
            roomsList.add(rooms);
        }

        return roomsList;
    }

    /**
     *This function is responsible for displaying data and inserting it into
     * column.
     */
    public void showRooms(String option) {
        ObservableList<Rooms> list = getRoomsList(option);

        columnRoomID.setCellValueFactory(new PropertyValueFactory<Rooms, Integer>("roomID"));
        columnRoomNumber.setCellValueFactory(new PropertyValueFactory<Rooms, Integer>("roomNumber"));
        columnPrice_PN.setCellValueFactory(new PropertyValueFactory<Rooms, Integer>("price_PN"));
        columnBedQuantity.setCellValueFactory(new PropertyValueFactory<Rooms, Integer>("bedQuantity"));
        columnStandard.setCellValueFactory(new PropertyValueFactory<Rooms, String>("standard"));
        columnAvaliable.setCellValueFactory(new PropertyValueFactory<Rooms, String>("avaliable"));
        columnStartDate.setCellValueFactory(new PropertyValueFactory<Rooms, String>("StartDate"));
        columnFinishDate.setCellValueFactory(new PropertyValueFactory<Rooms, String>("FinishDate"));
        columnHotelName.setCellValueFactory(new PropertyValueFactory<Rooms, String>("hotelName"));

        tableViewRooms.setItems(list);

    }
    /*
    private void checkGuests() {
        String query = "DELETE  FROM `guests` WHERE finishDate < curdate();";

        List<String> passList = new ArrayList<>();
        passList.add(query);
        Message msg = new Message("_cleanOlderRecords", passList);
        Client client = new Client(25565, "127.0.0.1");
        Message reply = client.executeRequest(msg);

    }*/

    /**
     * This function is used for setting green color when Avaliable row is = 1
     * and red when Avaliable row is = 0;
     */
    public void setColor(){

        tableViewRooms.setRowFactory(tv -> new TableRow<Rooms>() {
            @Override
            public void updateItem(Rooms item, boolean empty) {
                super.updateItem(item, empty) ;
                if (item == null) {
                    setStyle("");
                } else if (item.getAvaliable().equals("FREE")) {
                    setStyle("-fx-background-color:  #33cc33;");
                } else {
                    setStyle("-fx-background-color:  #ff3300;");
                }
            }
        });
    }






    /**
     * Here we have two functions which will be executed right after switching to Rooms Scene.
     * We showRooms and set proper color: red or green
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
