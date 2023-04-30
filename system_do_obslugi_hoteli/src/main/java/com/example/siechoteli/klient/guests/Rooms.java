package com.example.siechoteli.klient.guests;


/**
 * This class is analogical to {@link com.example.siechoteli.klient.guests.Guests}
 * @author Lukasz Kiepas
 */
public class Rooms {

    private Integer roomID;
    private Integer roomNumber;
    private Integer price_PN;
    private Integer bedQuantity;
    private String Standard;
    private String Avaliable;
    private String startDate;
    private String finishDate;
    private String hotelName;

    public Rooms(Integer roomID, Integer roomNumber, Integer price_PN, Integer bedQuantity, String standard, String avaliable, String startDate, String finishDate, String hotelName) {
        this.roomID = roomID;
        this.roomNumber = roomNumber;
        this.price_PN = price_PN;
        this.bedQuantity = bedQuantity;
        this.Standard = standard;
        this.Avaliable = avaliable;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.hotelName = hotelName;
    }

    /***
     *
     * @return value of variable called roomID
     */
    public Integer getRoomID() {
        return roomID;
    }
    /***
     *
     * @return value of variable called roomNumber
     */

    public Integer getRoomNumber() {
        return roomNumber;
    }

    /***
     *
     * @return value of variable called price_PN
     */
    public Integer getPrice_PN() {
        return price_PN;
    }
    /***
     *
     * @return value of variable called bedQuantity
     */

    public Integer getBedQuantity() {
        return bedQuantity;
    }

    public String getStandard() {
        return Standard;
    }
    /***
     *
     * @return value of variable called Avaliable
     */
    public String getAvaliable() {
        return Avaliable;
    }

    /***
     *
     * @return value of variable called startDate
     */

    public String getStartDate() {
        return startDate;
    }

    /***
     *
     * @return value of variable called finishDate
     */

    public String getFinishDate() {
        return finishDate;
    }
    /***
     *
     * @return value of variable called hotelName
     */

    public String getHotelName() {
        return hotelName;
    }


}
