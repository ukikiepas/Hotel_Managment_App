package com.example.siechoteli.klient.guests;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class GuestsTest {

    Guests guest = new Guests("Lukasz", "Kiepas", "s091274@student.tu.kielce.pl", 123,1234,"PSK", "13", LocalDate.parse("2001-13-09"), LocalDate.parse("19.05.2022"), "1", "30");

    @Test
    void nameTest(){
        assertEquals("Lukasz", guest.getName());
    }

    @Test
    void nameSurname(){
        assertEquals("Kiepas", guest.getSurname());
    }

    @Test
    void emailTest(){
        assertEquals("s091274@student.tu.kielce.pl", guest.getEmail());
    }

    @Test
    void phoneNumberTest(){
        assertEquals(123, guest.getNumber());
    }

    @Test
    void id_numberTest(){
        assertEquals(1234, guest.getId_number());
    }


    @Test
    void hotelTest(){
        assertEquals("PSK", guest.getHotel());
    }

    @Test
    void roomNumberTest(){
        assertEquals("13", guest.getRoom_number());
    }

    @Test
    void startDateTest(){
        assertEquals(LocalDate.parse("2001-13-09"), guest.getStartDate());
    }

    @Test
    void finishDateTest(){
        assertEquals(LocalDate.parse("19.05.2022"), guest.getFinishDate());
    }

    @Test
    void standardTest(){
        assertEquals("1", guest.getStandard());
    }

    @Test
    void priceTest(){
        assertEquals("30", guest.getPrice());
    }



}