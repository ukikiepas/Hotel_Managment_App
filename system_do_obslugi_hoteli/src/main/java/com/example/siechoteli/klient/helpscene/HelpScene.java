package com.example.siechoteli.klient.helpscene;


/**
 * This class contains getters to variables which are provided in HelScene
 *
 */
public class HelpScene {

    private String hotelName;
    private String yourName;
    private int employeeID;
    private int contactNumber;
    private String issue;
    private int problemID;


    public HelpScene(String hotelName, String yourName, int employeeID, int contactNumber, String issue, int problemID) {
        this.hotelName = hotelName;
        this.yourName = yourName;
        this.employeeID = employeeID;
        this.contactNumber = contactNumber;
        this.issue = issue;
        this.problemID = problemID;
    }
    /***
     *
     * @return value of variable called hotelName
     */
    public String getHotelName() {
        return hotelName;
    }

    /***
     *
     * @return value of variable called yourName
     */
    public String getYourName() {
        return yourName;
    }
    /***
     *
     * @return value of variable called employeeID
     */

    public int getEmployeeID() {
        return employeeID;
    }
    /***
     *
     * @return value of variable called contactNumber
     */
    public int getContactNumber() {
        return contactNumber;
    }
    /***
     *
     * @return value of variable called issue
     */

    public String getIssue() {
        return issue;
    }



}
