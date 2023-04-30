package com.example.siechoteli.klient.guests;

import java.time.LocalDate;

/**
 * Boilerplate for database data sending and receiving in guests package
 */
public class Guests {
    private String name;
    private String hotel;
    private String room_number;
    private String surname;
    private String email;
    private Integer number;
    private Integer id_number;
    private String standard;
    private String price;
    private LocalDate startDate;
    private LocalDate finishDate;



    /***
     *
     * @param name
     * @param surname
     * @param email
     * @param number
     * @param id_number
     * @param hotel
     * @param room_number
     * @param startDate
     * @param finishDate
     * @param standard
     * @param price
     * In those variables we hold infomrations set in text fields
     * and here we have the contructor which sets appropriate values to  certain variables
     */

    public Guests(String name, String surname, String email, Integer number, Integer id_number, String hotel, String room_number, LocalDate startDate, LocalDate finishDate
                    , String standard, String price) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.number = number;
        this.id_number = id_number;
        this.hotel = hotel;
        this.room_number = room_number;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.standard = standard;
        this.price = price;
    }




    /***
     *
     * @return value of variable called name
     */
    public String getName() {
        return name;
    }

    /***
     *
     * @return value of variable called surname
     */
    public String getSurname() {
        return surname;
    }
    /***
     *
     * @return value of variable called email
     */

    public String getEmail() {
        return email;
    }

    /***
     *
     * @return value of variable called number
     */

    public Integer getNumber() {
        return number;
    }
    /***
     *
     * @return value of variable called numberID
     */

    public Integer getId_number() {
        return id_number;
    }
    /***
     *
     * @return value of variable called hotel
     */

    public String getHotel() {
        return hotel;
    }
    /***
     *
     * @return value of variable called room_number
     */
    public String getRoom_number() {
        return room_number;
    }
    /***
     *
     * @return value of variable called startDate
     */
    public LocalDate getStartDate() {
        return startDate;
    }
    /***
     *
     * @return value of variable called finishDate
     */
    public LocalDate getFinishDate() {
        return finishDate;
    }
    /***
     *
     * @return standard
     */
    public String getStandard() {
        return standard;
    }
    /***
     *
     * @return price
     */
    public String getPrice() {
        return price;
    }


}
