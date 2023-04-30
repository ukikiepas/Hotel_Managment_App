package com.example.siechoteli.klient.guests;

import com.example.siechoteli.klient.mainapp.Message;
import com.example.siechoteli.klient.mainapp.Client;
import com.example.siechoteli.utilities.Warnings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


/**
 * Class that handles inserting, deleting, and updating our database
 * and FXML table with clients data.
 * {@link Guests Class which holds template for our table}
 */
public class GuestsController implements Initializable {


    @FXML
    private TableColumn<Guests, String> columName;

    @FXML
    private TableColumn<Guests, String> columnEmail;

    @FXML
    private TableColumn<Guests, Integer> columnID_number;

    @FXML
    private TableColumn<Guests, String> columnHotel;

    @FXML
    private TableColumn<Guests, String> columnRoomNumber;

    @FXML
    private TableColumn<Guests, String> columnSurname;

    @FXML
    private TableColumn<Guests, Integer> columnTelephone;

    @FXML
    private TableColumn<Guests, LocalDate> columnStartDate;

    @FXML
    private TableColumn<Guests, LocalDate> columnFinishDate;

    @FXML
    private TableColumn<Guests, String> columnStandard;

    @FXML
    private TableColumn<Guests, String> columnPrice;

    @FXML
    private TableView<Guests> tableViewGuests;

    @FXML
    private TextField textFieldTelephone;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldID_number;

    @FXML
    private TextField textFieldName;



    @FXML
    private TextField textFieldSurname;



    @FXML
    private ChoiceBox<String> hotelNamesChoiceBox;

    private String hotelsNamesString[] = {"Spectra", "LJH", "Colony"};


    @FXML
    private ChoiceBox<String> RoomNumberChoiceBox;

    @FXML
    private DatePicker textFieldStartDate;

    @FXML
    private DatePicker textFieldFinishDate;

    /*@FXML
    private TextField textFieldStandard;*/

    @FXML
    private ChoiceBox<String> standardChoiceBox;

    private String standardString[] = {"LOW", "HIGH"};


    @FXML
    private ChoiceBox<String> PriceTextField;


    @FXML
    private Button addButton;

    private Parent root;
    private Stage stage;
    private Scene scene;


    /**
     * This function handles back button.
     *
     * @param event In this case ActionEvent reacts on button press [back button]
     * @throws IOException
     */
    public void backToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/siechoteli/guests/chooseScene.fxml"));
        //"here we get our stage"
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Handles guests adding
     *
     * @param event In this case ActionEvent reacts on button press [Add button]
     * @throws IOException
     */
    public void addGuestsButton(ActionEvent event) throws IOException {
        if(PriceTextField.getValue()=="Get price here!")
        {
            Warnings.warningAllPrice();
        }
        else if(checkingInput()==true){
            insertData();
            showGuests();
        }
        else{
            Warnings.warningAlertDataInput();
        }

    }
    /**
     *This function is checking whether some of TexFields are empty if so returns false and block sending request to server.
     */
    public  boolean checkingInput()
    {
        if(EmailisValid(textFieldEmail.getText())==false) {
            Warnings.warningEmailInput();
            return false;
        }
        else if(PhoneValid(textFieldTelephone.getText())==false) {
            Warnings.warningTelephoneNumber();
            return false;
        }
        else if(IdValid(textFieldID_number.getText())==false) {
            Warnings.warningIDInput();
            return false;
        }
        else {
            return true;
        }
    }
    /**
     * It is checking if the string of numbers is correct to be a phone number
     * @param phone it holds phone number put into the TextFieldTelephone
     */
    public Boolean PhoneValid(String phone)
    {
        if(phone.length()==9) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * It is checking if the string of numbers is correct to be a validId(american)
     * @param id it holds id put into the TextFieldID_number
     */
    public Boolean IdValid(String id)
    {
        if(id.length()==9) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * It is checking is the string of numbers is correct to be a phone number
     * @param email it holds email put into the TextFieldEmail
     */
    public  boolean EmailisValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }


    /**
     * Handles guests updating
     *
     * @param event In this case ActionEvent reacts on button press [Update button]
     * @throws IOException
     */
    public void updateGuestsButton(ActionEvent event) throws IOException {

        if(PriceTextField.getValue()=="Get price here!")
        {
            Warnings.warningAllPrice();
        }
        else if(checkingInput()==true){
            updateRecord();
            showGuests();
        }
        else{
            Warnings.warningAlertDataInput();
        }
    }

    /**
     * Handles guests deleting
     *
     * @param event In this case ActionEvent reacts on button press [delete button]
     * @throws IOException
     */
    public void deleteGuestsButton(ActionEvent event) throws IOException {
        deleteRecord();
        showGuests();
    }
    List<String> passList_adjust = new ArrayList<>();
    List<String> RoomsList = new ArrayList<>();
    String price;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectAdjustedHotelsName();
        hotelNamesChoiceBox.getItems().addAll(passList_adjust);
        standardChoiceBox.getItems().addAll(standardString);


        checkGuests();
        showGuests();

    }

    /**
     * this function reacts on clicking on ChoiceBox and executes function.
     * @param event
     */
    public void func(MouseEvent event) {
        selectAdjustedPrice();
    }
    /**
     * this function reacts on clicking on ChoiceBox and executes function.
     * @param event
     */
    public void RoomsFilter(MouseEvent event){
        selectAdjustedRoomNumber();
    }

    /**
     * This function set our ChoiceBoxes to default values.
     * @param event
     */
    public void PriceFilter(MouseEvent event)
    {
        PriceTextField.setValue("Get price here!");
        RoomNumberChoiceBox.setValue("RoomNumber");
    }



    /**
     * This function sends a message to a server and server returns data of all guests in String List.     *
     * Later we create new guest object and save in a loop all this data into guestes object
     *
     * @return Function returns observableList of Guests
     */
    public ObservableList<Guests> getGuestsList() {
        ObservableList<Guests> guestsList = FXCollections.observableArrayList();
        Message msg =new Message("_getGuestList", null);
        Client client  =new Client(25565, "127.0.0.1");
        Message reply = client.executeRequest(msg);

        int i = Integer.parseInt(reply.header);

        Guests guests;


        for(int x =-1; x<i;){

            guests = new Guests(reply.arguments.get(++x), reply.arguments.get(++x), reply.arguments.get(++x),
                    Integer.valueOf(reply.arguments.get(++x)), Integer.valueOf(reply.arguments.get(++x)), reply.arguments.get(++x),
                    String.valueOf(reply.arguments.get(++x)), LocalDate.parse(reply.arguments.get(++x)), LocalDate.parse(reply.arguments.get(++x)),
                    reply.arguments.get(++x), String.valueOf(reply.arguments.get(++x)));
            guestsList.add(guests);
        }

        return guestsList;
    }

    /**
     This function get return from getGuestList method and later saves it into ObservableList list.
     Later this updates our TableViewGusests with our list
     */
    public void showGuests() {
        ObservableList<Guests> list = getGuestsList();

        columName.setCellValueFactory(new PropertyValueFactory<Guests, String>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<Guests, String>("surname"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<Guests, String>("email"));
        columnTelephone.setCellValueFactory(new PropertyValueFactory<Guests, Integer>("number"));
        columnID_number.setCellValueFactory(new PropertyValueFactory<Guests, Integer>("id_number"));
        columnHotel.setCellValueFactory(new PropertyValueFactory<Guests, String>("hotel"));
        columnRoomNumber.setCellValueFactory(new PropertyValueFactory<Guests, String>("room_number"));
        columnStartDate.setCellValueFactory(new PropertyValueFactory<Guests, LocalDate>("StartDate"));
        columnFinishDate.setCellValueFactory(new PropertyValueFactory<Guests, LocalDate>("FinishDate"));
        columnStandard.setCellValueFactory(new PropertyValueFactory<Guests, String>("Standard"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<Guests, String>("Price"));


        tableViewGuests.setItems(list);
    }

    /**
     * This function is used for data validation in insert and update statements.
     * @param reservBeginDat
     * @param reservFinishDat
     * @return
     */
    public boolean dateValid (String reservBeginDat, String reservFinishDat){

        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        // I am checking if begin reservation and finish reservation date is bigger than current  and if finish>begin
        if(reservBeginDat.compareTo(currentDate) >= 0 && (reservFinishDat.compareTo(currentDate)) >= 0 && reservFinishDat.compareTo(reservBeginDat) >=0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * This function hadnles inserting data into our DataBase.
     * We have here 3 different queries to insert into 3 different database tables.
     * We send this queries to server via passList which is list of Strings
     * After all this operation we clean all textfields for the app clearance
     */

    private void insertData() {

        do{
            if(!dateValid(textFieldStartDate.getValue().toString() ,textFieldFinishDate.getValue().toString())){
                Warnings.warningProperDate();
                break;
            }

            String query = "INSERT INTO GUESTS VALUES ('" + textFieldName.getText() + "','" + textFieldSurname.getText() + "','" + textFieldEmail.getText() + "'," + textFieldTelephone.getText()
                    + "," + textFieldID_number.getText() + ",'" + hotelNamesChoiceBox.getValue() + "'," + RoomNumberChoiceBox.getValue() + ",'" + textFieldStartDate.getValue() + "', '" + textFieldFinishDate.getValue() + "', '"
                    +  standardChoiceBox.getValue() + "'," + PriceTextField.getValue() + ")";
            System.out.println(query);


            String query2 = "INSERT INTO ROOMS (roomID, RoomNumber , price_PN, standard,  available, startdate, finishdate, hotelName ) " +
                    "VALUES (" + null + ", " + RoomNumberChoiceBox.getValue() + ", " + PriceTextField.getValue()  + ", '" + standardChoiceBox.getValue() + "','" + "TAKEN" + "', '" + textFieldStartDate.getValue()  + "','" + textFieldFinishDate.getValue() + "', '" + hotelNamesChoiceBox.getValue() +
                    "')";


            String query3 = "UPDATE roomsList SET Available = 'TAKEN', startDate =  " + "'" +textFieldStartDate.getValue()  +  "',"  + "finishDate = '" +textFieldFinishDate.getValue() +
                    "' WHERE roomID = " + RoomNumberChoiceBox.getValue() +";";




            List<String> passList = new ArrayList<>();
            passList.add(query);
            passList.add(query2);
            passList.add(query3);

            Message msg = new Message("_insertGuests", passList);
            Client client = new Client(25565, "127.0.0.1");
            Message reply = client.executeRequest(msg);

            if (reply.header.equals("1")) {
                Warnings.informationAlertAdd();
            } else {
                Warnings.warningAlertDataInput();
            }


            clearTextFields();
        }while (false);


    }






    /**
     *This function hadnles updating data in our DataBase.
     * We have om 3 different queries to insert into 3 different database tables.
     * We send this queries to server via passList which is list of Strings
     * After all this operation we clean all textfields for the app clearance
     */
    private void updateRecord() {

        do{
            if(!dateValid(textFieldStartDate.getValue().toString() ,textFieldFinishDate.getValue().toString())){
                Warnings.warningProperDate();
                break;
            }

            String query1 = "UPDATE guests SET name = '" + textFieldName.getText() + "',  surname = '" + textFieldSurname.getText() +
                    "',  email = '" + textFieldEmail.getText() + "',  telephone = " + textFieldTelephone.getText() + ",  id_number = " + textFieldID_number.getText()
                    + ",  hotel = '" + hotelNamesChoiceBox.getValue() + "',  roomnumber = " + RoomNumberChoiceBox.getValue() + ", startdate =  '" + textFieldStartDate.getValue()
                    + "', finishdate = '" + textFieldStartDate.getValue() + "', standard = '" +standardChoiceBox.getValue() +
                    "', price = " + PriceTextField.getValue()  +    " WHERE id_number = " + textFieldID_number.getText() + ";";

            String query2 = "UPDATE roomslist SET available = 'TAKEN' , "  +  "startDate= '" +textFieldStartDate.getValue()  + "', finishDate='"+ textFieldStartDate.getValue() +"'  WHERE roomID = " + RoomNumberChoiceBox.getValue() +";";

            String query3 = "UPDATE rooms SET  roomnumber ="  + RoomNumberChoiceBox.getValue() + ", price_PN = " + PriceTextField.getValue()  +  ", bedQuantity = " +
                    1 + ", standard = '" + standardChoiceBox.getValue() + "', available = 'TAKEN'"   + ",    startDate = '" + textFieldStartDate.getValue()
                    + "', finishDate = '" + textFieldStartDate.getValue() + "', hotelName = '" +hotelNamesChoiceBox.getValue() + "' WHERE roomnumber = " +RoomNumberChoiceBox.getValue() ;

            List<String> passList = new ArrayList<>();

            passList.add(query1);
            passList.add(query2);
            passList.add(query3);


            Message msg = new Message("_updateGuests", passList);
            Client client = new Client(25565, "127.0.0.1");
            Message reply = client.executeRequest(msg);
            passList.clear();

            if (reply.header.equals("1")) {
                Warnings.informationAlertUpdate();
            } else {
                Warnings.warningAlertDataInput();
            }


            clearTextFields();

        }while (false);


    }

    /**
     *This function hadnles deleting data in our DataBase.
     * We send this query to server via passList which is list of Strings
     * After all this operation we clean all textfields for the app clearance
     */
    private void deleteRecord() {
        String query = "DELETE FROM guests WHERE id_number = " + textFieldID_number.getText() + "";
        String query2 = "UPDATE roomslist SET Available= 'FREE' , startDate= '3000-12-12', finishDate='3000-12-12'  WHERE roomID = " + RoomNumberChoiceBox.getValue() +";";

        List<String> passList = new ArrayList<>();
        passList.add(query);
        passList.add(query2);
        Message msg = new Message("_deleteGuests", passList);
        Client client = new Client(25565, "127.0.0.1");
        Message reply = client.executeRequest(msg);

        if (reply.header.equals("1")) {
            Warnings.informationAlertDelete();
        } else {

        }
        /*
        executeQuery(query);
        if(alertDecision == 0){
            informationAlertDelete();
        }else{
            alertDecision --;
        }*/
        clearTextFields();
    }



    /**
     * This function doees one thing - basically checks if user finish date is greater than our current date and
     * if so it deletes record from database and makes our roomslist room again avaliable
     */
    private void checkGuests() {
        String query = "DELETE  FROM `guests` WHERE finishDate < curdate();";
        String query2 = "UPDATE roomslist SET Available= 'FREE' , startDate= '3000-12-12', finishDate='3000-12-12' WHERE finishDate < curdate();";

        List<String> passList = new ArrayList<>();
        passList.add(query);
        passList.add(query2);
        Message msg = new Message("_cleanOlderRecords", passList);
        Client client = new Client(25565, "127.0.0.1");
        Message reply = client.executeRequest(msg);

    }




    /**
     *      *   it is sending query to server  and get proper data which is later put in our HotelsChoiceBox
     *
     */

    public void selectAdjustedHotelsName()
    {

        Message msg = new Message("_selectAdjustedHotelsName", null);
        Client client = new Client(25565, "127.0.0.1");
        Message reply = client.executeRequest(msg);
        int x = 0;
        while (x < reply.arguments.size()){
            passList_adjust.add(reply.arguments.get(x));
            x++;
        }


    }

    /**
     *   it is sending query to server  and get proper data which is later put in our PriceTextField
     */
    public void selectAdjustedPrice()
    {

        String query = "Select distinct price_PN from roomslist where hotelName='"+hotelNamesChoiceBox.getValue()+"' AND Standard='"+standardChoiceBox.getValue()+"';";
        List<String> listArg = new ArrayList<>();
        listArg.add(query);
        Message msg = new Message("_selectAdjustedPrice", listArg);
        Client client = new Client(25565, "127.0.0.1");
        Message reply = client.executeRequest(msg);
        PriceTextField.setValue(reply.arguments.get(0));

        //poprawilem Ci to
        /*
        Connection conn = getConnection();

        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                price = (rs.getString("price_PN"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        PriceTextField.setValue(price);*/
    }

    /**
     * it is sending query to server via RoomsList which is list of Strings to database to select apropriate RoomNumber and pass it to our list
     *
     */
    public void selectAdjustedRoomNumber()
    {
        RoomsList.clear();
        RoomNumberChoiceBox.getItems().clear();

        String query = "Select distinct RoomNumber from roomslist where  hotelName='"+hotelNamesChoiceBox.getValue()+"' AND Standard='"+standardChoiceBox.getValue()+"' AND Available='FREE';";
        List<String> listArg = new ArrayList<>();
        listArg.add(query);
        Message msg = new Message("_selectAdjustedRoomNumber", listArg);
        Client client = new Client(25565, "127.0.0.1");
        Message reply = client.executeRequest(msg);
        RoomNumberChoiceBox.getItems().addAll(reply.arguments);
        //poprawilem Ci to
        /*
        RoomsList.clear();
        RoomNumberChoiceBox.getItems().clear();
        String query = "Select distinct RoomNumber from roomslist where  hotelName='"+hotelNamesChoiceBox.getValue()+"' AND Standard='"+standardChoiceBox.getValue()+"' AND Available='FREE';";

        Connection conn = getConnection();

        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                RoomsList.add(rs.getString("RoomNumber"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        RoomNumberChoiceBox.getItems().addAll(RoomsList);*/
    }



    /**
     * This function clean all our TextFields
     * It's used in for example add function to clear input text fields after addition
     */
    private void clearTextFields() {
        textFieldName.clear();
        textFieldSurname.clear();
        textFieldEmail.clear();
        textFieldTelephone.clear();
        textFieldID_number.clear();
        hotelNamesChoiceBox.setValue("Hotel");
        RoomNumberChoiceBox.setValue("Room number");
        textFieldStartDate.setValue(null);
        textFieldFinishDate.setValue(null);
        standardChoiceBox.setValue("Standard");
        PriceTextField.setValue("Get price here!");
    }

    /**
     * This function loads data into our input TextFields after clicking on a specific row.
     * It was created for update function
     * @param mouseEvent
     */
    public void handleMouseAction(MouseEvent mouseEvent) {
        Guests guests = tableViewGuests.getSelectionModel().getSelectedItem();
        textFieldName.setText(guests.getName());
        textFieldSurname.setText(guests.getSurname());
        textFieldEmail.setText(guests.getEmail());
        textFieldTelephone.setText(String.valueOf(guests.getNumber()));
        textFieldID_number.setText(String.valueOf(guests.getId_number()));
        hotelNamesChoiceBox.setValue(guests.getHotel());
        //textFieldHotel.setText(guests.getHotel());
        RoomNumberChoiceBox.setValue(String.valueOf(guests.getRoom_number()));
        textFieldStartDate.setValue(guests.getStartDate());
        textFieldFinishDate.setValue(guests.getFinishDate());
        standardChoiceBox.setValue(String.valueOf(guests.getStandard()));
        //textFieldStandard.setText(String.valueOf(guests.getStandard()));
        PriceTextField.setValue("Get price here!");
    }
}

