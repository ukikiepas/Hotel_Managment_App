package com.example.siechoteli.server;

import com.example.siechoteli.klient.guests.Guests;
import com.example.siechoteli.klient.mainapp.Message;
import com.example.siechoteli.klient.guests.Rooms;

import java.io.*;
import java.net.SocketAddress;
import java.sql.*;
import java.util.*;


/**
 * This is a ServerThread class.
 * Everytime client send a request to a server a new instance of ServerThread is created
 */
public class ServerThread implements Runnable {

    /** This is a simple mechanizm which we use in every query. If everything went as planned
     * we leave/set our value 1 / to 1.
     * If we had some exception we set it to 0 and therefore our client know that something
     * didn't go as planned
    */
    public int alertDec = 1;

    private String inputHeader;

    private List<String> inputArguments;

    private ObjectOutputStream objectOutputStream;

    SocketAddress socketAddress;

    /**
     * The only serverThread constructor
     * It set values of our: inputHeader, inputArguments, objectOutputStream, socketAddress
     * @param inputHeader
     * @param inputArguments
     * @param objectOutputStream
     * @param socketAddress
     */
    ServerThread(String inputHeader, List inputArguments, ObjectOutputStream objectOutputStream,
                 SocketAddress socketAddress) {
        this.inputHeader = inputHeader;
        this.inputArguments = inputArguments;
        this.objectOutputStream = objectOutputStream;
        this.socketAddress = socketAddress;
    }

    /**
     * Here our run() method  sends a respone ticket to our client
     * with information about his request.
     * We get that data from getResponse function
     * Get response is a simple function which uses switchcase decision block
     * to execute proper function
     * After that we flush buffor.
     */
    @Override
    public void run() {

        try {
            objectOutputStream.writeObject(getResponse());
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }


    }

    /**
     * This function Connects us with our database
     * @return Connection object
     */
    public Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/relaxmsdb", "root", "");
            //System.out.println("Udalo sie polaczyc");
            return conn;
        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    /**
     * This is our utility functions which has one job - execute our query in database
     * @param query
     */
    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
            alertDec = 1;
        }catch (Exception e){
            e.printStackTrace();
            alertDec = 0;
        }

    }


    // From now only we have only handlers for user requests


    /**
     * This functions check login credentials.
     * If it is okay we send 1 in header
     * If credentials are not correct we send 0 in header and we can't log in
     * @return
     */
    private Message _login(){


        Connection conn = getConnection();
        String query = "SELECT * FROM employees";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                if(rs.getString("login").equals(inputArguments.get(0)) && rs.getString("password").equals(inputArguments.get(1)) ){
                    return new Message("1", null);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Message("0", null);



    }



    /**
     * This function inserts  guests into Data Base
     * If everything went good we send 1 to client
     * If not we send 0 to client
     * @return
     */
    private Message _insertGuests(){

            executeQuery(inputArguments.get(0));
            executeQuery(inputArguments.get(1));
            executeQuery(inputArguments.get(2));

        if(alertDec == 1){
            return new Message("1", null);
        }else{
            return new Message("0", null);
        }
    }

    /**
     * This function updates  guests in Data Base
     * If everything went good we send 1 to client
     * If not we send 0 to client
     * @return
     */
    private  Message _updateGuests(){
        executeQuery(inputArguments.get(0));
        executeQuery(inputArguments.get(1));
        executeQuery(inputArguments.get(2));

        if(alertDec == 1){
            return new Message("1", null);
        }else{
            return new Message("0", null);
        }
    }

    /**
     * This function delete  guests from Data Base
     * If everything went good we send 1 to client
     * If not we send 0 to client
     * @return
     */
    private Message _deleteGuests(){
        executeQuery(inputArguments.get(0));
        executeQuery(inputArguments.get(1));

        if(alertDec == 1){
            return new Message("1", null);
        }else{
            return new Message("0", null);
        }
    }

    /**
     * This function inserts help ticket into Data Base
     * If everything went good we send 1 to client
     * If not we send 0 to client
     * @return
     */
    private Message _insertTicket(){
        executeQuery(inputArguments.get(0));

        if(alertDec == 1){
            return new Message("1", null);
        }else{
            return new Message("0", null);
        }
    }

    /**
     * This function get data from DataBase and send it back to our getGuestList in
     * {@link com.example.siechoteli.klient.guests.GuestsController}
     * @return this functions returns Message Object with header and List<String> which contains all our guests data.
     */
    private Message _getGuestList(){
        int i = -1;
        List <String> guestList = new ArrayList<>();

        Connection conn = getConnection();
        String query = "SELECT * FROM guests";

        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Guests guests = null;

            while (rs.next()) {

                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                String email = rs.getString("Email");
                Integer telephone = rs.getInt("Telephone");
                Integer id_number = rs.getInt("ID_Number");
                String hotel = rs.getString("Hotel");
                Integer roomnumber = rs.getInt("RoomNumber");
                String startDate = rs.getString("StartDate");
                String finishDate = rs.getString("FinishDate");
                String standard = rs.getString("standard");
                Integer price = rs.getInt("price");
                i = i+11;

                guestList.add(name);
                guestList.add(surname);
                guestList.add(email);
                guestList.add(String.valueOf(telephone));
                guestList.add(String.valueOf(id_number));
                guestList.add(hotel);
                guestList.add(String.valueOf(roomnumber));
                guestList.add(String.valueOf(startDate));
                guestList.add(String.valueOf(finishDate));
                guestList.add(String.valueOf(standard));
                guestList.add(String.valueOf(price));

            }



        } catch (Exception e) {
            e.printStackTrace();
        }
            String headerListConuter = String.valueOf(i);
            return new Message(headerListConuter, guestList);
    }

    /**
     * This function get data from DataBase and send it back to our getRoomsList in
     * {@link com.example.siechoteli.klient.guests.RoomsController}
     * @return this functions returns Message Object with header and List<String> which contains all our rooms data
     */
    private Message _getRoomsList(){
        int i = -1;
        List <String> roomList = new ArrayList<>();


        Connection conn = getConnection();
        String query = inputArguments.get(0);
        System.out.println("to moje query na serwie: " + query);

        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Rooms rooms = null;

            while (rs.next()) {

                Integer roomID = rs.getInt("roomID");
                Integer roomNumber = rs.getInt("roomNumber");
                Integer price_PN = rs.getInt("price_PN");
                Integer bedQuantity = rs.getInt("bedQuantity");
                String standard = rs.getString("Standard");
                String available = rs.getString("Available");
                String startDate = rs.getString("StartDate");
                String finishDate = rs.getString("FinishDate");
                String hotelName = rs.getString("hotelName");
                i = i+9;

                roomList.add(String.valueOf(roomID));
                roomList.add(String.valueOf(roomNumber));
                roomList.add(String.valueOf(price_PN));
                roomList.add(String.valueOf(bedQuantity));
                roomList.add(standard);
                roomList.add(String.valueOf(available));
                roomList.add(startDate);
                roomList.add(finishDate);
                roomList.add(hotelName);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        String headerListConuter = String.valueOf(i);
        return new Message(headerListConuter, roomList);
    }


    /**
     * This function also has one job - delete records with past date.
     * @return It returns object of Message with header "1" IF GOOD "0" IF STH WENT WRONG
     */
    private Message _cleanOlderRecords(){
        try{
            executeQuery(inputArguments.get(0));
            executeQuery(inputArguments.get(1));
            return new Message("1", null);
        }catch (Exception e){
            e.printStackTrace();
            return new Message("0", null);
        }
    }

    /**
     * Here we execute our query which adds record to
     * empLoggedTime table.
     * @return It returns and Message object with header
     * 1 IF GOOD 0 IF STH WENT WRONG
     */

    private Message _sendLoggedTime(){

        try{
            executeQuery(inputArguments.get(0));
            return new Message("1", null);
        }catch (Exception e){
            return new Message("0", null);
        }
    }

    private Message _selectAdjustedHotelsName(){
        String query = "Select distinct hotelName from roomslist;";
        List<String> argList = new ArrayList<>();

        Connection conn = getConnection();

        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                argList.add(rs.getString("hotelName"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return new Message("1", argList);
    }

    private Message _selectAdjustedPrice(){
        String query = inputArguments.get(0);
        List<String> argList = new ArrayList<>();

        Connection conn = getConnection();

        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                argList.add(rs.getString("price_PN"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return new Message("1", argList);
    }

    private Message _selectAdjustedRoomNumber(){
        String query = inputArguments.get(0);
        List<String> argList = new ArrayList<>();

        Connection conn = getConnection();

        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                argList.add(rs.getString("RoomNumber"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return new Message("1", argList);
    }



    /**
     * Here using a simple switch case syntax we check our inputHeader title
     * And then execute a proper command from cases.
     * After execution we save the resposne to response variable and then
     * we return this respone. Response is instance of Message.
     * @return
     */
    private Message getResponse() {
        Message response;
        // obsluga serwera
        switch (inputHeader) {
            case "_login":
                response = _login();
                break;
            case "_insertGuests":
                response = _insertGuests();
                break;
            case "_getGuestList":
                response = _getGuestList();
                break;
            case "_getRoomsList":
                response = _getRoomsList();
                break;
            case "_updateGuests":
                response = _updateGuests();
                break;
            case "_deleteGuests":
                response = _deleteGuests();
                break;
            case "_insertTicket":
                response = _insertTicket();
                break;
            case "_cleanOlderRecords":
                response = _cleanOlderRecords();
                break;
            case "_sendLoggedTime":
                response = _sendLoggedTime();
                break;
            case "_selectAdjustedHotelsName":
                response = _selectAdjustedHotelsName();
                break;
            case "_selectAdjustedPrice":
                response = _selectAdjustedPrice();
                break;
            case "_selectAdjustedRoomNumber":
                response = _selectAdjustedRoomNumber();
                break;





            default:
                response = new Message("NOP", null);
        }
        return response;
    }

}
