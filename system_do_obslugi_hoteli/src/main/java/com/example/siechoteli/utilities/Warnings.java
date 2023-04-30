package com.example.siechoteli.utilities;

import javafx.scene.control.Alert;

/**
 * Here this is class form utilities package.
 * As package name says, we have here few utilities methods which are used all along
 * whole project. They are static, because i wanted to use them without object initialization
 */

public class Warnings {

    public static void warningAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText("Provide proper login inputs please!");
        alert.setHeaderText("Something went wrong !");
        alert.showAndWait();
    }

    public static void warningAlertBusy(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText("Our consultants are currently busy, please try again later. ");
        alert.setHeaderText("Please try again later !");
        alert.showAndWait();
    }

    public static void informationAlertAdd() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information dialog");
        alert.setContentText("Guest was added successfully");
        alert.setHeaderText("Success");
        alert.showAndWait();
    }

    public static void informationAlertUpdate() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information dialog");
        alert.setContentText("Guest was updated successfully");
        alert.setHeaderText("Success");
        alert.showAndWait();
    }

    public static void informationAlertDelete() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information dialog");
        alert.setContentText("Guest was deleted successfully");
        alert.setHeaderText("Success");
        alert.showAndWait();
    }

    public static void warningAlertDataInput() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText("Provide proper data inputs please!");
        alert.setHeaderText("Something went wrong !");
        alert.showAndWait();
    }

    public static void warningEmailInput() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText("Please use proper email address!");
        alert.setHeaderText("Check your email !!");
        alert.showAndWait();
    }

    public static void warningIDInput() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText("Please use proper ID number!");
        alert.setHeaderText("Check your ID NUMBER!!");
        alert.showAndWait();
    }

    public static void warningTelephoneNumber() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText("Please use proper TELEPHONE number!");
        alert.setHeaderText("Check your TELEPHONE NUMBER!!");
        alert.showAndWait();
    }

    public static void warningAllOption() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText("Please choose all options ");
        alert.setHeaderText("Check if you have chosen all options!!");
        alert.showAndWait();
    }
    public static void warningAllPrice() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText("Please select price");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    public static void warningProperDate() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText("Please select future date");
        alert.setHeaderText("");
        alert.showAndWait();
    }







}
